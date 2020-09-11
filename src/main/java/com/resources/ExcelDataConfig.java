package com.resources;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataConfig {

	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet;
	private static String filePath = "C:\\Users\\A Shamily Devi\\git\\repository2\\MakeMyTrip\\inputfiles\\MakeMyTrip.xlsx";
	private static String sheetName = "Sheet1";

	public static void main(String[] args) throws Exception {
		List<RowBOs> rowBOs = readWorkbook(filePath, sheetName);
		getRowCount();
		System.out.println("\n");
		getCellCount();
		System.out.println("\n");

		printExcel(rowBOs);
	}

	private static void printExcel(List<RowBOs> rowBOs) {
		for (RowBOs rowBO : rowBOs) {
			for (Cell cell : rowBO.getCells()) {
				CellType cellType = cell.getCellType();

				if (cellType == CellType.BOOLEAN) {
					System.out.print(cell.getBooleanCellValue());
				} else if (cellType == CellType.STRING) {
					System.out.print(cell.getStringCellValue());
				} else if (cellType == CellType.NUMERIC) {
					if (DateUtil.isCellInternalDateFormatted(cell)) {
						System.out.print(cell.getDateCellValue());
					} else {
						System.out.print(cell.getNumericCellValue());
					}
				}
				System.out.print("\t");
			}
			System.out.println("\n");
		}
	}

	public static List<RowBOs> readWorkbook(String filepath, String sheetName) throws Exception {

		List<RowBOs> rows = new ArrayList<RowBOs>();

		FileInputStream fis = new FileInputStream(new File(filepath));

		 workbook = new XSSFWorkbook(fis);

		 sheet = workbook.getSheet(sheetName);

		Iterator<Row> rowsIterator = sheet.iterator();

		while (rowsIterator.hasNext()) {
			Row row = rowsIterator.next();
			RowBOs rowBO = constructRow(row);
			rows.add(rowBO);
		}
		return rows;
	}

	public static RowBOs constructRow(Row row) {
		RowBOs rowBO = new RowBOs();
		rowBO.setRow(row);
		List<Cell> cells = rowBO.initializeCells();
		Iterator<Cell> cellIterator = row.iterator();//check the variable. From where we should call the Iterator

		while (cellIterator.hasNext()) {
			cells.add(cellIterator.next());
		}
		rowBO.setCells(cells);

		return rowBO;

	}
	
	public static int getRowCount() {
		int rowCount = sheet.getLastRowNum();
		System.out.println(rowCount);
		return rowCount;
		
	}
	
	public static int getCellCount() {
		Row row = sheet.getRow(0);
		int cellCount = row.getLastCellNum();
		System.out.println(cellCount);
		return cellCount;
		
	}

}
