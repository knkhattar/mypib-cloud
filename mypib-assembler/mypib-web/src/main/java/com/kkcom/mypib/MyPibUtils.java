package com.kkcom.mypib;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;

import org.springframework.security.crypto.codec.Base64;

import com.kkcom.mypib.constants.MyPibConstants;

//import org.apache.commons.codec.binary.Base64;

/*
 * Compiled with jdk1.6.0
 * */
public class MyPibUtils {
	
	public static void main(String[] args) {
		try {
			// Program arguments 1. E or D, 2. string 3. seed
			

			final String eOrD = args[0];
			final String toBeOperated = args[1];
			final String seed = args[2];
			if (eOrD.equalsIgnoreCase("E")) {
				System.out.println("Encrypted value is "
						+ getEncryptedValue(toBeOperated, seed));
			} else {
				System.out.println("Decrypted value is "
						+ getDecryptedValue(toBeOperated, seed));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String getEncryptedValue(final String toBeEncrypted,
			final String seed) throws Exception {

		// INITIALISE THE VARIABLES
		final String cryptoSeed = seed;
		DESKeySpec keySpec = new DESKeySpec(cryptoSeed.getBytes("UTF8"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(keySpec);

		// ENCODE

		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] cleartext = toBeEncrypted.getBytes("UTF8");
		byte[] newbyte = cipher.doFinal(cleartext);
		String encrypedString = new String(
				org.springframework.security.crypto.codec.Base64
						.encode(newbyte));
		return encrypedString;
	}

	public static String getDecryptedValue(final String toBeDecrpted,
			final String seed) throws Exception {

		// INITIALISE THE VARIABLES
		final String cryptoSeed = seed;
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
	
