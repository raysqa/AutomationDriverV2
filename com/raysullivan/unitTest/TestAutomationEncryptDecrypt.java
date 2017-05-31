package raysullivan.unitTest;

import org.testng.annotations.*;

import static org.fest.assertions.api.Assertions.*;

import raysullivan.operation.AutomationDriverEncryptDecrypt;
import raysullivan.operation.AutomationDriverException;

public class TestAutomationEncryptDecrypt {

	@Test(dataProvider = "encryptValues", description = "encryptString", enabled = true)
	public void encryptString(String toEncrypt, String keyString, String encrypted) throws Exception {
		String newString = AutomationDriverEncryptDecrypt.encrypt(toEncrypt, keyString);
		// System.out.println("Encrypt " + toEncrypt + ":\t" + newString);
		assertThat(newString).isEqualTo(encrypted);
	}

	@Test(dataProvider = "encryptValuesExceptions", description = "encryptStringExceptions", expectedExceptions = AutomationDriverException.class, enabled = true)
	public void encryptStringExceptions(String toEncrypt, String keyString) throws Exception {
		AutomationDriverEncryptDecrypt.decrypt(toEncrypt, keyString);
	}

	@Test(dataProvider = "encryptValuesBad", description = "encryptString", enabled = true)
	public void encryptStringBad(String toEncrypt, String keyString, String encrypted) throws Exception {
		String newString = AutomationDriverEncryptDecrypt.encrypt(toEncrypt, keyString);
		// System.out.println("Encrypt " + toEncrypt + ":\t" + newString);
		assertThat(newString).isNotEqualTo(encrypted);
	}

	@Test(dataProvider = "decryptValues", description = "decryptString", enabled = true)
	public final void decryptString(String encryptedData, String keyString, String resultString) throws Exception {
		assertThat(AutomationDriverEncryptDecrypt.decrypt(encryptedData, keyString)).isEqualTo(resultString);
	}

	@Test(dataProvider = "decryptValuesExceptions", description = "decryptStringExceptions", expectedExceptions = AutomationDriverException.class, enabled = true)
	public final void decryptStringExceptions(String encryptedData, String keyString, String resultString)
			throws Exception {
		AutomationDriverEncryptDecrypt.decrypt(encryptedData, keyString);
	}

	@Test(dataProvider = "decryptValuesInvalid", description = "decryptStringInvalid", enabled = true)
	public final void decryptStringBad(String encryptedData, String keyString, String resultString) throws Exception {
		assertThat(AutomationDriverEncryptDecrypt.decrypt(encryptedData, keyString)).isNotEqualTo(resultString);
	}

	@DataProvider
	public final Object[][] encryptValues() {
		return new String[][] { new String[] { "Welcome1", "automationDriver", "ieKkIwygTyBf63mi62KD7w==" },
				new String[] { "Trummino65", "automationDriver", "UbvSbIXxhSqpFp895+teXA==" },
				new String[] { "Welcome1", "MerryChristmas!#", "UTrj43lAOWmZjXaqGaKnBg==" },
				new String[] { "Welcome2", "MerryChristmas!#", "tNs3FeJl0BYnShvQl0fqvA==" },
				new String[] { "DoTheTwist", "automationDriver", "YtPA1/s+SdGJSKNeq5EjSA==" },
				new String[] { "Welcome2", "BaseballNational", "4QUUgpWUehdpYUKU2U8xZA==" },
				new String[] {
						"Incredibly Long Text String with blanks and other stuff like special characters !@#$%^&*()+_|{}[]:<>,./?",
						"automationDriver",
						"A0TEWyN1ibkJ/1uMJ9BkqKrJfTPpnaEsgnz7sygQyl2IOHdXkeBk9Cz47ciwkekp5x8WYr+7cvDiphPAB7Sm3s5Q7fgt/5Sj3Xsp+PbE/MT43uiL2aWE6msoHnJk4JwWnSt5wYrtwtOEw07vBSDMlg==" },
				new String[] { "", "BaseballNational", "3t0JHljez4OIHnou97KwVw==" }, };
	}

	@DataProvider
	public final Object[][] encryptValuesBad() {
		return new String[][] {
			    new String[] { "Welcome1","horsehockeynamaz" , "4QUUgpWUehdpYUKU2U8xZA=="},
				new String[] { "Trummino65", "automationDriver" , "4QUUgpWUehdpYUKU2U8xZA=="}, 
				new String[] { "Welcome1", "MerryChristmas!#" , "4QUUgpWUehdpYUKU2U8xZA=="},
				new String[] { "Welcome2", "MerryChristmas!#" , "4QUUgpWUehdpYUKU2U8xZA=="}, 
				new String[] { "DoTheTwist", "automationDriver" , "4QUUgpWUehdpYUKU2U8xZA=="},
				new String[] { "Welcome2", "BaseballNational" , "4QUUgpWUehdpKUYU2U8xZA=="},
				new String[] {
						"Incredibly Long Text String with blanks and other stuff like special characters !@#$%^&*()+_|{}[]:<>,./?",
						"automationDriver" , "4QUUgpWUehdpYUKU2U8xZA=="},
				new String[] { "", "BaseballNational" , "4QUUgpWUehdpYUKU2U8xZA=="}, };
	}

