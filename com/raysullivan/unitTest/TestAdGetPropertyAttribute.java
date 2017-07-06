package raysullivan.unitTest;

import org.testng.annotations.Test;

import raysullivan.operation.AdReadProperties;
import raysullivan.operation.AdException;
import raysullivan.operation.AdGetPropertyAttribute;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.Properties;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;

public class TestAdGetPropertyAttribute {
	private AdReadProperties object = new AdReadProperties();
	private AdGetPropertyAttribute pa = new AdGetPropertyAttribute();
	Properties p = mock(Properties.class);

	@Test(dataProvider = "validPropertyName", description = "getPropertyValid", enabled = true)
	public void getPropertyValid(String propertyFileName, String objectName, String resultString) throws Exception {
		p = object.getObjectRepository(propertyFileName);
		By b = pa.getObject(p, objectName, propertyFileName);
		assertThat(b.toString()).isEqualTo(resultString);
	}

	@Test(dataProvider = "invalidPropertyName", description = "getPropertyInvalid", expectedExceptions = AdException.class, enabled = true)
	public void getPropertyInvalid(String propertyFileName, String objectName) throws Exception {
		p = object.getObjectRepository(propertyFileName);
		pa.getObject(p, objectName, propertyFileName);
	}

	@DataProvider
	public Object[][] validPropertyName() {
		return new Object[][] {
				new Object[] { "Test.Properties", "mainLogo", "By.cssSelector: img[alt=\"Red Robin Logo\"]" },
				new Object[] { "Test.properties", "changeLocation", "By.linkText: Change Location" },
				new Object[] { "Test.properties", "menuLanding", "By.id: pageTitle" },
				new Object[] { "Test.properties", "locSearchBox", "By.className: changeLocationInput" },
				new Object[] { "Test.properties", "locFindButton", "By.tagName: findBtn" },
				new Object[] { "Test.properties", "locationMoreInfo", "By.name: toggleMoreLocationInfo" },
				new Object[] { "Test.properties", "locationDistance", "By.partialLinkText: div.distance" },
				new Object[] { "Test.properties", "upperIconInstagram",
						"By.xpath: //div[3]/div[1]/div[2]/div[2]/ul/li[1]/a" }, };
	}

	@DataProvider
	public Object[][] invalidPropertyName() {
		return new Object[][] { new Object[] { "Test.Properties", "failedNoBy" },
				new Object[] { "Test.properties", "failedIncorrectType" },
				new Object[] { "Test.Properties", "missing" }, new Object[] { "Test.Properties", null },
				new Object[] { "Test.Properties", "" }, };
	}
}
