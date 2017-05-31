package raysullivan.unitTest;

import org.testng.annotations.*;

import raysullivan.operation.AutomationDriverException;
import raysullivan.operation.AutomationDriverUtil;

import static org.fest.assertions.api.Assertions.*;

/**
 * TutilTest
 * 
 * Tests the Automation Driver utility class / methods
 * 
 * @author rsullivan
 *
 */
public class TestAutomationDriverUtil {

	private static final String TEST_REPORT_PATH = System.getProperty("user.dir") + "\\resource\\Test Report\\";
	private static final String TEST_PROPERTY_PATH = System.getProperty("user.dir") + "\\resource\\Test Properties\\";
	private AutomationDriverUtil util = new AutomationDriverUtil();
	private static int millisec = 1000, roundup = 1000, TIMEOUT = 20, SHEET_ITERATIONS = 1;
	private static boolean CAPTURE_CSV = false, CAPTURE_VIDEO = true;
	private static String TEST_CASE = "TestCase", SPREADSHEET = "Spreadsheet.xlsx", WORKSHEET = "Worksheet",
			RESULT_SPREADSHEET = "Results.xlsx", BROWSER = "Firefox", WEB_PROFILE = "Test_Profile",
			PROPERTY_NAME = "test.properties", KEYSTRING = "automationDriver";

	@Test(dataProvider = "valueCellFormat", description = "valueCellFormatTest", enabled = true)
	/**
	 * valueCellFormatTEst
	 * 
	 * @param cell1
	 * @param cell4
	 * @param cell5
	 * @param expected
	 */
	public final void valueCellFormatTest(final String cell1, String cell3, final String cell4, String expected)
			throws Exception {
		String[] cell = new String[6];
		cell[1] = cell1;
		cell[3] = cell3;
		cell[4] = cell4;
		assertThat(util.valueCellFormat(cell)).isEqualTo(expected);
	}
	@Test(dataProvider = "valueCellFormatException", description = "valueCellFormatExceptionTest", expectedExceptions = AutomationDriverException.class, enabled = true)
	public final void valueCellFormatExceptionTest(final String cell1, String cell3, final String cell4, String expected)
			throws Exception {
		String[] cell = new String[6];
		cell[1] = cell1;
		cell[3] = cell3;
		cell[4] = cell4;
		util.valueCellFormat(cell);
	}
	/**
	 * getElapsedTime
	 * 
	 * @param end
	 * @param start
	 * @param expected
	 */
	@Test(dataProvider = "etValues", description = "getElapsedTime", enabled = true)
	public final void getElapsedTime(long end, long start, float expected) {
		assertThat(util.calcEt(end, start, millisec)).isEqualTo(expected);
	}

	/**
	 * getAverage
	 * 
	 * @param total
	 * @param divideBy
	 * @param expected
	 */
	@Test(dataProvider = "avgValues", description = "getAverage", enabled = true)
	public final void getAverage(float total, float divideBy, float expected) {
		assertThat(util.calcAvg(total, divideBy, roundup)).isEqualTo(expected);
	}

	/**
	 * getSuccess
	 */
	@Test(description = "getSuccess", enabled = true)
	public final void getSuccess() {
		assertThat(util.getSuccessString()).isEqualTo("Success");
	}
	
	@Test(description = "getTestReportPath", enabled=true)
	public final void getTestReportPath(){
		assertThat(util.getTestReportPath()).isEqualTo(TEST_REPORT_PATH);
	}
	
	@Test(description = "getTestPropertyPath", enabled=true)
	public final void getTestPropertyPath(){
		assertThat(util.getTestPropertyPath()).isEqualTo(TEST_PROPERTY_PATH);
	}
	/**
	 * getError
	 */
	@Test(description = "getError", enabled = true)
	public final void getError() {
		assertThat(util.getErrorString()).isEqualTo("Error");
	}

	/**
	 * getTestcase
	 */
	@Test(description = "getTestcase", enabled = true)
	public final void getTestcase() {
		util.setTestCase(TEST_CASE);
		assertThat(util.getTestCase()).isEqualTo(TEST_CASE);
	}

	@Test(description = "getSpreadsheet", enabled = true)
	public final void getSpreadsheet() {
		util.setSpreadsheet(SPREADSHEET);
		assertThat(util.getSpreadsheet()).isEqualTo(SPREADSHEET);
	}

	@Test(description = "getResultSpreadsheet", enabled = true)
	public final void getResultSpreadsheet() {
		util.setResultSpreadsheet(RESULT_SPREADSHEET);
		assertThat(util.getResultSpreadsheet()).isEqualTo(RESULT_SPREADSHEET);
	}

	@Test(description = "getWorksheet", enabled = true)
	public final void getWorksheet() {
		util.setWorksheet(WORKSHEET);
		assertThat(util.getWorksheet()).isEqualTo(WORKSHEET);
	}

	@Test(description = "getPropertyName", enabled = true)
	public final void getPropertyName() {
		util.setPropertyName(PROPERTY_NAME);
		assertThat(util.getPropertyName()).isEqualTo(PROPERTY_NAME);
	}

	@Test(description = "getBrowser", enabled = true)
	public final void getBrowser() {
		util.setBrowser(BROWSER);
		assertThat(util.getBrowser()).isEqualTo(BROWSER);
	}

	@Test(description = "getWebProfile", enabled = true)
	public final void getWebProfile() {
		util.setWebProfile(WEB_PROFILE);
		assertThat(util.getWebProfile()).isEqualTo(WEB_PROFILE);
	}

