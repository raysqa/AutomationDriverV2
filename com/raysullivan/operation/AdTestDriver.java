package raysullivan.operation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import raysullivan.dataProvider.Default;

@Test(dataProvider = "DefaultDataProvider", dataProviderClass = Default.class)
public class AdTestDriver {

	private WebDriver webdriver = null;
	private AdUtil util = new AdUtil();
	private ProfilesIni allProfiles = null;
	private FirefoxProfile profile = null;

	@AfterTest
	public void afterTest() throws Exception {
		Thread.sleep(1000);
		try {
			AdVideoRecorder.stopRecording();
		} catch (NullPointerException npe) {
		}
	}

	@AfterSuite
	public void afterSuite() throws Exception {
		webdriver.close();
		try {
			Thread.sleep(1000);
			webdriver.quit();
		} catch (Exception e) {
		}
	}

	public final void testDriver(final String spreadsheet, final String worksheet, String resultSpreadsheet,
			final String browser, final String webProfile, final int timeout, final String propertyName,
			final int sheetIterations, final boolean capCsv, final boolean capVideo) throws Exception {
		util.setTestProfile(spreadsheet, worksheet, resultSpreadsheet, browser, webProfile, timeout, propertyName,
				sheetIterations, capCsv, capVideo);
		if (webProfile == "none") {
			webdriver = AdBrowser.getDriver(null);
		} else {
			allProfiles = new ProfilesIni();
			profile = allProfiles.getProfile(webProfile);
			try {
				profile.setPreference("foo.bar", 23);
			} catch (NullPointerException npe) {
				throw new AdException("Error: FireFox Profile " + webProfile + " is invalid or does not exist.");
			}
			webdriver = AdBrowser.getDriver(profile);
		}
	}
}
