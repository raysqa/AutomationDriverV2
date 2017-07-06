package raysullivan.operation;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import raysullivan.dataProvider.Default;

@Test(dataProvider = "DefaultDataProvider", dataProviderClass = Default.class)
public class AdTestDriver {

	private WebDriver driver = null;
	private AdUtil util = new AdUtil();
	private ProfilesIni allProfiles = null;
	private FirefoxProfile profile = null;
	private AdExcelTestResults wfile = null;
	private AdCsvTestResults cfile = null;
	private AdOpenKDTestSpreadsheet rfile = null;
	private AdReadProperties object = null;
	private Properties allObjects = null;
	private AdVariable var = null;
	private AdBrowser br = new AdBrowser(null, null);
	private String csvFile, excelFile;
	private Sheet sheet;
	private AdVideoRecorder vr = null;
	//private final String TESTCASEPATH = "Test Cases\\";
	private int rowCount;

	@AfterTest
	public void afterTest() throws Exception {
		Thread.sleep(1000);
		try {
			vr.stopRecording();
		} catch (NullPointerException npe) {
		}
	}

	@AfterSuite
	public void afterSuite() throws Exception {
		driver.close();
		try {
			Thread.sleep(1000);
			driver.quit();
		} catch (Exception e) {
		}
	}

	public final void testDriver(final String spreadsheet, final String worksheet, String resultSpreadsheet,
			final String browser, final String webProfile, final int timeout, final String propertyName,
			final int sheetIterations, final boolean capCsv, final boolean capVideo) throws Exception {
		util.setTestProfile(spreadsheet, worksheet, resultSpreadsheet, browser, webProfile, timeout, propertyName,
				sheetIterations, capCsv, capVideo);
		if (webProfile == "none") {
			driver = br.getDriver(null);
		} else {
			allProfiles = new ProfilesIni();
			profile = allProfiles.getProfile(webProfile);
			try {
				profile.setPreference("foo.bar", 23);
			} catch (NullPointerException npe) {
				throw new AdException("Error: FireFox Profile " + webProfile + " is invalid or does not exist.");
			}
			driver = br.getDriver(profile);
		}
		setFluentWait(timeout);
		setInputOutputFiles(resultSpreadsheet);
		setObjectRepository(propertyName);
		sheet = initFiles(spreadsheet, excelFile, worksheet, resultSpreadsheet,
				csvFile, sheetIterations, capCsv);
		startVidCap(capVideo);
		var = new AdVariable();
				for (int si = 1; si <= sheetIterations; si++) {
			/*
			 * Send test information to console
			 */
			System.out.println("Test " + worksheet + " iteration " + si
					+ " of " + sheetIterations + " started.");
			/*
			 * Count the number of test steps and set up to iterate through all
			 * test steps
			 */
			try {
				rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
			} catch (NullPointerException e1) {
				throw new AdException("Error:  Worksheet "
						+ worksheet + " does not exist or is invalid");
			}
		for (int i = 1; i < rowCount + 1; i++) {
			// Loop over all the rows
			Row row = sheet.getRow(i);
			/*
			 * Excel is zero based for rows; add 1 to the current row for
			 * the actual row number
			 */
			int r = i + 1;
			/*
			 * Move the spreadsheet row just read to an array
			 */
			String[] cell = new String[8];
			try {
				cell[0] = row.getCell(0).toString();
				cell[1] = row.getCell(1).toString();
				cell[2] = row.getCell(2).toString();
				cell[3] = row.getCell(3).toString()
						.replaceAll("(\\r|\\n|\\t)", "");
				cell[4] = row.getCell(4).toString();
				cell[5] = row.getCell(5).toString();
				cell[6] = row.getCell(6).toString();
				cell[7] = row.getCell(7).toString();
			} catch (NullPointerException e) {
				throw new AdException(
						"Error:  Unable to process row " + r
								+ " from spreadsheet: " + spreadsheet
								+ ", worksheet: " + worksheet);
			}
			/*
			 * Check if the first cell contain a value, if yes, That means
			 * it is the new testcase name; if the second cell has content,
			 * process as a keyword instruction
			 */
			if (cell[0].length() == 0 && cell[1].length() > 0) {
				/*
				 * Format the value cell to ensure correct text or decimal
				 */
				//cell[3] = util.valueCellFormat(cell);
				/*
				 * Timestamp the call to execute the test step
				 */
				String timeStamp = new SimpleDateFormat(
						"MM/dd/yyyy HH:mm:ss").format(Calendar
						.getInstance().getTime());
				/*
				 * Call the method to perform the action on the UI returning
				 * a value of the elapsed time and result message
				 */
				String returnString[] = new String[3];
				returnString = br.perform(allObjects, propertyName,
						cell, var, util);
				/*
				 * Validate the format of the return string
				 */
				returnString[1] = util.resultCellFormat(cell, returnString);
				/*
				 * Initialize log variables and assertion error message
				 */
				String[] rows = {timeStamp, worksheet, util.getTestCase(),
						si + " of " + sheetIterations, Integer.toString(r),
						cell[1], cell[2], cell[3], cell[4], cell[5],
						cell[6], cell[7],returnString[0], returnString[2],
						returnString[1]};
				String errorMsg = "Error in operation "
						+ util.getSpreadsheet() + "\\"
						+ util.getWorksheet() + "\\" + util.getTestCase()
						+ " Row " + r + " " + cell[1] + " " + cell[2] + " "
						+ cell[3];
				/*
				 * Assertions: 1. Passed if the UI action returned a success
				 * string 2. Failed if the UI action returned an error
				 * string 3. Failed if the UI action returned an unexpected
				 * result
				 */
				/*
				 * Success Returned
				 */
				if (returnString[1].equals(returnString[2])) {
					rows[1] = worksheet;
					/*
					 * Send the test results to the log
					 */
					testOutput(capCsv, rows);
					Assert.assertTrue(true);
				}
				/*
				 * Generic Error Returned
				 */
				else if (returnString[1].equals(util.getErrorString())) {
					testOutput(capCsv, rows);
					Assert.assertTrue(errorMsg + "\t" + cell[3], false);
				}
				/*
				 * Specific Error Returned
				 */
				else {
					/*
					 * Set actual result for excel output
					 */
					rows[12] = returnString[2];
					if (returnString[1].equals(returnString[2])) {
						// Assertion will pass if this condition exists
						rows[1] = worksheet;
					}
					testOutput(capCsv, rows);
					Assert.assertTrue(errorMsg + " Expecting: \""
							+ returnString[2] + "\" Actual: \""
							+ returnString[1] + "\"",
							returnString[1].equals(returnString[2]));
				}
			} else {
				if (cell[0].length() > 0) {
					// Set the new testcase name when it started
					String testCase = cell[0];
					util.setTestCase(testCase);
					System.out.println("\tTest Case: " + testCase + "...");
				}
			}
		}
		System.out.println("Test " + worksheet + " iteration " + si
				+ " of " + sheetIterations + " completed successfully.");
	}
	stopVidCap(capVideo);
	if (sheetIterations > 0) {
		System.out.println("Test " + worksheet + " complete.");
	}
}

