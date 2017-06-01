package raysullivan.operation;

import java.util.Properties;

import org.openqa.selenium.By;

public class AutomationDriverGetPropertyAttribute {
	private static AutomationDriverUtil util = new AutomationDriverUtil();

	public static By getObject(Properties p, String objectName, String propertyName) throws Exception {
		String objectType = "none";
		String newObjectName = null;
		String propDelimiter = util.getPropertyDelimiter();
		try {
			newObjectName = p.getProperty(objectName).substring(0, p.getProperty(objectName).indexOf(propDelimiter))
					.trim();
			objectType = p.getProperty(objectName).substring(p.getProperty(objectName).indexOf(propDelimiter) + 1)
					.trim();
		} catch (StringIndexOutOfBoundsException | NullPointerException e) {
			throw new AutomationDriverException(
					"Error: object '" + objectName + "' not found in property file '" + propertyName + "'.");
		}
		switch (objectType.toUpperCase()) {
		case "ID":
			return By.id(newObjectName);
		case "CLASSNAME":
			return By.className(newObjectName);
		case "TAGNAME":
			return By.tagName(newObjectName);
		case "NAME":
			return By.name(newObjectName);
		case "LINK":
			return By.linkText(newObjectName);
		case "PARTIALLINK":
			return By.partialLinkText(newObjectName);
		case "CSS":
			return By.cssSelector(newObjectName);
		case "XPATH":
			return By.xpath(newObjectName);
		default:
			throw new AutomationDriverException(
					"Error : '" + objectType + "' not a valid object type for object name '" + newObjectName + "'.");
		}
		
	}
}
