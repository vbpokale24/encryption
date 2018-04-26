import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class TripleDESSHA512 {	

	public static String encrypt(String message, String secretKey) throws Exception {
		/*System.out.println("#### message : " + message);
		System.out.println("#### secretKey : " + secretKey);*/
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
		byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
		SecretKey key = new SecretKeySpec(keyBytes, "DESede");
		Cipher cipher = Cipher.getInstance("DESede");
		cipher.init(1, key);
		byte[] plainTextBytes = message.getBytes("utf-8");
		byte[] buf = cipher.doFinal(plainTextBytes);
		byte[] base64Bytes = Base64.encodeBase64(buf);
		String base64EncryptedString = new String(base64Bytes);
	//	System.out.println("#### Encrypted Text : " + base64EncryptedString);

		return base64EncryptedString;
	}

	public static String decrypt(String encryptText, String secretKey) throws Exception {
		byte[] message = Base64.decodeBase64(encryptText.getBytes("utf-8"));
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
		byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
		SecretKey key = new SecretKeySpec(keyBytes, "DESede");
		Cipher decipher = Cipher.getInstance("DESede");
		decipher.init(2, key);
		byte[] plainText = decipher.doFinal(message);
		String DecryptedString = new String(plainText, "UTF-8");
		System.out.println("#### DecryptedString : " + DecryptedString);
		return DecryptedString;
	}
}
