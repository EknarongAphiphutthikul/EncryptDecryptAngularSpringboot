package th.eknarongaphiphutthikul.encryptdecrypt.encryptdecrypt;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {
	
	private static String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqnHsy5NH/XTusUHhd+mfqFDBfD7Fj8Y+M8gm6Rb9ugHWbiyCliLqoS80aFY2yTMlJ4pPCtcIxNgypwLq+xUyNKQbAv92D/O7XK1NVhQCETJpIa9uwoFyxlTf7Wmp/Ct5HM56XEPwdT+prRt4h+V1PjZlHhEmI0BKru01Y5sF4MGrI1A6R/Sf48XaRYVXk2vbvBZxazOShIGTjSLIIO8CDp25X7/djn8wuuqZpRdRupIm2LWbAVXMvfzviL1ReiazbkDaztLF+9KIVmt9tTh/Aw5FuAwbUk6j+XgW2CAkgZhNoutzre4F2t7NvpKLay8ae5+9wbwGEv0/yWsauOGVSwIDAQAB";
	private static String privateKey = "MIIEowIBAAKCAQEAqnHsy5NH/XTusUHhd+mfqFDBfD7Fj8Y+M8gm6Rb9ugHWbiyCliLqoS80aFY2yTMlJ4pPCtcIxNgypwLq+xUyNKQbAv92D/O7XK1NVhQCETJpIa9uwoFyxlTf7Wmp/Ct5HM56XEPwdT+prRt4h+V1PjZlHhEmI0BKru01Y5sF4MGrI1A6R/Sf48XaRYVXk2vbvBZxazOShIGTjSLIIO8CDp25X7/djn8wuuqZpRdRupIm2LWbAVXMvfzviL1ReiazbkDaztLF+9KIVmt9tTh/Aw5FuAwbUk6j+XgW2CAkgZhNoutzre4F2t7NvpKLay8ae5+9wbwGEv0/yWsauOGVSwIDAQABAoIBAAECU6iQ4qg5fQBHp5ISLAPPFpYpuPm07sFXugM/Q5lKUX6tfMhuxHzXzrOfbwuZ5epqAzXJOCASN5qNhd6erYFqR06pPIWzp7RQwlh0Hd8EMWMlqr40Df3mmrzJF1yK1Vi6UDcjn9TR36tKslf7n8oSgF7BMzyaMNcASISiclJ2UJb0Af7gS0k0vhns9KeBVrxAJ2WAH5ysSKjZ4PGhwfqjqn/p1K1Y0+YCyKNacanzitkctVDnlABJ/UnsS/Ixi4r/of7vC2ZzXQwzStPUCKXZ2DnEvkapj1rhrFibk65Hc6iSdsjmXomXgCiXVjmhKDgDeymQjfMyOBgse9kV/iECgYEA3Hndf6UG4wI9r3tS3ZGFgLSyo4DcbFLK7d/b4S9SPfa1TW3fCzOxwGkDapewbt2pPbNuk/ly9UOirecZHQ9F3cvTPaH+IiUDRsD8GgP6xbqc1e5SVLGltlB6IfR5RKJ2KIB7Iul+FLXHyEAPAwikIzvH2xJxu9oVShvYJ/wwfrcCgYEAxehk6D0hGEMpnScwxj1JKnmbdbsm6Pe4aDoeIVQeV6APQf7bJ1nPQZTAfNFOdmVZ3ig1lx8MeI4nEi8cX6P0zVu22tJpiHX50GMRBUMlqDRTCR2KPchbD62XidE6elu/N5fNHGdOAQFvmTo6ardnb+EH7zDL7gQC0co5M65ICg0CgYAPnBEymFyEdgQoCHM1fLgXBLQhH157zODRNqg6qT8JZA4yDTVddauAE+9J/LNhJhB1bd7GW0LoWoGKDnKYvBwWN/gcxPtZL4Wr3L38mYuHtE6mVtk9ZdZ1sfzzqntrgJUdCPuu3RkwHi6m3uIxNMtqMzFTMVscEbSwKoiDTDeiwQKBgBt3zcmN8nHzoGr3bYWWK/mBN1p3x0a+WxHcPyJREoUG6hc20CP+Cw4HH1AznLhCCN8UpZt767cvw6x6bW24y9lUyWRFn/AYr7uEhHA+iZN2769sMiuxCzJ53XZ7W6pv4XJN12IB1Im/MUYLFuCI5ApbKUbBdwxp8ho7/wR1Q/phAoGBALA/7TWf4YHaaOfSYsvAHtp9V1CZYTzXxblrWffHrR6r4f/pNpM2c7hYL4gjEVa7QhbucG/sQq+g2VJQQoMiMEN+fKgrBIfGC8+qc6JEp8n8VHaw+AHu3q6i39f4zGEKgKnfhZFVHWfGhITvtjgY/5rCnFrTRKp4jY3fjOmaYNkZ";
	
	@Autowired private RSASecurity rSASecurity;
	
	public String rsaDecrypt(String msg) throws Exception {
		return rSASecurity.decrypt(msg, privateKey);
	}	
	
	public String rsaEncrypt(String msg) throws Exception {
		return rSASecurity.encrypt(msg, publicKey);
	}
	
	@PostConstruct
	public void test() throws Exception {
		/*
		 * Test RSA
		 */
		String encrypt = rsaEncrypt("akenarong");
		System.out.println(encrypt);
		System.out.println(rsaDecrypt(encrypt));
	}
}