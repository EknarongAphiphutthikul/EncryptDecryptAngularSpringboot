package th.eknarongaphiphutthikul.encryptdecrypt.encryptdecrypt;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AesEncryptDecryptUtils {

	@Autowired
	private AesGcmSecurity aesGcmSecurity;

	public String encrypt(String plainText) throws Exception {
		return aesGcmSecurity.encrypt(getKey(), plainText);
	}

	public String decrypt(String encryptText) throws Exception {
		return aesGcmSecurity.decrypt(getKey(), encryptText);
	}

	public String getKey() throws Exception {
		String key = "jWLCmil2X75TMBWpLCyny6bgFZtJIxEL5P8Fr1eSaCc=";
		return key;
	}
	
	
	@PostConstruct
	public void testAes() throws Exception {
		System.out.println("AES TEST");
		String encryptText = encrypt("akenarongAES");
		System.out.println(encryptText);
		System.out.println(decrypt(encryptText));
	}

}
