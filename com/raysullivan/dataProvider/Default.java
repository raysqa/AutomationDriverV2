package raysullivan.dataProvider;

import org.testng.annotations.DataProvider;

public class Default {
	@DataProvider

	public static Object[][] DefaultDataProvider() {
		/*
		 * P1 - spreadsheet name in resource folder 
		 * P2 - worksheet name in the spreadsheet 
		 * P3 - Test result output spreadsheet
		 * P4 - Browser (Firefox, IE, Chrome, Opera, Safari); the first browser
		 * 		in any data test case will be used for all
		 * P5 - Firefox Profile name (must exist on machine
		 * 		executing the test); default = default 
		 * P6 - default waitFor in seconds
		 * P7 - Application specific object.properties file
		 * P8 - Number of times to execute sheet; 0=skip
		 * P9 - Output to .csv (false=no, true=yes)
		 * P10 - Output to video (false=no, true=yes)
		 */
		return new Object[][] {
				{ "Spreadsheet.xlsx", "Worksheet", "OutputSpreadsheet.xlsx", "Firefox", "Profile", 30,
						"Object.properties", 1, false, false },
				{ "Spreadsheet.xlsx", "Worksheet", "OutputSpreadsheet.xlsx", "IE", "Profile", 30, "Object.properties",
						1, false, false },
				{ "Spreadsheet.xlsx", "Worksheet", "OutputSpreadsheet.xlsx", "Chrome", "Profile", 30,
						"Object.properties", 1, false, false } };
	}
}
