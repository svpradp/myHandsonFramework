package com.utilities;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider {
	
	XSSFWorkbook wb;
	
	public ExcelDataProvider() {
		
		File src = new File("./TestData/Data.xlsx");
		
		try {
			FileInputStream fis = new FileInputStream(src);
			
			wb = new XSSFWorkbook(fis);
		} 
		catch (Exception e) {
			
			System.out.println("Unable to read Excel File: "+e.getMessage());;
		}
	}
	
	//Metod overloading - same method name but different arguments
	public String getStringData(int ShtIndex,int row, int col) 
	{
		
		return wb.getSheetAt(ShtIndex).getRow(row).getCell(col).getStringCellValue();
		
	}
	
	public String getStringData(String Shtname,int row, int col) 
	{
		
		return wb.getSheet(Shtname).getRow(row).getCell(col).getStringCellValue();
		
	}
	
	public double getNumericData(String Shtname,int row, int col) 
	{
		
		return wb.getSheet(Shtname).getRow(row).getCell(col).getNumericCellValue();
	}

}
