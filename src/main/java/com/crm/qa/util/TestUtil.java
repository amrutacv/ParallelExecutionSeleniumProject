package com.crm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openxml4j.exceptions.InvalidFormatException;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;

public class TestUtil {
	public static final long pageLoadTimeOut = 40;
	public static final long implicitlyWait = 40;
	public static String TESTDATA_SHEET_PATH = "C:\\Users\\AcVyawahare\\eclipse-workspace\\MavenProject3\\src\\main\\java\\com\\crm\\qa\\testData\\CRM_TestData.xlsx";

	public static Workbook book;
	public static Sheet sheet;
	
	public static Object[][] getTestData(String sheetName) throws InvalidFormatException {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		//Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
	    Object[][] data = new Object[sheet.getLastRowNum()][1];
		Cell cell;
		// System.out.println(sheet.getLastRowNum() + "--------" +
		// sheet.getRow(0).getLastCellNum());
		int j=0;
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Map<Object, Object> datamap = new HashMap<>();

			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				String cellValue;
				cell = sheet.getRow(i).getCell(k);
				if (cell == null || cell.getCellType() == CellType.BLANK) {
					cellValue = "";
				} else {
					cellValue = cell.getStringCellValue();
				}
				datamap.put(sheet.getRow(0).getCell(k).getStringCellValue(), cellValue);
			}
			data[j][0] = datamap;
			j++;
		}
		return data;
	}
	
	public static void takeScreenshotAtEndOfTest(WebDriver driver) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}
	
	public static WebDriver getDriver(String browser) {
		WebDriver driver;
		switch(browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("enable-automation");
			driver = new ChromeDriver(options);
			break;
		case "ie":
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
			break;
		default:
			   driver = null;
			   break;
		}
		return driver;
	}
}

