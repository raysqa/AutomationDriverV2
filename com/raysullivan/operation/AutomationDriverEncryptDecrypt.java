package raysullivan.operation;

import java.security.*;
import java.util.Base64;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class AutomationDriverEncryptDecrypt {
	private static final String ALGORITHM = "AES";

	private static Key generateKey(byte[] keyValue) throws Exception {
		try {
			final Key key = new SecretKeySpec(keyValue, ALGORITHM);
			return key;
		} catch (IllegalArgumentException iae) {
			throw new AutomationDriverException(
					"IllegalArgumentException:  Encrypt/decrypt keystring cannot be null or blank");
		}
	}

	public static String encrypt(final String dataToEncrypt, final String keyString) throws Exception {
		byte[] keyValue;
		final Cipher cipher = Cipher.getInstance(ALGORITHM);
		try {
			keyValue = keyString.getBytes();
			final Key key = generateKey(keyValue);
			cipher.init(Cipher.ENCRYPT_MODE, key);
		} catch (NullPointerException npe) {
			throw new AutomationDriverException("NullPointerException:  Encrypt keystring cannot be null or blank");

		} catch (InvalidKeyException ike) {
			throw new AutomationDriverException(
					"InvalidKeyException:  Decrypt keystring must be 16 characters; keystring length of "
							+ keyString.length() + " is invalid.  Keystring: " + keyString);
		}
		byte[] encVal;
		try {
			encVal = cipher.doFinal(dataToEncrypt.getBytes());
			final String encryptedValue = Base64.getEncoder().encodeToString(encVal);
			return encryptedValue;
		} catch (NullPointerException npe) {
			throw new AutomationDriverException("Error:  String to encrypt cannot be null");
		}

	}

	public static String decrypt(String encryptedData, String keyString) throws Exception {
		final Cipher cipher = Cipher.getInstance(ALGORITHM);
		try {
			byte[] keyValue = keyString.getBytes();
			Key key = generateKey(keyValue);
			cipher.init(Cipher.DECRYPT_MODE, key);

		} catch (NullPointerException npe) {
			throw new AutomationDriverException("NullPointerException:  Decrypt keystring cannot be null or blank");
		} catch (InvalidKeyException ike) {
			throw new AutomationDriverException(
					"InvalidKeyException:  Decrypt keystring must be 16 characters; keystring length of "
							+ keyString.length() + " is invalid.  Keystring: " + keyString);
		}
		byte[] decodedValue;
		try {
			decodedValue = Base64.getDecoder().decode(encryptedData);
			final byte[] decValue = cipher.doFinal(decodedValue);
			final String decryptedValue = new String(decValue);

			return decryptedValue;
		} catch (IllegalArgumentException | BadPaddingException | NullPointerException e) {
			throw new AutomationDriverException("Error:  String to decrypt is not a valid AES cipher.");
		}
	}

}
