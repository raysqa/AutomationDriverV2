package raysullivan.operation;

public class AutomationDriverUtil {

	private String decimalPlaces, spreadsheet, worksheet, propertyName;
	private String ERROR = "Error";
	private String SUCCESS = "Success";
	private int timeout, iterations;
	private static String browser;
	private String profile;
	private String resultSpreadsheet;
	private String testCase;
	private boolean csv, video;
	private String keyString;
	private static final String RESOURCEPATH = System.getProperty("user.dir") + "\\resource\\";
	private static final String TESTREPORTPATH = "Test Report\\";
	private static final String TESTPROPERTYPATH = "Test Properties\\";
	private static final String DRIVERPATH = System.getProperty("user.dir") + "\\Drivers\\";
	private static final String SPREADSHEET = ".xlsx";
	private static final String PROPERTYDELIMITER = "|";

	public String valueCellFormat(final String[] cell) throws Exception {
		/*
		 * If a value is TEXT or if the action is PAUSE/CLICKANDHOLD, strip of
		 * trailing .0 from numeric values passed from the spreadsheet
		 * 
		 * Otherwise if it is Numeric, make sure the number has two decimal
		 * places
		 */
		String value = cell[3];
		final String valueType = cell[4];
		final String keyword = cell[1];
		final String decimalConstant = ".0";
		setKeyString("automationDriver");
		boolean error = false;
		try {
			decimalPlaces = value.substring(value.indexOf("."));
		} catch (StringIndexOutOfBoundsException s) {
			decimalPlaces = ".0";
			error = true;
		} catch (NullPointerException npe) {
			throw new AutomationDriverException("Keyword or value cannot be null");
		}
		try{
		if (valueType.equalsIgnoreCase("TEXT") || keyword.equalsIgnoreCase("PAUSE")
				|| keyword.equalsIgnoreCase("CLICKANDHOLD") || keyword.equalsIgnoreCase("SELECTBYINDEX")
				|| keyword.equalsIgnoreCase("DESELECTBYINDEX")) {
			value = value.replace(decimalPlaces, "");
		} else if (valueType.equalsIgnoreCase("DECIMAL") && decimalPlaces.equals(decimalConstant)) {
			if (error) {
				value = value + decimalPlaces;
			}
			value = value.replace(decimalPlaces, ".00");
		} else if (valueType.equalsIgnoreCase("DECIMAL") && decimalPlaces.length() == 2) {
			value = value + "0";
		}
		if (valueType.equalsIgnoreCase("ENCRYPT")) {
			value = AutomationDriverEncryptDecrypt.decrypt(value, getKeyString());
		}
		return value;
		} catch (NullPointerException npe) {
			throw new AutomationDriverException("Keyword or value cannot be null");
		}
	}

	public void setKeyString(final String keyString) {
		this.keyString = keyString;
	}

	public String getKeyString() {
		return keyString;
	}

	public final float calcEt(long end, long start, int millisec) {
		if (start > end) {
			return (float) (start - end) / millisec;
		} else {
			return (float) (end - start) / millisec;
		}
	}

	public final float calcAvg(float total, float divideBy, int roundup) {
		return (float) Math.round((total / divideBy) * roundup) / roundup;
	}

	public String getSuccessString() {
		return SUCCESS;
	}

	public String getErrorString() {
		return ERROR;
	}

	public void setTestCase(String tEST_CASE) {
		this.testCase = tEST_CASE;

	}

	public String getTestCase() {
		return testCase;
	}

	public void setTestProfile(String sPREADSHEET, String wORKSHEET, String rESULT_SPREADSHEET, String bROWSER,
			String wEB_PROFILE, int tIMEOUT, String pROPERTY_NAME, int sHEET_ITERATIONS, boolean cAPTURE_CSV,
			boolean cAPTURE_VIDEO) {
		setSpreadsheet(sPREADSHEET);
		setWorksheet(wORKSHEET);
		setResultSpreadsheet(rESULT_SPREADSHEET);
		setBrowser(bROWSER);
		setWebProfile(wEB_PROFILE);
		setTimeout(tIMEOUT);
		setPropertyName(pROPERTY_NAME);
		setSheetIterations(sHEET_ITERATIONS);
		setIsCapCsv(cAPTURE_CSV);
		setIsCapVideo(cAPTURE_VIDEO);
	}

	public String getSpreadsheet() {
		return spreadsheet;
	}

	public String getWorksheet() {
		return worksheet;
	}

	public int getTimeout() {
		return timeout;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public String getBrowser() {
		return browser;
	}

	public String getWebProfile() {
		return profile;
	}

	public int getSheetIterations() {
		return iterations;
	}

	public String getResultSpreadsheet() {
		return resultSpreadsheet;
	}

	public boolean getIsCapCsv() {
		return csv;
	}

	public boolean getIsCapVideo() {
		return video;
	}

	public void setTimeout(int tIMEOUT) {
		this.timeout = tIMEOUT;

	}

	public void setSpreadsheet(final String sPREADSHEET) {
		this.spreadsheet = sPREADSHEET;

	}

	public void setResultSpreadsheet(String rESULTSPREADSHEET) {
		this.resultSpreadsheet = rESULTSPREADSHEET;

	}

	public void setWorksheet(String wORKSHEET) {
		this.worksheet = wORKSHEET;

	}

	public void setPropertyName(String pROPERTY_NAME) {
		this.propertyName = pROPERTY_NAME;

	}

	public void setBrowser(String bROWSER) {
		AutomationDriverUtil.browser = bROWSER;
	}

	public void setWebProfile(String wEB_PROFILE) {
		this.profile = wEB_PROFILE;
		
	}

	public void setSheetIterations(int sHEET_ITERATIONS) {
		this.iterations = sHEET_ITERATIONS;		
	}

	public void setIsCapCsv(boolean cAPTURE_CSV) {
		this.csv = cAPTURE_CSV;
		
	}

	public void setIsCapVideo(boolean cAPTURE_VIDEO) {
		this.video = cAPTURE_VIDEO;
		
	}

	public String getTestReportPath() {
		return getResourcePath() + TESTREPORTPATH;
	}


	public String getResourcePath() {
		return RESOURCEPATH;
	}

	public String getDriverPath() {
		return DRIVERPATH;
	}

	public String getTestPropertyPath() {
		return getResourcePath() + TESTPROPERTYPATH;
	}

	public String getSpreadsheetExtension() {
		return SPREADSHEET;
	}

	public String getPropertyDelimiter() {
		return PROPERTYDELIMITER;
	}
}