	@Test(description = "getSheetIterations", enabled = true)
	public final void getSheetIterations() {
		util.setSheetIterations(SHEET_ITERATIONS);
		assertThat(util.getSheetIterations()).isEqualTo(SHEET_ITERATIONS);
	}

	@Test(description = "getIsCapCsv", enabled = true)
	public final void getIsCapCsv() {
		util.setIsCapCsv(CAPTURE_CSV);
		assertThat(util.getIsCapCsv()).isEqualTo(CAPTURE_CSV);
	}

	@Test(description = "getIsCapVideo", enabled = true)
	public final void getIsCapVideo() {
		util.setIsCapVideo(CAPTURE_VIDEO);
		assertThat(util.getIsCapVideo()).isEqualTo(CAPTURE_VIDEO);
	}
	
	@Test(description = "getKeyString", enabled = true)
	public final void getKeyString() {
		util.setKeyString(KEYSTRING);
		assertThat(util.getKeyString()).isEqualTo(KEYSTRING);
	}


	/**
	 * getTestProfile
	 */
	@Test(description = "getTestProfile", enabled = true)
	public final void getTestProfile() {
		util.setTestProfile(SPREADSHEET, WORKSHEET, RESULT_SPREADSHEET, BROWSER, WEB_PROFILE, TIMEOUT, PROPERTY_NAME,
				SHEET_ITERATIONS, CAPTURE_CSV, CAPTURE_VIDEO);
		assertThat(util.getSpreadsheet()).isEqualTo(SPREADSHEET);
		assertThat(util.getWorksheet()).isEqualTo(WORKSHEET);
		assertThat(util.getResultSpreadsheet()).isEqualTo(RESULT_SPREADSHEET);
		assertThat(util.getBrowser()).isEqualTo(BROWSER);
		assertThat(util.getWebProfile()).isEqualTo(WEB_PROFILE);
		assertThat(util.getTimeout()).isEqualTo(TIMEOUT);
		assertThat(util.getPropertyName()).isEqualTo(PROPERTY_NAME);
		assertThat(util.getSheetIterations()).isEqualTo(SHEET_ITERATIONS);
		assertThat(util.getIsCapCsv()).isEqualTo(CAPTURE_CSV);
		assertThat(util.getIsCapVideo()).isEqualTo(CAPTURE_VIDEO);
	}

	/**
	 * getTimeout
	 */
	@Test(description = "getTimeout", enabled = true)
	public final void getTestTimeout() {
		util.setTimeout(TIMEOUT);
		assertThat(util.getTimeout()).isEqualTo(TIMEOUT);
	}

	/**
	 * valueCellFormat data provider
	 * 
	 * @return Object[][]
	 */
	@DataProvider
	public final Object[][] valueCellFormat() {
		return new String[][] { new String[] { "", "123.0", "text", "123" },
				new String[] { "pause", "123.0", "", "123" }, 
				new String[] { "", "123.00", "TEXT", "123" },
				new String[] { "PAUSE", "123.00", "", "123" }, 
				new String[] { "", "123", "TEXT", "123" },
				new String[] { "", "12345678.0", "TEXT", "12345678" }, 
				new String[] { "PAUSE", "123", "", "123" },
				new String[] { "GETTEXT", "100.0", "decimal", "100.00" },
				new String[] { "", "200.01", "DeCiMaL", "200.01" },
				new String[] { "SETTEXT", "300.99", "DECIMAL", "300.99" },
				new String[] { "", "400", "DECIMAL", "400.00" }, 
				new String[] { "", "url", "", "url" },
				new String[] { "", "4,140.00 USD", "", "4,140.00 USD" }, 
				new String[] { "", "123.0", "", "123.0" },
				new String[] { "settext", "ieKkIwygTyBf63mi62KD7w==", "encrypt", "Welcome1" } };
	}
	@DataProvider
	public final Object[][] valueCellFormatException() {
		return new String[][] { new String[] { null, "123.0", "", "123.0" },
				new String[] { "GETTEXT", null, "", "123.0" } };
	}
	/**
	 * etValues
	 * 
	 * @return Object[][]
	 */
	@DataProvider
	public final Object[][] etValues() {
		return new Object[][] { new Object[] { 20000L, 10000L, 10f },
				new Object[] { 1418321923194L, 1418321913196L, 9.998F },
				new Object[] { 1418321913196L, 1418321923194L, 9.998F },
				new Object[] { -1418321923194L, 0L, 1418322053.194F },
				new Object[] { 0L, -1418321923194L, 1418322053.194F }, new Object[] { -23194L, -13194L, 10f },
				new Object[] { -13194L, -23194L, 10.0f }, new Object[] { 23194L, -13194L, 36.388f },
				new Object[] { 23194L, 13194L, 10.0f }, new Object[] { 13194L, 23194L, 10.0f },
				new Object[] { 1000L, 1000L, 0.0f }, new Object[] { 0L, 0L, 0.0f } };
	}

	/**
	 * avgValues data provider
	 * 
	 * @return Object[][]
	 */
	@DataProvider
	public final Object[][] avgValues() {
		return new Object[][] { new Object[] { 16452534193050.4f, 1418321913194f, 11.6f },
				new Object[] { 1134657530555.2f, 1418321913194f, 0.8f },
				new Object[] { 851000239525966f, 1418321913194f, 600.005f },
				new Object[] { -14183219131.94f, 1418321913194f, -0.01f },
				new Object[] { 14183219131.94f, -1418321913194f, -0.01f },
				new Object[] { -851000239525966f, -1418321913194f, 600.005f }, new Object[] { 0f, 0f, 0f },
				new Object[] { 500f, 100f, 5f }, new Object[] { 500f, 500f, 1f } };
	}
}