package raysullivan.unitTest;

import static org.fest.assertions.api.Assertions.assertThat;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import raysullivan.operation.AdException;
import raysullivan.operation.AdReadProperties;

public class TestAdBrowserProperties {
	private AdReadProperties object = new AdReadProperties();
	
	@Test(description = "testPropertyFile", dataProvider = "getPropertyFileName", enabled = true)
	public void testPropertyFile(String propertyFileName, String resultString) throws IOException, AdException {
		Properties p = new Properties();
		p = object.getObjectRepository(propertyFileName);
		assertThat(p.toString()).isEqualTo(resultString);
	}

	@Test(description = "testFileNotFound", dataProvider = "propertyFileNotFound", expectedExceptions = AdException.class, enabled = true)
	public void testFileNotFound(String propertyFileName) throws IOException, AdException {
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
