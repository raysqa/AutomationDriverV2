package raysullivan.operation;

import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnsupportedCommandException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdKeywords {
	private AdUtil util = new AdUtil();
	private AdGetPropertyAttribute pa = new AdGetPropertyAttribute();
	private static final int millisec = 1000;
	private Set<?> setOfOldHandles = null;
	private WebDriver driver;
	private Wait<WebDriver> wait;
	private AdBrowser browser = new AdBrowser(driver, wait);

	public String assertEqual(String variableName1, String variableName2, AdVariable var) throws Exception {
		if (var.getVariableValue(variableName1).equals(var.getVariableValue(variableName2))) {
			return util.getSuccessString();
		} else {
			return util.getErrorString();
		}
	}

	public String assertNotEqual(String variableName1, String variableName2, AdVariable var) throws Exception {
		if (!var.getVariableValue(variableName1).equals(var.getVariableValue(variableName2))) {
			return util.getSuccessString();
		} else {
			return util.getErrorString();
		}
	}

	public String assertContains(String variableName1, String variableName2, AdVariable var) throws Exception {
		if (var.getVariableValue(variableName1).contains(var.getVariableValue(variableName2))) {
			return util.getSuccessString();
		} else {
			return util.getErrorString();
		}
	}

	public String assertNotContains(String variableName1, String variableName2, AdVariable var) throws Exception {
		if (!var.getVariableValue(variableName1).contains(var.getVariableValue(variableName2))) {
			return util.getSuccessString();
		} else {
			return util.getErrorString();
		}
	}

	public String click(Properties p, String objectName, String propertyName) throws Exception {
		try {
			browser.findElement(pa.getObject(p, objectName, propertyName)).click();
			return util.getSuccessString();
		} catch (NoSuchElementException nsee) {
			return "Object " + objectName + " could not be found due to a NoSuchElementException";
		} catch (StaleElementReferenceException sere) {
			return "Object " + objectName + " could not be found due to StaleElementReferenceException";
		} catch (ElementNotVisibleException enve) {
			return "Object " + objectName + " could not be found due to ElementNotVisibleException";
		} catch (WebDriverException we) {
			return "Object " + objectName + " could not be clicked due to WebDriverException: Element is not clickable";
		}
	}

	public String clickOn(Properties p, String objectName, String propertyName, WebElement checkbox) throws Exception {
		try {
			checkbox = browser.findElement(pa.getObject(p, objectName, propertyName));
			checkbox.click();
			if (checkbox.isSelected() == true) {
				return util.getSuccessString();
			} else {
				return util.getErrorString();
			}
		} catch (NoSuchElementException nsee) {
			return "Object " + objectName + " could not be found due to NoSuchElementException";
		} catch (StaleElementReferenceException sere) {
			return "Object " + objectName + " could not be found due to StaleElementReferenceException";
		} catch (ElementNotVisibleException enve) {
			return "Object " + objectName + " could not be found due to ElementNotVisibleException";
		} catch (WebDriverException we) {
			return "Object " + objectName
					+ " could not be clicked on due to WebDriverException: Element is not clickable";
		}

	}

	public String clickOff(Properties p, String objectName, String propertyName, WebElement checkbox) throws Exception {
		try {
			checkbox = browser.findElement(pa.getObject(p, objectName, propertyName));
			checkbox.click();
			if (checkbox.isSelected() == true) {
				return util.getSuccessString();
			} else {
				return util.getErrorString();
			}
		} catch (NoSuchElementException nsee) {
			return "Object " + objectName + " could not be found due to NoSuchElementException";
		} catch (StaleElementReferenceException sere) {
			return "Object " + objectName + " could not be found due to StaleElementReferenceException";
		} catch (ElementNotVisibleException enve) {
			return "Object " + objectName + " could not be found due to ElementNotVisibleException";
		} catch (WebDriverException we) {
			return "Object " + objectName
					+ " could not be clicked on due to WebDriverException: Element is not clickable";
		}
	}

	public String clickNoAssert(Properties p, String objectName, String propertyName) throws Exception {
		try {
			browser.findElement(pa.getObject(p, objectName, propertyName)).click();
		} catch (Exception e) {
		}
		return util.getSuccessString();
	}

	public String clickAlert(WebDriver driver) throws Exception {
		try {
			driver.switchTo().alert().accept();
		} catch (NoAlertPresentException npe) {
			return "Error: No alert is present.";
		}
		return util.getSuccessString();
	}

	public String pause(String value) throws Exception {
		try {
			int sleep = millisec * Integer.parseInt(value);
			if (sleep <= 0) {
				throw new AdException("Error:  PAUSE duration must be a positive integer value");
			}
			Thread.sleep(sleep);
			return util.getSuccessString();
		} catch (NumberFormatException nfe) {
			throw new AdException("Error:  PAUSE duration must be a positive integer value");
		}
	}

	public String reload(WebDriver driver) throws Exception {
		try {
			((JavascriptExecutor) driver).executeScript("document.location.reload()");
			Thread.sleep(2000);
			return util.getSuccessString();
		} catch (Exception er) {
			return "Error: unable to refresh the current page.";
		}
	}

	public String submit(Properties p, String objectName, String propertyName) throws Exception {
		try {
			browser.findElement(pa.getObject(p, objectName, propertyName)).submit();
			return util.getSuccessString();
		} catch (NoSuchElementException nsee) {
			return "Object " + objectName + " could not be found due to NoSuchElementException";
		} catch (StaleElementReferenceException sere) {
			return "Object " + objectName + " could not be found due to StaleElementReferenceException";
		}
	}

	public String clear(Properties p, String objectName, String propertyName) throws Exception {
		try {
			browser.findElement(pa.getObject(p, objectName, propertyName)).clear();
			return util.getSuccessString();
		} catch (NoSuchElementException nsee) {
			return "Object " + objectName + " could not be found due to NoSuchElementException";
		} catch (StaleElementReferenceException sere) {
			return "Object " + objectName + " could not be found due to StaleElementReferenceException";
		} catch (ElementNotVisibleException enve) {
			return "Object " + objectName + " could not be found due to ElementNotVisibleException";
		}
	}

	public String gotoUrl(Properties p, String objectName, String propertyName, WebDriver driver, String value)
			throws Exception {
		try {
			driver.get(p.getProperty(value));
		} catch (NullPointerException e) {
			return "Error: URL property " + value + " not found in property file " + propertyName;
		}
		return util.getSuccessString();

	}

	public String waitVisible(Properties p, String objectName, String propertyName, WebDriverWait waitVar)
			throws Exception {
		try {
			waitVar.until(ExpectedConditions
					.visibilityOfElementLocated(pa.getObject(p, objectName, propertyName)));
			return util.getSuccessString();
		} catch (TimeoutException te) {
			return "Object " + objectName + " did not become visible within the default timeout duration";

		} catch (NoSuchElementException nsee) {
			return "Object " + objectName + " could not be found due to NoSuchElementException";
		} catch (StaleElementReferenceException sere) {
			return "Object " + objectName + " could not be found due to StaleElementReferenceException";
		}

	}

	public String waitInvisible(Properties p, String objectName, String propertyName, WebDriverWait waitVar)
			throws Exception {
		try {
			waitVar.until(ExpectedConditions
					.invisibilityOfElementLocated(pa.getObject(p, objectName, propertyName)));
			return util.getSuccessString();
		} catch (TimeoutException te) {
			return "Object " + objectName + " did not become visible within the default timeout duration";

		} catch (NoSuchElementException nsee) {
			return "Object " + objectName + " could not be found due to NoSuchElementException";
		} catch (StaleElementReferenceException sere) {
			return "Object " + objectName + " could not be found due to StaleElementReferenceException";
		}

	}

	public String clickAndHold(Properties p, String objectName, String propertyName, String value, WebDriver driver)
			throws Exception {
		Actions ch = new Actions(driver);
		try {
			ch.clickAndHold(browser.findElement(pa.getObject(p, objectName, propertyName))).build()
					.perform();
			int sleep = Integer.parseInt(value);
			if (sleep <= 0) {
				return "Error:  CLICKANDHOLD duration must be a positive integer value";
			}
			Thread.sleep(sleep);
			ch.release(browser.findElement(pa.getObject(p, objectName, propertyName))).build()
					.perform();
			return util.getSuccessString();
		} catch (NoSuchElementException nsee) {
			return "Object " + objectName + " could not be found due to NoSuchElementException";
		} catch (StaleElementReferenceException sere) {
			return "Object " + objectName + " could not be found due to StaleElementReferenceException";
		} catch (ElementNotVisibleException enve) {
			return "Object " + objectName + " could not be found due to ElementNotVisibleException";
		} catch (MoveTargetOutOfBoundsException mtoobe) {
			return "Object " + objectName + " could not be found due to MoveTargetOutOfBoundsException";
		} catch (UnsupportedCommandException uce) {
			return "CLICKANDHOLD action on object " + objectName
					+ " not supported on this version of Selenium WebDriver";
			// rs hover action not supported by Selenium Webdriver 3.2.0 - see
			// mozilla bug
			// rs https://bugzilla.mozilla.org/show_bug.cgi?id=1292178
		}
	}

	public String hover(Properties p, String objectName, String propertyName, WebDriver driver) throws Exception {
		// rs hover action not supported by Selenium Webdriver 3.2.0 - see
		// mozilla bug
		// rs https://bugzilla.mozilla.org/show_bug.cgi?id=1292178
		Actions h = new Actions(driver);
		try {
			h.moveToElement(browser.findElement(pa.getObject(p, objectName, propertyName))).build()
					.perform();
			return util.getSuccessString();
		} catch (NoSuchElementException nsee) {
			return "Object " + objectName + " could not be found due to NoSuchElementException";
		} catch (StaleElementReferenceException sere) {
			return "Object " + objectName + " could not be found due to StaleElementReferenceException";
		} catch (ElementNotVisibleException enve) {
			return "Object " + objectName + " could not be found due to ElementNotVisibleException";
		} catch (MoveTargetOutOfBoundsException mtoobe) {
			return "Object " + objectName + " could not be found due to MoveTargetOutOfBoundsException";
		} catch (UnsupportedCommandException uce) {
			return "HOVER action on object " + objectName + " not supported on this version of Selenium WebDriver";
			// rs hover action not supported by Selenium Webdriver 3.2.0 - see
			// mozilla bug
			// rs https://bugzilla.mozilla.org/show_bug.cgi?id=1292178
		} catch (InvalidArgumentException iae) {
			return "HOVER action on object " + objectName + " could not be performed due to InvalidArgumentException";
			// rs hover action not supported by Selenium Webdriver 3.2.0 - see
			// mozilla bug
			// rs https://bugzilla.mozilla.org/show_bug.cgi?id=1292178
		}

	}

	public String switchFrame(Properties p, String objectName, String propertyName, WebDriver driver) throws Exception {
		try {
			if (objectName.length() == 0) {
				driver.switchTo().defaultContent();
			} else {
				driver.switchTo().frame(p.getProperty(objectName));
			}
			return util.getSuccessString();
		} catch (NoSuchFrameException nsee) {
			final List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
			if (iframes.isEmpty()) {
				return "iFrame " + p.getProperty(objectName) + " could not be found due to NoSuchFrameException";
			}
			for (WebElement iframe : iframes) {
				try {
					driver.switchTo().frame(iframe);
					return util.getSuccessString();
				} catch (NoSuchFrameException nsee1) {
					return "iFrame " + p.getProperty(objectName) + " could not be found due to NoSuchFrameException";
				}
			}
		} catch (StaleElementReferenceException sere) {
			return "iFrame " + objectName + " could not be found due to StaleElementReferenceException";
		}
		return util.getSuccessString();
	}

	public String listWindows(WebDriver driver) throws Exception {
		if (setOfOldHandles != null) {
			setOfOldHandles.clear();
		}
		setOfOldHandles = driver.getWindowHandles(); // here we save
														// id's of
														// windows
		System.out.print("Number of windows open: " + setOfOldHandles.size() + " value: ");
		if (!setOfOldHandles.isEmpty()) {
			// here if we have a new window it will shift to it
			String newWindowHandle = (String) setOfOldHandles.iterator().next();
			System.out.println(newWindowHandle);
		}
		System.out.println();
		return util.getSuccessString();
	}

	public String listFrames(WebDriver driver) throws Exception {
		final List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
		System.out.println("Number of frames on this page: " + iframes.size());
		for (WebElement iframe : iframes) {
			System.out.println("Page has iFrame " + iframe);
		}
		return util.getSuccessString();
	}

	public String scrollTo(Properties p, String objectName, String propertyName, WebDriver driver) throws Exception {
		try {
			WebElement scroll = browser.findElement(pa.getObject(p, objectName, propertyName));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
			Thread.sleep(500);
			return util.getSuccessString();
		} catch (TimeoutException te) {
			return "Object " + objectName + " failed to become invisible";
		} catch (NoSuchElementException nsee) {
			return "Object " + objectName + " could not be found due to NoSuchElementException";
		} catch (StaleElementReferenceException sere) {
			return "Object " + objectName + " could not be found due to StaleElementReferenceException";
		}
	}

	public String navForward(WebDriver driver) throws Exception {
		driver.navigate().forward();
		return util.getSuccessString();
	}

	public String navBackward(WebDriver driver) throws Exception {
		driver.navigate().back();
		return util.getSuccessString();
	}

	public String setText(Properties p, String objectName, String propertyName, String value, String variable,
			String valueType, AdVariable var) throws Exception {
		value = util.validateValType("SETTEXT", value, valueType);
		if (variable != null && variable != "") {
			var.setVariableValue(value, variable);
		}
		try {
			browser.findElement(pa.getObject(p, objectName, propertyName)).sendKeys(value);
			return util.getSuccessString();
		} catch (NoSuchElementException nsee) {
			return "Object " + objectName + " could not be found due to NoSuchElementException";
		} catch (StaleElementReferenceException sere) {
			return "Object " + objectName + " could not be found due to StaleElementReferenceException";
		} catch (ElementNotVisibleException enve) {
			return "Object " + objectName + " could not be found due to ElementNotVisibleException";
		}
	}

	public String select(Properties p, String objectName, String propertyName, String value, String variable,
			String valueType, AdVariable var) throws Exception {
		value = util.validateValType("SELECT", value, valueType);
		if (variable != null && variable != "") {
			var.setVariableValue(value, variable);
		}
		try {
			Select drpObject = new Select(
					browser.findElement(pa.getObject(p, objectName, propertyName)));
			drpObject.selectByVisibleText(value);
			return util.getSuccessString();
		} catch (NoSuchElementException nsee) {
			return "Object " + objectName + " could not be found due to NoSuchElementException";
		} catch (StaleElementReferenceException sere) {
			return "Object " + objectName + " could not be found due to StaleElementReferenceException";
		} catch (ElementNotVisibleException enve) {
			return "Object " + objectName + " could not be found due to ElementNotVisibleException";
		}
	}

	public String deselect(Properties p, String objectName, String propertyName, String value, String variable,
			String valueType, AdVariable var) throws Exception {
		value = util.validateValType("SELECT", value, valueType);
		if (variable != null && variable != "") {
			var.setVariableValue(value, variable);
		}
		try {
			Select drpObject = new Select(
					browser.findElement(pa.getObject(p, objectName, propertyName)));
			drpObject.deselectByVisibleText(value);
			return util.getSuccessString();
		} catch (NoSuchElementException nsee) {
			return "Object " + objectName + " could not be found due to NoSuchElementException";
		} catch (StaleElementReferenceException sere) {
			return "Object " + objectName + " could not be found due to StaleElementReferenceException";
		} catch (ElementNotVisibleException enve) {
			return "Object " + objectName + " could not be found due to ElementNotVisibleException";
		}
	}

	public String selectByIndex(Properties p, String objectName, String propertyName, String value, String variable,
			String valueType, String operation, AdVariable var) throws Exception {
		value = util.validateValType(operation.toUpperCase(), value, valueType);
		if (variable != null && variable != "") {
			var.setVariableValue(value, variable);
		}
		try {
			Select drpObject = new Select(
					browser.findElement(pa.getObject(p, objectName, propertyName)));
			int selectIdx = Integer.parseInt(value);
			drpObject.selectByIndex(selectIdx);
			return util.getSuccessString();
		} catch (NoSuchElementException nsee) {
			return "Object " + objectName + " could not be found due to NoSuchElementException";
		} catch (StaleElementReferenceException sere) {
			return "Object " + objectName + " could not be found due to StaleElementReferenceException";
		} catch (ElementNotVisibleException enve) {
			return "Object " + objectName + " could not be found due to ElementNotVisibleException";
		}
	}

	public String deselectByIndex(Properties p, String objectName, String propertyName, String value, String variable,
			String valueType, String operation, AdVariable var) throws Exception {
		value = util.validateValType(operation.toUpperCase(), value, valueType);
		if (variable != null && variable != "") {
			var.setVariableValue(value, variable);
		}
		try {
			Select drpObject = new Select(
					browser.findElement(pa.getObject(p, objectName, propertyName)));
			int selectIdx = Integer.parseInt(value);
			drpObject.deselectByIndex(selectIdx);
			return util.getSuccessString();
		} catch (NoSuchElementException nsee) {
			return "Object " + objectName + " could not be found due to NoSuchElementException";
		} catch (StaleElementReferenceException sere) {
			return "Object " + objectName + " could not be found due to StaleElementReferenceException";
		} catch (ElementNotVisibleException enve) {
			return "Object " + objectName + " could not be found due to ElementNotVisibleException";
		}
	}

	public String gotoAddress(Properties p, String objectName, String propertyName, String value, String variable,
			String valueType, String operation, AdVariable var, WebDriver driver) throws Exception {
		value = util.validateValType(operation.toUpperCase(), value, valueType);
		if (variable != null && variable != "") {
			var.setVariableValue(value, variable);
		}
		driver.get(value);
		return util.getSuccessString();
	}

	public String assertUrl(String objectName, String value, String variable, String valueType, String operation,
			AdVariable var, WebDriver driver) throws Exception {
		// clean up value type
		value = variableHandler(operation.toUpperCase(), value, variable, valueType, var);
		try {
			if (driver.getCurrentUrl().equals(value)) {
				return util.getSuccessString();
			} else {
				return util.getErrorString();
			}
		} catch (NoSuchElementException nsee) {
			return "Object " + objectName + " could not be found due to NoSuchElementException";
		} catch (StaleElementReferenceException sere) {
			return "Object " + objectName + " could not be found due to StaleElementReferenceException";
		}
	}

	public String createVar(String value, String variable, String valueType, String operation, AdVariable var)
			throws Exception {
		if (!var.isAVariable(variable)) {
			return "CREATEVARIABLE invalid: variable not declared.";
		} else if (!var.isAVariable(value)) {
			value = variableHandler(operation.toUpperCase(), value, variable, valueType, var);
			return util.getSuccessString();
		} else {
			return "CREATEVARIABLE invalid: Value cannot be a variable.";
		}
	}

	public String containsText(Properties p, String objectName, String propertyName, String value, String variable,
			String valueType, String operation, AdVariable var) throws Exception {
		value = variableHandler(operation.toUpperCase(), value, variable, valueType, var);
		if (variable != null && variable != "") {
			var.setVariableValue(value, variable);
		}
		return null;
	}

	public String storeText(Properties p, String objectName, String propertyName, String value, String variable,
			String operation, AdVariable var) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String assertAttribute(Properties p, String objectName, String propertyName, String value, String variable,
			String valueType, String operation, AdVariable var) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String assertText(Properties p, String objectName, String propertyName, String value, String variable,
			String valueType, String variable2, AdVariable var) {
		// TODO Auto-generated method stub
		return null;
	}

	public String variableHandler(String keyword, String value, String variable, String valueType, AdVariable var)
			throws Exception, AdException {
		value = util.validateValType(keyword, value, valueType);
		// is the validation against a variable?
		if (var.isAVariable(value)) {
			System.out.println("VariableHandler:  Variable " + value + " set to " + var.getVariableValue(value));
			value = var.getVariableValue(value);
		}
		// create variable if passed
		if (var.isAVariable(variable)) {
			var.setVariableValue(variable, value);
		}
		// is the validation against a variable?
		return value;
	}
}
