package raysullivan.operation;

import java.util.Properties;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

public class AutomationDriverKeywords {
	private static AutomationDriverVariable var = null;
	private static AutomationDriverUtil util = new AutomationDriverUtil();
	private static AutomationDriverBrowser browser = new AutomationDriverBrowser(null, null);

	public static String[] validateAssertVariables(String vName1, String vName2, String operation)
			throws AutomationDriverException {
		var = new AutomationDriverVariable();
		String[] variableNames = { var.validateVariableName(vName1), var.validateVariableName(vName2) };
		return variableNames;
	}

	public String assertEqual(String variableName1, String variableName2,  AutomationDriverVariable var)
			throws AutomationDriverException {
		if (var.getVariableValue(variableName1).equals(var.getVariableValue(variableName2))) {
			return util.getSuccessString();
		} else {
			return util.getErrorString();
		}
	}

	public String assertNotEqual(String variableName1, String variableName2, AutomationDriverVariable var)
			throws AutomationDriverException {
		if (!var.getVariableValue(variableName1).equals(var.getVariableValue(variableName2))) {
			return util.getSuccessString();
		} else {
			return util.getErrorString();
		}
	}

	public String assertContains(String variableName1, String variableName2, AutomationDriverVariable var)
			throws AutomationDriverException {
		if (var.getVariableValue(variableName1).contains(var.getVariableValue(variableName2))) {
			return util.getSuccessString();
		} else {
			return util.getErrorString();
		}
	}

	public String assertNotContains(String variableName1, String variableName2, AutomationDriverVariable var)
			throws AutomationDriverException {
		if (!var.getVariableValue(variableName1).contains(var.getVariableValue(variableName2))) {
			return util.getSuccessString();
		} else {
			return util.getErrorString();
		}
	}

	public String[] click(Properties p, String objectName, String propertyName, String[] returnMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] clickOn(Properties p, String objectName, String propertyName, WebElement checkbox,
			String[] returnMessage) throws Exception {
		try {
			checkbox = browser.findElement(AutomationDriverGetPropertyAttribute.getObject(p, objectName,
					propertyName));
			checkbox.click();
			if (checkbox.isSelected() == true) {
				returnMessage[1] = util.getSuccessString();
			}
		} catch (NoSuchElementException nsee) {
			returnMessage[1] = "Object " + objectName
					+ " could not be found due to NoSuchElementException";
		} catch (StaleElementReferenceException sere) {
			returnMessage[1] = "Object "
					+ objectName
					+ " could not be found due to StaleElementReferenceException";
		} catch (ElementNotVisibleException enve) {
			returnMessage[1] = "Object " + objectName
					+ " could not be found due to ElementNotVisibleException";
		} catch (WebDriverException we) {
			returnMessage[1] = "Object "
					+ objectName
					+ " could not be clicked on due to WebDriverException: Element is not clickable";
		}
		return returnMessage;
	}

	public String[] clickOff(Properties p, String objectName, String propertyName, WebElement checkbox,
			String[] returnMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] clickNoAssert(Properties p, String objectName, String propertyName, String[] returnMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] clickAlert(String[] returnMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] pause(String value, String[] returnMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] refresh(String[] returnMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] submit(Properties p, String objectName, String propertyName, String[] returnMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] clear(Properties p, String objectName, String propertyName, String[] returnMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] gotoUrl(Properties p, String objectName, String propertyName, String[] returnMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] waitVisible(Properties p, String objectName, String propertyName, String[] returnMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] waitInvisible(Properties p, String objectName, String propertyName, String[] returnMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] clickAndHold(Properties p, String objectName, String propertyName, String value,
			String[] returnMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] hover(Properties p, String objectName, String propertyName, String[] returnMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] switchFrame(Properties p, String objectName, String propertyName, String[] returnMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] listWindows(String[] returnMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] listFrames(String[] returnMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] scrollTo(Properties p, String objectName, String propertyName, String[] returnMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] navForward(Properties p, String objectName, String propertyName, String[] returnMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] navBackward(Properties p, String objectName, String propertyName, String[] returnMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] setText(Properties p, String objectName, String propertyName, String value, String variable,
			String operation, AutomationDriverVariable var2, String[] createVariable, String[] returnMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] select(Properties p, String objectName, String propertyName, String value, String variable,
			String operation, AutomationDriverVariable var2, String[] createVariable, String[] returnMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] deselect(Properties p, String objectName, String propertyName, String value, String variable,
			String operation, AutomationDriverVariable var2, String[] createVariable, String[] returnMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] selectByIndex(Properties p, String objectName, String propertyName, String value, String variable,
			String operation, AutomationDriverVariable var2, String[] createVariable, String[] returnMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] deselectByIndex(Properties p, String objectName, String propertyName, String value, String variable,
			String operation, AutomationDriverVariable var2, String[] createVariable, String[] returnMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] gotoAddress(Properties p, String objectName, String propertyName, String value, String variable,
			String operation, AutomationDriverVariable var2, String[] createVariable, String[] returnMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] assertUrl(String objectName, String value, String variable, String operation,
			AutomationDriverVariable var2, String[] createVariable, String[] returnMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] assertText(Properties p, String objectName, String propertyName, String value, String variable,
			String operation, AutomationDriverVariable var2, String[] createVariable, String[] returnMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] createVar(String value, String variable, String operation, AutomationDriverVariable var2,
			String[] createVariable, String[] returnMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] containsText(Properties p, String objectName, String propertyName, String value, String variable,
			String operation, AutomationDriverVariable var2, String[] createVariable, String[] returnMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] storeText(Properties p, String objectName, String propertyName, String value, String variable,
			String operation, AutomationDriverVariable var2, String[] createVariable, String[] returnMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] assertAttribute(Properties p, String objectName, String propertyName, String value, String variable,
			String valueType, String operation, AutomationDriverVariable var2, String[] createVariable,
			String[] returnMessage) {
		// TODO Auto-generated method stub
		return null;
	}

}
