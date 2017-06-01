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

public class AutomationDriverBrowser {
	private static AutomationDriverUtil util = new AutomationDriverUtil();
	private static AutomationDriverKeywords kw = new AutomationDriverKeywords();
	private static WebDriver driver;
	private WebDriverWait waitVar;	
	private static final int millisec = 1000;
	private long starttime, endtime;
	
	public AutomationDriverBrowser(WebDriver driver, WebDriverWait waitVar) {
		this.driver = driver;
		this.waitVar = waitVar;
	}
	public String[] perform(Properties p, String propertyName, String cell[],
			AutomationDriverVariable var, AutomationDriverUtil tool) throws Exception {
		String operation = cell[1];
		String objectName = cell[2];
		String value = cell[3];
		String valueType = cell[4];
		String variable = cell[5];
		String returnMessage[] = new String[3];
		String getVariable[] = new String[2];
		String createVariable[] = new String[2];
		returnMessage[1] = util.getErrorString(); // actual result
		returnMessage[2] = util.getSuccessString(); // expected result
		WebElement checkbox = null;
		starttime = new Date().getTime();
		switch (operation.toUpperCase()) {
			case "SKIP" :
				// Ignore the step and return success
				returnMessage[1] = util.getSuccessString();
				break;
			case "CLICK" :
				// Perform click
				returnMessage = kw.click(p, objectName, propertyName,
						returnMessage);
				break;
			case "CLICKON" :
				returnMessage = kw.clickOn(p, objectName, propertyName, checkbox,
						returnMessage);
				break;
			case "CLICKOFF" :
				returnMessage = kw.clickOff(p, objectName, propertyName, checkbox,
						returnMessage);
				break;
			case "CLICKNOASSERT" :
				returnMessage = kw.clickNoAssert(p, objectName, propertyName,
						returnMessage);
				break;
			case "CLICKALERT" :
				returnMessage = kw.clickAlert(returnMessage);
				break;
			case "PAUSE" :
				// sleep for the determined number of milliseconds
				returnMessage = kw.pause(value, returnMessage);
				break;
			case "REFRESH" :
				returnMessage = kw.refresh(returnMessage);
				break;
			case "SUBMIT" :
				// Perform submit
				returnMessage = kw.submit(p, objectName, propertyName,
						returnMessage);
				break;
			case "SETTEXT" :
				// If the get variable and set variable are the same, error
				returnMessage = kw.setText(p, objectName, propertyName, value,
						variable, operation, var, createVariable, returnMessage);
				break;
			case "SELECT" :
				returnMessage = kw.select(p, objectName, propertyName, value,
						variable, operation, var, createVariable, returnMessage);
				break;
			case "DESELECT" :
				returnMessage = kw.deselect(p, objectName, propertyName, value,
						variable, operation, var, createVariable, returnMessage);
				break;
			case "SELECTBYINDEX" :
				returnMessage = kw.selectByIndex(p, objectName, propertyName,
						value, variable, operation, var, createVariable,
						returnMessage);
				break;
			case "DESELECTBYINDEX" :
				returnMessage = kw.deselectByIndex(p, objectName, propertyName,
						value, variable, operation, var, createVariable,
						returnMessage);
				break;
			case "CLEAR" :
				returnMessage = kw.clear(p, objectName, propertyName,
						returnMessage);
				break;
			case "GOTOURL" :
				// Get url of application
				returnMessage = kw.gotoUrl(p, objectName, propertyName, returnMessage);
				break;
			case "GOTO" :
				returnMessage = kw.gotoAddress(p, objectName, propertyName, value,
						variable, operation, var, createVariable, returnMessage);
				break;
			case "ASSERTURL" :
				returnMessage = kw.assertUrl(objectName, value, variable,
						operation, var, createVariable, returnMessage);
				break;
			case "ASSERTTEXT" :
				returnMessage = kw.assertText(p, objectName, propertyName, value,
						variable, operation, var, createVariable, returnMessage);
				break;
			case "ASSERTEQUAL" :
				returnMessage[1] = kw.assertEqual(value, variable, var);
				break;
			case "ASSERTNOTEQUAL" :
				returnMessage[1] = kw.assertNotEqual(value, variable, var);
				break;
			case "ASSERTCONTAINS" :
				returnMessage[1] = kw.assertContains(value, variable, var);
				break;
			case "ASSERTNOTCONTAINS" :
				returnMessage[1] = kw.assertNotContains(value, variable, var);
				break;
			case "CREATEVARIABLE" :
				returnMessage = kw.createVar(value, variable, operation, var,
						createVariable, returnMessage);
				break;
			case "CONTAINSTEXT" :
				returnMessage = kw.containsText(p, objectName, propertyName,
						value, variable, operation, var, createVariable,
						returnMessage);
				break;
			case "STORETEXT" :
				returnMessage = kw.storeText(p, objectName, propertyName, value,
						variable, operation, var, createVariable, returnMessage);
				break;
			case "ASSERTATTRIBUTE" :
				returnMessage = kw.assertAttribute(p, objectName, propertyName, value,
						variable, valueType, operation, var, createVariable, returnMessage);
				break;
			case "WAITVISIBLE" :
				returnMessage = kw.pause("1", returnMessage);
				returnMessage = kw.waitVisible(p, objectName, propertyName,
						returnMessage);
				break;
			case "WAITINVISIBLE" :
				returnMessage = kw.waitInvisible(p, objectName, propertyName,
						returnMessage);
				break;
			case "CLICKANDHOLD" :
				returnMessage = kw.clickAndHold(p, objectName, propertyName,
						value, returnMessage);
				break;
			case "HOVER" :
				// rs hover action not supported by Selenium Webdriver 3.0.1 - see mozilla bug 
				// rs https://bugzilla.mozilla.org/show_bug.cgi?id=1292178
				returnMessage = kw.hover(p, objectName, propertyName,
						returnMessage);  
				break;
			case "SWITCHFRAME" :
				returnMessage = kw.switchFrame(p, objectName, propertyName,
						returnMessage);
				break;
			case "LISTWINDOWS" :
				returnMessage = kw.listWindows(returnMessage);
				break;
			case "LISTFRAMES" :
				returnMessage = kw.listFrames(returnMessage);
				break;
			case "SCROLLTO" :
				returnMessage = kw.scrollTo(p, objectName, propertyName,
						returnMessage);
				break;
			case "NAVFORWARD" :
				// driver.navigate().forward();
				// returnMessage[1] = util.getSuccessString();
				returnMessage = kw.navForward(p, objectName, propertyName,
						returnMessage);
				break;
			case "NAVBACK" :
				//driver.navigate().back();
				//returnMessage[1] = util.getSuccessString();
				returnMessage = kw.navBackward(p, objectName, propertyName,
						returnMessage);
				break;
			case "SCREENSHOT" :
				String baseScreenshot = util.getTestReportPath();
				SimpleDateFormat dateFormat = new SimpleDateFormat(
						"yyyy-MM-dd HH.mm.ss");
				String ssfile = baseScreenshot + "Screenshot_"
						+ util.getSpreadsheet() + "_" + util.getWorksheet()
						+ "_" + util.getTestCase() + "_"
						+ dateFormat.format(new Date()) + ".png";
				try {
					takeScreenShot(driver, ssfile);
				} catch (Exception e) {
					returnMessage[1] = "Error:  Unable to create a SCREENSHOT";
					break;
				}
				returnMessage[1] = util.getSuccessString();
				break;
			default :
				// error
				throw new AutomationDriverException("Error: " + operation
						+ " is not a valid Keyword");
		}
		endtime = new Date().getTime();
		returnMessage[0] = Float.toString(util.calcEt(endtime, starttime,
				millisec));
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
	WebElement findElement(By by) {
		WebElement elem = driver.findElement(by);
		// draw a border around the found element
		if (driver instanceof JavascriptExecutor) {
			((JavascriptExecutor) driver).executeScript(
					"arguments[0].style.border='3px solid yellow'", elem);
		}
		return elem;
	}
	
}
