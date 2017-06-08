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
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdKeywords {
	private static AdUtil util = new AdUtil();
	private static AdBrowser browser = new AdBrowser();
	private static final int millisec = 1000;
	private static Set<?> setOfOldHandles = null;

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
			browser.findElement(AdGetPropertyAttribute.getObject(p, objectName, propertyName)).click();
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
			checkbox = browser.findElement(AdGetPropertyAttribute.getObject(p, objectName, propertyName));
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
			checkbox = browser.findElement(AdGetPropertyAttribute.getObject(p, objectName, propertyName));
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
			browser.findElement(AdGetPropertyAttribute.getObject(p, objectName, propertyName)).click();
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
			browser.findElement(AdGetPropertyAttribute.getObject(p, objectName, propertyName)).submit();
			return util.getSuccessString();
		} catch (NoSuchElementException nsee) {
			return "Object " + objectName + " could not be found due to NoSuchElementException";
		} catch (StaleElementReferenceException sere) {
			return "Object " + objectName + " could not be found due to StaleElementReferenceException";
		}
	}

	public String clear(Properties p, String objectName, String propertyName) throws Exception {
		try {
			browser.findElement(AdGetPropertyAttribute.getObject(p, objectName, propertyName)).clear();
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
					.visibilityOfElementLocated(AdGetPropertyAttribute.getObject(p, objectName, propertyName)));
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
					.invisibilityOfElementLocated(AdGetPropertyAttribute.getObject(p, objectName, propertyName)));
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
			ch.clickAndHold(browser.findElement(AdGetPropertyAttribute.getObject(p, objectName, propertyName))).build()
					.perform();
			int sleep = Integer.parseInt(value);
			if (sleep <= 0) {
				return "Error:  CLICKANDHOLD duration must be a positive integer value";
			}
			Thread.sleep(sleep);
			ch.release(browser.findElement(AdGetPropertyAttribute.getObject(p, objectName, propertyName))).build()
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
		Actions a = new Actions(driver);
		try {
			a.moveToElement(browser.findElement(AdGetPropertyAttribute.getObject(p, objectName, propertyName))).build()
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
			final List<WebElement> iframes = driver.findElements(By
					.tagName("iframe"));
			if (iframes.isEmpty()) {
				return "iFrame "
						+ p.getProperty(objectName)
						+ " could not be found due to NoSuchFrameException";
			}
			for (WebElement iframe : iframes) {
				try {
					driver.switchTo().frame(iframe);
					return util.getSuccessString();
				} catch (NoSuchFrameException nsee1) {
					return "iFrame "
							+ p.getProperty(objectName)
							+ " could not be found due to NoSuchFrameException";
				}
			}
		} catch (StaleElementReferenceException sere) {
			return "iFrame "
					+ objectName
					+ " could not be found due to StaleElementReferenceException";
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
		System.out.print("Number of windows open: "
				+ setOfOldHandles.size() + " value: ");
		if (!setOfOldHandles.isEmpty()) {
			String newWindowHandle = (String) setOfOldHandles
					.iterator().next(); // here IF we have new window it
										// will shift on it
			System.out.println(newWindowHandle);
		}
		System.out.println();
		return util.getSuccessString();
	}

	public String listFrames(WebDriver driver) throws Exception {
		final List<WebElement> iframes = driver.findElements(By
				.tagName("iframe"));
		System.out.println("Number of frames on this page: "
				+ iframes.size());
		for (WebElement iframe : iframes) {
			System.out.println("Page has iFrame " + iframe);
		}
		return util.getSuccessString();
	}

	public String scrollTo(Properties p, String objectName, String propertyName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String navForward(Properties p, String objectName, String propertyName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String navBackward(Properties p, String objectName, String propertyName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String setText(Properties p, String objectName, String propertyName, String value, String variable,
			String operation, AdVariable var2) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String select(Properties p, String objectName, String propertyName, String value, String variable,
			String operation, AdVariable var2) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String deselect(Properties p, String objectName, String propertyName, String value, String variable,
			String operation, AdVariable var2) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String selectByIndex(Properties p, String objectName, String propertyName, String value, String variable,
			String operation, AdVariable var2) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String deselectByIndex(Properties p, String objectName, String propertyName, String value, String variable,
			String operation, AdVariable var2) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String gotoAddress(Properties p, String objectName, String propertyName, String value, String variable,
			String operation, AdVariable var2) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String assertUrl(String objectName, String value, String variable, String operation, AdVariable var2)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String assertText(Properties p, String objectName, String propertyName, String value, String variable,
			String operation, AdVariable var2) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String createVar(String value, String variable, String operation, AdVariable var2) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String containsText(Properties p, String objectName, String propertyName, String value, String variable,
			String operation, AdVariable var2) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String storeText(Properties p, String objectName, String propertyName, String value, String variable,
			String operation, AdVariable var2) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String assertAttribute(Properties p, String objectName, String propertyName, String value, String variable,
			String valueType, String operation, AdVariable var2) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
