package raysullivan.driver;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import raysullivan.dataProvider.WebrootConsumerDataprovider;
import raysullivan.operation.AdTestDriver;

@Test(dataProvider = "webrootConsumerDataProvider", dataProviderClass = WebrootConsumerDataprovider.class, description = "WebrootConsumerDriver")
public class WebrootConsumerDriver extends AdTestDriver {

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("Beginning Test Suite WebrootConsumerDriver");
	}
}
