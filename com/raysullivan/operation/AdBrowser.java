package raysullivan.operation;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdBrowser {
	private static AdUtil util = new AdUtil();
	private static AdKeywords kw = new AdKeywords();
	private static WebDriver driver;
	private WebDriverWait waitVar;
	private static final int millisec = 1000;
	private long starttime, endtime;

	//public AdBrowser(WebDriver driver, WebDriverWait waitVar) {
	//	this.driver = driver;
	//	this.waitVar = waitVar;
	//}

	public String[] perform(Properties p, String propertyName, String cell[], AdVariable var,
			AdUtil tool) throws Exception {
		String operation = cell[1];
		String objectName = cell[2];
		String value = cell[3];
		String valueType = cell[4];
		String variable = cell[5];
		String variable2 = cell[6];
		String returnMessage[] = new String[3];
		returnMessage[1] = util.getErrorString(); // actual result
		returnMessage[2] = util.getSuccessString(); // expected result
		WebElement checkbox = null;
		starttime = new Date().getTime();
		switch (operation.toUpperCase()) {
		case "SKIP":
			// Ignore the step and return success
			returnMessage[1] = util.getSuccessString();
			break;
		case "CLICK":
			// Perform click
			returnMessage[1] = kw.click(p, objectName, propertyName);
			break;
		case "CLICKON":
			returnMessage[1] = kw.clickOn(p, objectName, propertyName, checkbox);
			break;
		case "CLICKOFF":
			returnMessage[1] = kw.clickOff(p, objectName, propertyName, checkbox);
			break;
		case "CLICKNOASSERT":
			returnMessage[1] = kw.clickNoAssert(p, objectName, propertyName);
			break;
		case "CLICKALERT":
			returnMessage[1] = kw.clickAlert(driver);
			break;
		case "PAUSE":
			// sleep for the determined number of milliseconds
			returnMessage[1] = kw.pause(value);
			break;
		case "RELOAD":
			returnMessage[1] = kw.reload(driver);
			break;
		case "SUBMIT":
			// Perform submit
			returnMessage[1] = kw.submit(p, objectName, propertyName);
			break;
		case "SETTEXT":
			returnMessage[1] = kw.setText(p, objectName, propertyName, value, variable, valueType, var);
			break;
		case "SELECT":
			returnMessage[1] = kw.select(p, objectName, propertyName, value, variable, operation, var);
			break;
		case "DESELECT":
			returnMessage[1] = kw.deselect(p, objectName, propertyName, value, variable, operation, var);
			break;
		case "SELECTBYINDEX":
			returnMessage[1] = kw.selectByIndex(p, objectName, propertyName, value, variable, valueType, operation, var);
			break;
		case "DESELECTBYINDEX":
			returnMessage[1] = kw.deselectByIndex(p, objectName, propertyName, value, variable, valueType, operation, var);
			break;
		case "CLEAR":
			returnMessage[1] = kw.clear(p, objectName, propertyName);
			break;
		case "GOTOURL":
			// Get url of application
			returnMessage[1] = kw.gotoUrl(p, objectName, propertyName, driver, value);
			break;
		case "GOTO":
			returnMessage[1] = kw.gotoAddress(p, objectName, propertyName, value, variable, operation, var);
			break;
		case "ASSERTURL":
			returnMessage[1] = kw.assertUrl(objectName, value, variable, operation, var);
			break;
		case "ASSERTTEXT":
			returnMessage[1] = kw.assertText(p, objectName, propertyName, value, variable, operation, var);
			break;
		case "ASSERTEQUAL":
			returnMessage[1] = kw.assertEqual(variable, variable2, var);
			break;
		case "ASSERTNOTEQUAL":
			returnMessage[1] = kw.assertNotEqual(variable, variable2, var);
			break;
		case "ASSERTCONTAINS":
			returnMessage[1] = kw.assertContains(variable, variable2, var);
			break;
		case "ASSERTNOTCONTAINS":
			returnMessage[1] = kw.assertNotContains(variable, variable2, var);
			break;
		case "CREATEVARIABLE":
			returnMessage[1] = kw.createVar(value, variable, operation, var);
			break;
		case "CONTAINSTEXT":
			returnMessage[1] = kw.containsText(p, objectName, propertyName, value, variable, operation, var);
			break;
		case "STORETEXT":
			returnMessage[1] = kw.storeText(p, objectName, propertyName, value, variable, operation, var);
			break;
		case "ASSERTATTRIBUTE":
			returnMessage[1] = kw.assertAttribute(p, objectName, propertyName, value, variable, valueType, operation,
					var);
			break;
		case "WAITVISIBLE":
			returnMessage[1] = kw.pause("1");
			returnMessage[1] = kw.waitVisible(p, objectName, propertyName, waitVar);
			break;
		case "WAITINVISIBLE":
			returnMessage[1] = kw.waitInvisible(p, objectName, propertyName, waitVar);
			break;
		case "CLICKANDHOLD":
			returnMessage[1] = kw.clickAndHold(p, objectName, propertyName, value, driver);
			break;
		case "HOVER":
			// rs hover action not supported by Selenium Webdriver 3.0.1 - see mozilla bug
			// rs https://bugzilla.mozilla.org/show_bug.cgi?id=1292178
			returnMessage[1] = kw.hover(p, objectName, propertyName, driver);
			break;
		case "SWITCHFRAME":
			returnMessage[1] = kw.switchFrame(p, objectName, propertyName, driver);
			break;
		case "LISTWINDOWS":
			returnMessage[1] = kw.listWindows(driver);
			break;
		case "LISTFRAMES":
			returnMessage[1] = kw.listFrames(driver);
			break;
		case "SCROLLTO":
			returnMessage[1] = kw.scrollTo(p, objectName, propertyName, driver);
			break;
		case "NAVFORWARD":
			returnMessage[1] = kw.navForward(driver);
			break;
		case "NAVBACK":
			returnMessage[1] = kw.navBackward(driver);
			break;
		case "SCREENSHOT":
			String baseScreenshot = util.getTestReportPath();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
			String ssfile = baseScreenshot + "Screenshot_" + util.getSpreadsheet() + "_" + util.getWorksheet() + "_"
					+ util.getTestCase() + "_" + dateFormat.format(new Date()) + ".png";
			try {
				takeScreenShot(driver, ssfile);
			} catch (Exception e) {
				returnMessage[1] = "Error:  Unable to create a SCREENSHOT";
				break;
			}
			returnMessage[1] = util.getSuccessString();
			break;
		default:
			// error
			throw new AdException("Error: " + operation + " is not a valid Keyword");
		}
		endtime = new Date().getTime();
		returnMessage[0] = Float.toString(util.calcEt(endtime, starttime, millisec));
		return returnMessage;
	}

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

	public static WebDriver getDriver(final FirefoxProfile profile) throws AdException {
		if (util.getBrowser() == null || util.getBrowser() == "") {
			throw new AdException("Browser cannot be blank or null");
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
				throw new AdException("Browser " + util.getBrowser() + " not supported");
			}
			try {
				driver.manage().window().maximize();
			} catch (UnsupportedOperationException e) {
			}
		}
		return driver;
	}

	WebElement findElement(By by) {
		WebElement elem = driver.findElement(by);
		// draw a border around the found element
		if (driver instanceof JavascriptExecutor) {
			((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid yellow'", elem);
		}
		return elem;
	}

}
