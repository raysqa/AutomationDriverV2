package raysullivan.operation;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

public class AutomationDriverBrowser {
	private static AutomationDriverUtil util = new AutomationDriverUtil();
	private static WebDriver driver;

	public static void takeScreenShot(final WebDriver webdriver, final String fileWithPath) throws Exception {
		// Convert web driver object to TakeScreenshot
		final TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
		// Call getScreenshotAs method to create image file
		final File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		// Move image file to new destination
		final File destFile = new File(fileWithPath);
		// Copy file at destination
		FileUtils.copyFile(srcFile, destFile);
		// Cleanup
		FileUtils.deleteQuietly(srcFile);
	}

	public static WebDriver getDriver(final FirefoxProfile profile) throws AutomationDriverException {
		if (util.getBrowser() ==null || util.getBrowser() == "") {
			throw new AutomationDriverException("Browser cannot be blank or null");
		}
		if (driver == null) {
			switch (util.getBrowser().toLowerCase()) {
			case "ie":
				System.setProperty("webdriver.ie.driver", util.getDriverPath() + "IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				break;
			case "chrome":
				System.setProperty("webdriver.chrome.driver", util.getDriverPath() + "chromedriver.exe");
				driver = new ChromeDriver();
				break;
			case "opera":
				driver = new OperaDriver();
				break;
			case "safari":
				driver = new SafariDriver();
				break;
			case "firefox":
				System.setProperty("webdriver.gecko.driver", util.getDriverPath() + "geckodriver.exe");
				if (profile == null) {
					driver = new FirefoxDriver();
				} else {
					driver = new FirefoxDriver(profile);
				}
				break;
			default:
				throw new AutomationDriverException("Browser " + util.getBrowser() + " not supported");
			}
			try {
				driver.manage().window().maximize();
			} catch (UnsupportedOperationException e) {
			}
		}
		return driver;
	}
}
