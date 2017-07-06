package raysullivan.driver;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import raysullivan.dataProvider.RedRobinComDataprovider;
import raysullivan.operation.AdTestDriver;

@Test(dataProvider = "redRobinComDataProvider", dataProviderClass = RedRobinComDataprovider.class, description = "RedRobinComDriver")
public class RedRobinComDriver extends AdTestDriver { 

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("Beginning Test Suite RedRobinComDriver");
	}
}
