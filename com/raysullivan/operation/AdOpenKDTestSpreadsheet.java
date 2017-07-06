package raysullivan.operation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AdOpenKDTestSpreadsheet {

	public Sheet readKDSheet(String filePath, String fileName, String sheetName) throws Exception {
		final File file = new File(filePath + "\\" + fileName);
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(file);
		} catch (FileNotFoundException fnf) {
			throw new AdException("Error:  Test Spreadsheet " + fileName + " at location " + filePath
					+ " with worksheet " + sheetName + " not found.");
		}
		Workbook workbook = null;
		final String fileExtensionName = fileName.substring(fileName.indexOf("."));

		if (fileExtensionName.equalsIgnoreCase(".xlsx")) {
			workbook = new XSSFWorkbook(inputStream);
		} else if (fileExtensionName.equalsIgnoreCase(".xls")) {
			workbook = new HSSFWorkbook(inputStream);
		}
		final Sheet sheet = workbook.getSheet(sheetName);
		if (sheet == null) {
			throw new AdException("Error:  Test Spreadsheet " + fileName + " at location " + filePath
					+ " does not contain worksheet " + sheetName + ".");
		}
		return sheet;
	}

}
