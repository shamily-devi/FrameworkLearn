package com.resources;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataConfig {
	
	XSSFWorkbook workbook ;
	XSSFSheet sheet;
	
	public void getWorkbook(String filepath) throws Exception {
		
		FileInputStream fis = new FileInputStream(filepath);
		
		workbook=new XSSFWorkbook(fis);
		
		
	}
	
	public int getRowCount(String sheetName) {
		return 0;
		
	}

}
