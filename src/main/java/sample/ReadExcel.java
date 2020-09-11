package sample;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	private static String readFileName = "S:\\FinalTry\\MakeMyTrip.xlsx";
	
	public static void main(String[] args) {
		List<RowBO> rowBOs = readFile(readFileName);
		printExcel(rowBOs);
	}
	
	 private static void printExcel(List<RowBO> rowBOs) {
		for(RowBO rowBO : rowBOs) {
			for(Cell cell : rowBO.getCells()) {
				CellType cellType = cell.getCellType();
				
				if(cellType == CellType.BOOLEAN) {
					System.out.print(cell.getBooleanCellValue());
				} else if(cellType == CellType.STRING) {
					System.out.print(cell.getStringCellValue());
				} else if(cellType == CellType.NUMERIC) {
					if(DateUtil.isCellInternalDateFormatted(cell)) {
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
	 
	 //iruku 

	private static List<RowBO> readFile(String readFileName) {
		List<RowBO> rows = new ArrayList<RowBO>();
		 try {
			FileInputStream fi = new FileInputStream(new File(readFileName));
			XSSFWorkbook workbook = new XSSFWorkbook(fi);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowsIterator = sheet.iterator();
			while(rowsIterator.hasNext()) {
				Row row = rowsIterator.next();
				RowBO rowBO = constructRow(row);
				rows.add(rowBO);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rows;
	}

	private static RowBO constructRow(Row row) {
		RowBO rowBO = new RowBO();
		rowBO.setRow(row);
		
		
		List<Cell> cells = rowBO.initializeCellsT();
		Iterator<Cell> cellIterator = row.cellIterator();
		
		while(cellIterator.hasNext()) {
			cells.add(cellIterator.next());
		}
		
		rowBO.setCells(cells);
		return rowBO;
	}
}
