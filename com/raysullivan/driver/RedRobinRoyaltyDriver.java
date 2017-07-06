package raysullivan.driver;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import raysullivan.dataProvider.RedRobinRoyaltyDataprovider;
import raysullivan.operation.AdTestDriver;

@Test(dataProvider = "redRobinRoyaltyDataProvider", dataProviderClass = RedRobinRoyaltyDataprovider.class, description = "RedRobinRoyaltyDriver")
public class RedRobinRoyaltyDriver extends AdTestDriver {

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("Beginning Test Suite RedRobinRoyaltyDriver");
	}
}
