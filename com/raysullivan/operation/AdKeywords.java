package raysullivan.operation;

import java.util.Properties;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

public class AdKeywords {
	private static AdVariable var = null;
	private static AdUtil util = new AdUtil();
	private static AdBrowser browser = new AdBrowser(null, null);

	public static String[] validateAssertVariables(String vName1, String vName2, String operation)
			throws AdException {
		var = new AdVariable();
		String[] variableNames = { var.validateVariableName(vName1), var.validateVariableName(vName2) };
		return variableNames;
	}

	public String assertEqual(String variableName1, String variableName2, AdVariable var)
			throws AdException {
		if (var.getVariableValue(variableName1).equals(var.getVariableValue(variableName2))) {
			return util.getSuccessString();
		} else {
			return util.getErrorString();
		}
	}

	public String assertNotEqual(String variableName1, String variableName2, AdVariable var)
			throws AdException {
		if (!var.getVariableValue(variableName1).equals(var.getVariableValue(variableName2))) {
			return util.getSuccessString();
		} else {
			return util.getErrorString();
		}
	}

	public String assertContains(String variableName1, String variableName2, AdVariable var)
			throws AdException {
		if (var.getVariableValue(variableName1).contains(var.getVariableValue(variableName2))) {
			return util.getSuccessString();
		} else {
			return util.getErrorString();
		}
	}

	public String assertNotContains(String variableName1, String variableName2, AdVariable var)
			throws AdException {
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

	public String clickOff(Properties p, String objectName, String propertyName, WebElement checkbox) {
		// TODO Auto-generated method stub
		return null;
	}

	public String clickNoAssert(Properties p, String objectName, String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	public String clickAlert() {
		// TODO Auto-generated method stub
		return null;
	}

	public String pause(String value) {
		// TODO Auto-generated method stub
		return null;
	}

	public String refresh() {
		// TODO Auto-generated method stub
		return null;
	}

	public String submit(Properties p, String objectName, String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	public String clear(Properties p, String objectName, String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	public String gotoUrl(Properties p, String objectName, String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	public String waitVisible(Properties p, String objectName, String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	public String waitInvisible(Properties p, String objectName, String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	public String clickAndHold(Properties p, String objectName, String propertyName, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	public String hover(Properties p, String objectName, String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	public String switchFrame(Properties p, String objectName, String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	public String listWindows() {
		// TODO Auto-generated method stub
		return null;
	}

	public String listFrames() {
		// TODO Auto-generated method stub
		return null;
	}

	public String scrollTo(Properties p, String objectName, String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	public String navForward(Properties p, String objectName, String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	public String navBackward(Properties p, String objectName, String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	public String setText(Properties p, String objectName, String propertyName, String value, String variable,
			String operation, AdVariable var2) {
		// TODO Auto-generated method stub
		return null;
	}

	public String select(Properties p, String objectName, String propertyName, String value, String variable,
			String operation, AdVariable var2) {
		// TODO Auto-generated method stub
		return null;
	}

	public String deselect(Properties p, String objectName, String propertyName, String value, String variable,
			String operation, AdVariable var2) {
		// TODO Auto-generated method stub
		return null;
	}

	public String selectByIndex(Properties p, String objectName, String propertyName, String value, String variable,
			String operation, AdVariable var2) {
		// TODO Auto-generated method stub
		return null;
	}

	public String deselectByIndex(Properties p, String objectName, String propertyName, String value, String variable,
			String operation, AdVariable var2) {
		// TODO Auto-generated method stub
		return null;
	}

	public String gotoAddress(Properties p, String objectName, String propertyName, String value, String variable,
			String operation, AdVariable var2) {
		// TODO Auto-generated method stub
		return null;
	}

	public String assertUrl(String objectName, String value, String variable, String operation,
			AdVariable var2) {
		// TODO Auto-generated method stub
		return null;
	}

	public String assertText(Properties p, String objectName, String propertyName, String value, String variable,
			String operation, AdVariable var2) {
		// TODO Auto-generated method stub
		return null;
	}

	public String createVar(String value, String variable, String operation, AdVariable var2) {
		// TODO Auto-generated method stub
		return null;
	}

	public String containsText(Properties p, String objectName, String propertyName, String value, String variable,
			String operation, AdVariable var2) {
		// TODO Auto-generated method stub
		return null;
	}

	public String storeText(Properties p, String objectName, String propertyName, String value, String variable,
			String operation, AdVariable var2) {
		// TODO Auto-generated method stub
		return null;
	}

	public String assertAttribute(Properties p, String objectName, String propertyName, String value, String variable,
			String valueType, String operation, AdVariable var2) {
		// TODO Auto-generated method stub
		return null;
	}

}
