import { Component } from '@angular/core';
import { AESGCMService } from './services/en-decrypt/aes-gcm.service';
import { RSAService } from './services/en-decrypt/rsa.service';
import { SHA512Service } from './services/en-decrypt/sha512.service';

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
    private shar512Service: SHA512Service,
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

    console.log("SHA-512 TEST");
    this.shar512Service.encryptSHA512("900007000|R202002|764|100|N|X2xkTIeEc987ZwClvgbddKCY7Nu3xbAb").then((sha512Text) => {
      console.log(sha512Text);
    });
  }
}
