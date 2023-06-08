package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utilities {

	public static final int ImplicitWait = 10;
	public static final int LoadWait = 10;

	public static String generateEmailWithTimeStamp() {
		Date date = new Date();
		String timeStamp = date.toString().replace(" ", "_").replace(":", "_");
		return "chandanhngowda" + timeStamp + "@gmail.com";
	}

	public static Object[][] readExcelData(String sheetName) throws IOException {
		File excelFile = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\TutorialsNinjaTestData.xlsx");
		XSSFWorkbook workBook = null;
		FileInputStream fisExcel = new FileInputStream(excelFile);
		workBook = new XSSFWorkbook(fisExcel);
		XSSFSheet sheet = workBook.getSheet(sheetName);

		// Find how many rows and coloumns are there in the excel sheet
		int rows = sheet.getLastRowNum();
		int coloumns = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[rows][coloumns];

		for (int i = 0; i < rows; i++) {
			XSSFRow row = sheet.getRow(i + 1); // i+1 is mandatory because it has to move to next cell to fetch the data
			for (int j = 0; j < coloumns; j++) {
				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();

				switch (cellType) {
				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;

				case NUMERIC:
					data[i][j] = Integer.toString((int) cell.getNumericCellValue());
					// We need to cast (int) because cell.getNumericCellValue() will give values
					// like Password@2023.0000
					// Then We need to convert the Interger into String using "Integer.toString"
					break;

				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;

				}
			}
		}
		return data;
	}
}
