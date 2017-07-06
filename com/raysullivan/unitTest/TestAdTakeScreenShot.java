package raysullivan.unitTest;


import static org.fest.assertions.api.Assertions.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import raysullivan.operation.AdBrowser;
import raysullivan.operation.AdUtil;

public class TestAdTakeScreenShot {
	private AdUtil util = new AdUtil();
	private FirefoxProfile profile = null;
	private ProfilesIni allProfiles = null;
	private AdBrowser br = new AdBrowser();

	WebDriver driver;

	@AfterTest
	public void afterTest() throws Exception {
		try {
			driver.close();
		} catch (NoSuchSessionException nsse) {
		}
		try {
			Thread.sleep(1000);
			driver.quit();
		} catch (Exception e) {
		}
		Thread.sleep(1000);
		
	}

	@BeforeTest
	public void beforeTest() throws Exception {
		util.setBrowser("Firefox");
		allProfiles = new ProfilesIni();
		profile = allProfiles.getProfile("TestProfile");
		driver = br.getDriver(profile);
	}

	@Test(description = "testScreenShot", enabled = true)
	public void testScreenShot() throws Exception {
		boolean fail = false;
		String baseScreenshot = util.getTestReportPath();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
		String ssfile = baseScreenshot + "FailureScreenshot_" + dateFormat.format(new Date()) + ".png";
		br.takeScreenShot(br.getDriver(null), ssfile);
		File f = new File(ssfile);
		if (!f.exists() || f.isDirectory()) {
			fail = true;
		}
		assertThat(fail).isEqualTo(false);
		f.delete();
	}
}
