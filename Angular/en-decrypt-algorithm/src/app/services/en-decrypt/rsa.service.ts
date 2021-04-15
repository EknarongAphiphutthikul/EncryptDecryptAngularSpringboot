import * as JsEncryptModule from 'jsencrypt';
import key from '@utils/keys/pub.key.json';
import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class RSAService {

    private encryptModule = new JsEncryptModule.JSEncrypt();

    ngOnInit() {
        this.encryptModule.setPublicKey(key.publickey);
        this.encryptModule.setPrivateKey(key.privatekey);
    }

    public encryptRsa(plainText:string) {
        return this.encryptModule.encrypt(plainText);
    }

    public decryptRsa(encryptedText:string) {
        return this.encryptModule.decrypt(encryptedText);
    }
}