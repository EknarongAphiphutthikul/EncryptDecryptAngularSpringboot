import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class SHA512Service {

    public async encryptSHA512(plainText: string) {
        const hashstring = await crypto.subtle.digest({ name: 'SHA-512'}, this.stringToArray(plainText)).then((sha512ArrayBuffer => {return sha512ArrayBuffer}));
        const hashArray = Array.from(new Uint8Array(hashstring));
        const hashHex = hashArray.map(b => b.toString(16).padStart(2, '0')).join('');
        return hashHex
    }

    private stringToArray(bufferString: string) {
        let uint8Array = new TextEncoder().encode(bufferString);
        return uint8Array;
    }
    private arrayToString(bufferValue: Uint8Array) {
        return new TextDecoder().decode(bufferValue);
    }

    private arrayToString1(bufferValue: ArrayBuffer) {
        return new TextDecoder().decode(bufferValue);
    }
}