	private void testOutput(final boolean capCsv, String[] rows) throws Exception {
		wfile.writeExcel(util.getTestReportPath(), util.getResultSpreadsheet(), util.getWorksheet(), rows);
		if (capCsv == true) {
			cfile.writeCsv(util.getTestReportPath(), util.getResultSpreadsheet(), util.getWorksheet(), rows);
		}
	}

	private void setFluentWait(final int timeout) throws AdException {
		if (timeout < 5) {
			throw new AdException(
					"Error:  Default WebDriverWait cannot be less than 5 seconds.");
		}
		util.setTimeout(timeout);
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			    .withTimeout(timeout, TimeUnit.SECONDS)
			    .pollingEvery(5, TimeUnit.SECONDS);
		br = new AdBrowser(driver, wait);
	}

	private void setObjectRepository(final String propertyName) throws IOException, AdException {
		object = new AdReadProperties();
		allObjects = object.getObjectRepository(propertyName);
	}

	private void setInputOutputFiles(String resultSpreadsheet) throws AdException {
		if (resultSpreadsheet.length() == 0) {
			throw new AdException("Error:  Result Spreadsheet cannot be blank or null.");
		}
		rfile = new AdOpenKDTestSpreadsheet();
		wfile = new AdExcelTestResults();
		cfile = new AdCsvTestResults();
	}
	private Sheet initFiles(String spreadsheet, String excelFile,
			String worksheet, String resultSpreadsheet, String csvFile,
			int sheetIterations, boolean capCsv) throws Exception {
		try {
			spreadsheet.substring(spreadsheet.indexOf("."));
			excelFile = spreadsheet;
		} catch (StringIndexOutOfBoundsException e) {
			excelFile = spreadsheet + util.getSpreadsheetExtension();
		}
		sheet = rfile.readKDSheet(util.getTestCasePath(), excelFile, worksheet);
		if (sheetIterations > 0) {
			/*
			 * Set the worksheet header information
			 */
			String[] row0 = {"Date/Time", "Worksheet", "TestCase", "Iteration",
					"Row", "Action", "Object", "Value", "Value Type",
					"Variable", "Comment", "Elapsed Time", "Expected", "Actual"};
			/*
			 * If executing, clear the result worksheet and .csv output files
			 */
			try {
				resultSpreadsheet.substring(resultSpreadsheet.indexOf("."));
				excelFile = resultSpreadsheet;
			} catch (StringIndexOutOfBoundsException e) {
				excelFile = resultSpreadsheet + util.getSpreadsheetExtension();
			}
			wfile.clearWorksheet(util.getTestReportPath(), excelFile, worksheet);
			wfile.writeExcel(util.getTestReportPath(), excelFile, worksheet,
					row0);
			if (capCsv == true) {
				csvFile = resultSpreadsheet + "_" + worksheet + ".csv";
				util.deleteFile(util.getTestReportPath(), csvFile);
				cfile.writeCsv(util.getTestReportPath(), resultSpreadsheet,
						worksheet, row0);
			}
		}
		return sheet;
	}
	private boolean startVidCap(boolean capVideo) throws Exception {
		if (capVideo == true) {
			System.out.println("\tStarting Video Recording");
			vr.startRecording(
					util.getSpreadsheet() + "_" + util.getWorksheet(),
					util.getTestReportPath());
		}
		return capVideo;
	}
	private boolean stopVidCap(boolean capVideo) throws Exception {
		if (capVideo == true) {
			Thread.sleep(1000);
			System.out.println("\tStop Video Recording");
			vr.stopRecording();
		}
		return capVideo;
	}
}
