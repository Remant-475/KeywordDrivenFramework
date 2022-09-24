package com.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class KeywordDrivenEngine extends Base {
	
	    
		private Workbook workbook;
		private Sheet sheet;
		private FileInputStream excelFile;
		private String locatorName = null;
		private String locatorValue = null;
		private String action;
		private String value;
		private Base base;
		
		public final String FILE_PATH = "C:\\Users\\ASUS\\eclipse-workspace\\KeywordDrivenFramework\\src\\main\\resources\\Repository\\LoginTestScenarios.xlsx";
		
		public void startExecution(String sheetName) {
			
			try {
				
				//Taking Excel file into workbook class
				excelFile = new FileInputStream(FILE_PATH);
				try {
					workbook = WorkbookFactory.create(excelFile);
				} catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (InvalidFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//Taking Excel file sheet into Sheet Class
			sheet = workbook.getSheet("LoginTestData");
			int k=0;
			System.out.println(sheet.getLastRowNum());
			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				try {
				
				String data = sheet.getRow(i+1).getCell(k+1).getStringCellValue().trim();
				System.out.println("Data:"+data);
				if (!data.equalsIgnoreCase("NA")) {
					locatorName = data.split("=")[0].trim();
					System.out.println("LocatorName: "+locatorName);
					locatorValue = data.split("=")[1].trim();
					System.out.println("LocatorValue: "+locatorValue);
				}
				
				action = sheet.getRow(i+1).getCell(k+2).getStringCellValue().trim();
				System.out.println("Action:"+action);
				value = sheet.getRow(i+1).getCell(k+3).getStringCellValue().trim();
				System.out.println("Value:"+value);
				
				switch (action) {
				case "open browser":
					base = new Base();
					base.intit_Properties();
						if (value.isEmpty() || value.equals("NA")) {
							prop.getProperty("browser");
						}
						else {
							base.init_Driver(value);
						}
						
					break;

				case "enter url":
					driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
					driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
					if (value.isEmpty() || value.equals("NA")) {
						prop.getProperty("url");
					}
					else {
						driver.get(value);
					}
					
					break;
					
				
				case "quit":
					driver.quit();
					break;
					
				default:
					break;
				}
				switch (locatorName) {
				case "xpath":
					Thread.sleep(1000);
					WebElement element = driver.findElement(By.xpath(locatorValue));
					System.out.println("Web Element"+element);
					if (action.equalsIgnoreCase("sendkeys")) {
						element.clear();
						element.sendKeys(value);
						Thread.sleep(2000);
					} else if (action.equalsIgnoreCase("click")) {
						element.click();
					}
					break;

				case "null":
					break;
				default:
					break;
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			}
		}
}
				
				
				
				


