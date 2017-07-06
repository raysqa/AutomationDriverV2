package raysullivan.unitTest;

import org.testng.annotations.*;

import raysullivan.operation.AdException;
import raysullivan.operation.AdOpenKDTestSpreadsheet;

import static org.fest.assertions.api.Assertions.*;

import org.apache.poi.ss.usermodel.Sheet;



public class TestAdOpenExcelSpreadsheet {

	private Sheet sheet = null;
	private AdOpenKDTestSpreadsheet rfile = null;
	
	@BeforeTest
	public void beforeTest() {
		rfile = new AdOpenKDTestSpreadsheet();
	}

	@Test(dataProvider = "validFiles", description = "validKBTestFile", enabled = true)
	public void validKBTestFile(String filepath, String fileName, String sheetName) throws Exception {
		
		sheet = rfile.readKDSheet(filepath, fileName, sheetName);
		assertThat(sheet).isNotNull();
	}

	@Test(dataProvider = "invalidFiles", description = "invalidKBTestFile", expectedExceptions = AdException.class, enabled = true)
	public void invalidKBTestFile(String filepath, String fileName, String sheetName) throws Exception {
		sheet = rfile.readKDSheet(filepath, fileName, sheetName);
	}
	@DataProvider
	public final Object[][] validFiles() {
		return new String[][] {
				new String[] { "C:\\Users\\rsullivan\\Eclipse\\workspace\\AutomationDriverV2\\resource\\Test Cases",
						"DefaultTestCase.xlsx", "RenameWorksheet" }, 
				new String[] { "C:\\Users\\rsullivan\\Eclipse\\workspace\\AutomationDriverV2\\resource\\Test Cases",
						"DefaultTestCase.xls", "RenameWorksheet" }, };
	}
	@DataProvider
	public final Object[][] invalidFiles() {
		return new String[][] {
				new String[] { "C:\\Users\\rsullivan\\Eclipse\\workspace\\AutomationDriverV2\\resource\\",
						"DefaultTestCase.xlsx", "RenameWorksheet" }, 
				new String[] { null,
						"DefaultTestCase.xlsx", "RenameWorksheet" }, 
				new String[] { "C:\\Users\\rsullivan\\Eclipse\\workspace\\AutomationDriverV2\\resource\\",
						null, "RenameWorksheet" }, 
				new String[] { "C:\\Users\\rsullivan\\Eclipse\\workspace\\AutomationDriverV2\\resource\\",
						"DefaultTestCase.xlsx", null }, 
				new String[] { null, null, null }, 
				new String[] { "C:\\Users\\rsullivan\\Eclipse\\workspace\\AutomationDriverV2\\resource\\Test Cases",
						"DefaultTestCase", "RenameWorksheet" },
				new String[] { "C:\\Users\\rsullivan\\Eclipse\\workspace\\AutomationDriverV2\\resource\\Test Cases",
						"DefaultTestCase.xlsx", "enameWorksheet" },
				};
	}
}
