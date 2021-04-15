import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class AESGCMService {

    // 256 bit
    private keyAesGcmArray = this.base64ToArrayBuffer("jWLCmil2X75TMBWpLCyny6bgFZtJIxEL5P8Fr1eSaCc=");

    public async encryptAesGcm(plainText: string) {
        const iv = crypto.getRandomValues(new Uint8Array(12));
        const keysAesGcm = await this.importKeyAesGcm().then(cryptoKeyAesGcm => { return cryptoKeyAesGcm });
        const encryptedText = await crypto.subtle.encrypt({ name: 'AES-GCM', iv }, keysAesGcm, new TextEncoder().encode(plainText))
            .then(stringEncrypt => {
                return `${this.arrayBufferToBase64(iv)}${this.arrayBufferToBase64(new Uint8Array(stringEncrypt))}`;
        });
        return encryptedText;
    }

    public async decryptAesGcm(encryptedText: string) {
        const keysAesGcm = await this.importKeyAesGcm().then(cryptoKeyAesGcm => { return cryptoKeyAesGcm });
        const iv = encryptedText.substring(0, 16);
        const plainText = await crypto.subtle.decrypt({ name: 'AES-GCM', iv: this.base64ToArrayBuffer(iv) }, keysAesGcm, this.base64ToArrayBuffer(encryptedText.substring(16)) as ArrayBuffer)
            .then((arrayBufferDecrypt) => {
                let resultString: string = new TextDecoder().decode(arrayBufferDecrypt);
                return resultString.replace(/[\0]/g, '');
            });
        return plainText;
    }

    private importKeyAesGcm() {
        return crypto.subtle.importKey('raw', this.keyAesGcmArray, 'AES-GCM', true, ['encrypt', 'decrypt']);
    }

    private arrayBufferToBase64(bytes: Uint8Array) {
        let binary = '';
        bytes.forEach(char => { binary += String.fromCharCode(char) });
        return window.btoa(binary);
    }

    private base64ToArrayBuffer(base64: string) {
        const binaryString = window.atob(base64);
        const len = binaryString.length;
        const bytes = new Uint8Array(len);
        for (let i = 0; i < len; i++) {
            bytes[i] = binaryString.charCodeAt(i);
        }
        return bytes.buffer;
    }
}