	@DataProvider
	public final Object[][] encryptValuesExceptions() {
		return new String[][] { new String[] { null, "BaseballNational" }, new String[] { "Welcome2", null },
				new String[] { "Welcome2", "" }, new String[] { "", "Happy Birthday" }, new String[] { null, null },
				new String[] { "", "" }, new String[] { "Welcome2", "CricketNational" },
				new String[] { "Welcome2", "BaseballNationalLeague" }, };
	}

	@DataProvider
	public final Object[][] decryptValues() {
		return new String[][] { new String[] { "ieKkIwygTyBf63mi62KD7w==", "automationDriver", "Welcome1" },
				new String[] { "UTrj43lAOWmZjXaqGaKnBg==", "MerryChristmas!#", "Welcome1" },
				new String[] { "tNs3FeJl0BYnShvQl0fqvA==", "MerryChristmas!#", "Welcome2" },
				new String[] { "YtPA1/s+SdGJSKNeq5EjSA==", "automationDriver", "DoTheTwist" },
				new String[] { "4QUUgpWUehdpYUKU2U8xZA==", "BaseballNational", "Welcome2" },
				new String[] { "3t0JHljez4OIHnou97KwVw==", "BaseballNational", "" },
				new String[] { "UbvSbIXxhSqpFp895+teXA==", "automationDriver", "Trummino65" },
				new String[] {
						"A0TEWyN1ibkJ/1uMJ9BkqKrJfTPpnaEsgnz7sygQyl2IOHdXkeBk9Cz47ciwkekp5x8WYr+7cvDiphPAB7Sm3s5Q7fgt/5Sj3Xsp+PbE/MT43uiL2aWE6msoHnJk4JwWnSt5wYrtwtOEw07vBSDMlg==",
						"automationDriver",
						"Incredibly Long Text String with blanks and other stuff like special characters !@#$%^&*()+_|{}[]:<>,./?" },
				new String[] { "", "automationDriver", "" }, };
	}

	@DataProvider
	public final Object[][] decryptValuesExceptions() {
		return new String[][] { new String[] { null, "automationDriver", "Welcome1" },
				new String[] { "ieKkIwygTyBf63mi62KD7w==", "", "Welcome1" },
				new String[] { "ieKkIwygTyBf63mi62KD7w==", null, "Welcome1" },
				new String[] { "ieKkIwygTyBf63mi62KD7w==", "MerryChristmas!", "Welcome1" },
				new String[] { "ieKkIwygTyBf63mi62KD7w==", "MerryChristmas!#Today", "Welcome1" },
				new String[] { "ieKkIwygTyBf62mi62KD7w==", "MerryChristmas!#Today", "Welcome1" },
				new String[] { "", "MerryChristmas!#Today", "Welcome1" },
				new String[] { "ieKkIwygTyBf62mi62KD7w==", "", "Welcome1" }, new String[] { null, null, null },
				new String[] { "ieKkIwygTyBf63mi62KD7w=", "MerryChristmas!#Today", "Welcome1" },
				new String[] { "ieKkIwygTyBf63mi62KD7w==", "MerryChristmas!#", "Welcome1" }, };
	}

	@DataProvider
	public final Object[][] decryptValuesInvalid() {
		return new String[][] { new String[] { "ieKkIwygTyBf63mi62KD7w==", "automationDriver", "Welcome2" },
				new String[] { "UTrj43lAOWmZjXaqGaKnBg==", "MerryChristmas!#", "Welcome2" },
				new String[] { "tNs3FeJl0BYnShvQl0fqvA==", "MerryChristmas!#", "Welcome1" },
				new String[] { "YtPA1/s+SdGJSKNeq5EjSA==", "automationDriver", "DontDoTheTwist" },
				new String[] { "4QUUgpWUehdpYUKU2U8xZA==", "BaseballNational", "Welcome1" },
				new String[] { "3t0JHljez4OIHnou97KwVw==", "BaseballNational", "Merry Christmas" },
				new String[] { "3t0JHljez4OIHnou97KwVw==", "BaseballNational", null },
				new String[] {
						"A0TEWyN1ibkJ/1uMJ9BkqKrJfTPpnaEsgnz7sygQyl2IOHdXkeBk9Cz47ciwkekp5x8WYr+7cvDiphPAB7Sm3s5Q7fgt/5Sj3Xsp+PbE/MT43uiL2aWE6msoHnJk4JwWnSt5wYrtwtOEw07vBSDMlg==",
						"automationDriver",
						"Incrediblyy Long Text String with blanks and other stuff like special characters !@#$%^&*()+_|{}[]:<>,./?" },
				new String[] { "", "automationDriver", "Welcome1" }, };
	}

}