package raysullivan.unitTest;

import org.testng.annotations.Test;

import raysullivan.operation.AdBrowser;
import raysullivan.operation.AdException;
import raysullivan.operation.AdUtil;

import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

public class TestAdBrowser {

	private AdUtil util = new AdUtil();
	private FirefoxProfile profile = null;
	private ProfilesIni allProfiles = null;
	private AdBrowser br = new AdBrowser();

	WebDriver driver;
	
	@AfterTest
	public void afterTest() throws Exception {
	try {
	driver.close();
	} catch (NoSuchSessionException nsse) {}
	try {
		Thread.sleep(1000);
		driver.quit();
	} catch (Exception e) {
	}
	Thread.sleep(1000);
	}
	
	@Test(description = "invalidDriver", dataProvider = "invalidBrowsers", expectedExceptions = AdException.class, enabled=true)
	public void invalidDriver(String browser, String webProfile) throws AdException, Exception {
		util.setBrowser(browser);
		allProfiles = new ProfilesIni();
		profile = allProfiles.getProfile(webProfile);
		driver = br.getDriver(profile);
	}
	
	@Test(description = "getDriverWithProfile", dataProvider = "browsersWithProfile", enabled=true)
	public void getDriverWithProfile(String browser, String webProfile) throws AdException, Exception {
		util.setBrowser(browser);
		allProfiles = new ProfilesIni();
		profile = allProfiles.getProfile(webProfile);
		driver = br.getDriver(profile);
	}

	@DataProvider
	public Object[][] invalidBrowsers() {
		return new Object[][] { new Object[] { "" , null},
			new Object[] { null, null},
			new Object[] { "", "TestProfile" },
			new Object[] { null, "TestProfile" },
		};
	}
	@DataProvider
	public Object[][] browsersWithProfile() {
		return new Object[][] { new Object[] { "firefox", "TestProfile"}, 
			new Object[] { "Safari", null},
			new Object[] { "FIREFOX", null},
			new Object[] { "oPeRa" , "TestProfile"},
			new Object[] { "chrome" ,null},
			new Object[] { "ie" , null},
		};
	}
}
