package raysullivan.unitTest;

import static org.fest.assertions.api.Assertions.assertThat;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import raysullivan.operation.AutomationDriverException;
import raysullivan.operation.AutomationDriverReadProperties;

public class TestAutomationDriverBrowserProperties {
	private AutomationDriverReadProperties object = new AutomationDriverReadProperties();
	
	@Test(description = "testPropertyFile", dataProvider = "getPropertyFileName", enabled = true)
	public void testPropertyFile(String propertyFileName, String resultString) throws IOException, AutomationDriverException {
		Properties p = new Properties();
		p = object.getObjectRepository(propertyFileName);
		assertThat(p.toString()).isEqualTo(resultString);
	}

	@Test(description = "testFileNotFound", dataProvider = "propertyFileNotFound", expectedExceptions = AutomationDriverException.class, enabled = true)
	public void testFileNotFound(String propertyFileName) throws IOException, AutomationDriverException {
		object.getObjectRepository(propertyFileName);
	}

	@DataProvider
	public Object[][] getPropertyFileName() {
		return new Object[][] { new Object[] { "TestReadObject.properties", "{mainLogo=img[alt=Red Robin Logo]|css, changeLocation=Change Location|link}"}, };
	}

	@DataProvider
	public Object[][] propertyFileNotFound() {
		return new Object[][] { new Object[] { "est.properties" }, };
	}
}
