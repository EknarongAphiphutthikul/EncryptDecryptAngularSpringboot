package th.eknarongaphiphutthikul.encryptdecrypt.encryptdecrypt;

import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class AesGcmSecurity {

	protected final int AES_KEY_SIZE = 256;
	protected final int GCM_IV_LENGTH = 12;
	protected final int GCM_TAG_LENGTH = 16;
	protected final String AES_WORD = "AES";
	protected final String CIPHER_INSTANCE = "AES/GCM/NoPadding";

	public String encrypt(String key, String plainText) throws Exception {
		if (StringUtils.isBlank(plainText)) {
			return plainText;
		}
		
		SecretKey secretKey = initSecreKey(key);
		byte[] IV = randomIV();
		byte[] cipherText = encrypt(plainText.getBytes(), secretKey, IV);
		return Base64.getEncoder().encodeToString(IV) + Base64.getEncoder().encodeToString(cipherText);
	}
	
	public String decrypt(String key, String encryptText) throws Exception {
		if (!checkPrepareDecrypt(key, encryptText)) {
			return encryptText;
		}
		
		SecretKey secretKey = initSecreKey(key);
		byte[] IV = Base64.getDecoder().decode(encryptText.substring(0, 16));
		byte[] cipherText = Base64.getDecoder().decode(encryptText.substring(16));
		return decrypt(cipherText, secretKey, IV);
	}
	
	protected boolean checkPrepareDecrypt(String key, String encryptText) throws Exception {
		if (StringUtils.isBlank(encryptText)) {
			return false;
		}
		
		if (StringUtils.isBlank(key)) {
			throw new Exception("key is blank.");
		}
		
		return true;
	}
	
	protected byte[] randomIV() throws Exception {
		byte[] IV = new byte[GCM_IV_LENGTH];
		SecureRandom random = new SecureRandom();
		random.nextBytes(IV);
		return IV;
	}

	protected byte[] encrypt(byte[] plaintext, SecretKey key, byte[] IV) throws Exception {
		SecretKeySpec keySpec = initSecretKeySpec(key);
		GCMParameterSpec gcmParameterSpec = initGCMParameterSpec(IV);
		Cipher cipher = initCipher(Cipher.ENCRYPT_MODE, keySpec, gcmParameterSpec);
		byte[] cipherText = cipher.doFinal(plaintext);
		return cipherText;
	}

	protected String decrypt(byte[] cipherText, SecretKey key, byte[] IV) throws Exception {
		SecretKeySpec keySpec = initSecretKeySpec(key);
		GCMParameterSpec gcmParameterSpec = initGCMParameterSpec(IV);
		Cipher cipher = initCipher(Cipher.DECRYPT_MODE, keySpec, gcmParameterSpec);
		byte[] decryptedText = cipher.doFinal(cipherText);
		return new String(decryptedText);
	}

	protected GCMParameterSpec initGCMParameterSpec(byte[] IV) throws Exception {
		return new GCMParameterSpec(GCM_TAG_LENGTH * 8, IV);
	}
	
	protected SecretKey initSecreKey() throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(AES_WORD);
		keyGenerator.init(AES_KEY_SIZE);
		return keyGenerator.generateKey();
	}

	protected SecretKey initSecreKey(String secretKey) throws Exception {
		SecretKey key = null;
		if (StringUtils.isNotBlank(secretKey)) {
			key = new SecretKeySpec(Base64.getDecoder().decode(secretKey), AES_WORD);
		} else {
			key = initSecreKey();
		}
		return key;
	}

	protected SecretKeySpec initSecretKeySpec(SecretKey key) throws Exception {
		return new SecretKeySpec(key.getEncoded(), AES_WORD);
	}

	protected Cipher initCipher(int mode, SecretKeySpec keySpec, GCMParameterSpec gcmParameterSpec) throws Exception {
		Cipher cipher = Cipher.getInstance(CIPHER_INSTANCE);
		cipher.init(mode, keySpec, gcmParameterSpec);
		return cipher;
	}
}
