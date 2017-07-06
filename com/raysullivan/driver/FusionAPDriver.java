package raysullivan.driver;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import raysullivan.dataProvider.FusionSuiteDataprovider;
import raysullivan.operation.AdTestDriver;

@Test(dataProvider = "FusionApDataProvider", dataProviderClass = FusionSuiteDataprovider.class, description = "FusionAPDriver")
public class FusionAPDriver extends AdTestDriver{
	@BeforeSuite
	public void beforeSuite() {
		System.out.println("Beginning Test Suite FusionAPDriver");
	}
}
