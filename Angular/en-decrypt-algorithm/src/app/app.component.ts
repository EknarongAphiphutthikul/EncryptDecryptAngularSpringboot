import { Component } from '@angular/core';
import * as JsEncryptModule from 'jsencrypt';
import key from '@utils/keys/pub.key.json';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'en-decrypt-algorithm';

  ngOnInit() {
    const encrypt = new JsEncryptModule.JSEncrypt();
    encrypt.setPublicKey(key.publickey);
    const encryptMsg = encrypt.encrypt("akenarong");
    console.log(encryptMsg);

    encrypt.setPrivateKey(key.privatekey);
    console.log(encrypt.decrypt(encryptMsg));
  }
}
