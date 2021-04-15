import { Component } from '@angular/core';
import { AESGCMService } from './services/en-decrypt/aes-gcm.service';
import { RSAService } from './services/en-decrypt/rsa.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'en-decrypt-algorithm';

  constructor(
    private aesgcmService: AESGCMService,
    private rSAService: RSAService,
  ) { }

  ngOnInit() {
    console.log("RSA TEST");
    let encryptText = this.rSAService.encryptRsa("eknarongRsa");
    console.log(encryptText);
    console.log(this.rSAService.decryptRsa(encryptText));

    console.log("AES TEST");
    this.aesgcmService.encryptAesGcm("eknarongAesGcm").then((encryptAesGcmText) => {
      console.log(encryptAesGcmText);
      this.aesgcmService.decryptAesGcm(encryptAesGcmText).then((decryptAesGcmText) => {
        console.log(decryptAesGcmText);
      });
    });
  }
}
