import * as JsEncryptModule from 'jsencrypt';
import key from '@utils/keys/pub.key.json';
import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class RSAService {

    private encryptModule = new JsEncryptModule.JSEncrypt();

    public encryptRsa(plainText:string) {
        this.encryptModule.setPublicKey(key.publickey);
        return this.encryptModule.encrypt(plainText);
    }

    public decryptRsa(encryptedText:string) {
        this.encryptModule.setPrivateKey(key.privatekey);
        return this.encryptModule.decrypt(encryptedText);
    }
}