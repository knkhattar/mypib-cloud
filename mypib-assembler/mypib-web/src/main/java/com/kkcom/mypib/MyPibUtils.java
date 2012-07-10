package com.kkcom.mypib;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;

import org.springframework.security.crypto.codec.Base64;

import com.kkcom.mypib.constants.MyPibConstants;

//import org.apache.commons.codec.binary.Base64;

public class MyPibUtils {

	public static void main(String[] args) {
		try {

			//final String toBeEncrypted = "testid";//qM7Nw41vQw0=
			final String toBeEncrypted = "testpass";//Nr6QJLD0Te+wa9+W55Z1zA==
			String encryptedString = getEncryptedValue(toBeEncrypted);
			System.out.println("encryptedString:: " + encryptedString);
			String origilanString = getDecryptedValue(encryptedString);
			System.out.println("origilanString:: " + origilanString);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static  String getEncryptedValue(final String toBeEncrypted)
			throws Exception {

		
		// INITIALISE THE VARIABLES
		final String cryptoSeed = MyPibConstants.CRYPTO_SEED;
		DESKeySpec keySpec = new DESKeySpec(cryptoSeed.getBytes("UTF8"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(keySpec);

		// ENCODE

		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] cleartext = toBeEncrypted.getBytes("UTF8");
		byte[] newbyte = cipher.doFinal(cleartext);
		String encrypedString = new String(Base64.encode(newbyte));
		return encrypedString;
	}

	public static  String getDecryptedValue(final String toBeDecrpted)
			throws Exception {

		// INITIALISE THE VARIABLES
		final String cryptoSeed = MyPibConstants.CRYPTO_SEED;
		DESKeySpec keySpec = new DESKeySpec(cryptoSeed.getBytes("UTF8"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(keySpec);

		// 1.decoded bytes, 2. decrypted bytes 3. decrypted string
		byte[] decodedBytes = Base64.decode(toBeDecrpted.getBytes());
		Cipher cipherDec = Cipher.getInstance("DES"); // cipher is not
														// thread safe
		cipherDec.init(Cipher.DECRYPT_MODE, key);

		byte[] decryptedBytes = (cipherDec.doFinal(decodedBytes));
		String decryptedString = new String(decryptedBytes);

		return decryptedString;

	}

}
