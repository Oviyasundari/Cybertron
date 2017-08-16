package com.athena.frametest.utils;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelReader {
	File src;
	XSSFWorkbook workbook;
	XSSFSheet sheet;

	public ExcelReader(String excelfilepath) {
		src = new File(excelfilepath);
		try {
			FileInputStream file = new FileInputStream(src);
			workbook = new XSSFWorkbook(file);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public String[][] getExcelSheetData(String sheetname) {
		sheet = workbook.getSheet(sheetname);
		 return getData();
	}

		public String[][] getExcelSheetData(int sheetindex) {
		sheet = workbook.getSheetAt(sheetindex);
		 return getData();
	}
	
		public String[][] getData()
		{
			int lastRowNum = sheet.getLastRowNum();
			String[][] data = new String[lastRowNum+1][];
			
			for(int row = 1;row <= lastRowNum; row++)
			{
				data[row-1] = getRowData(row);
			}
			
			return data;
		}

	public String[] getRowData(int rowIndex) {
		String excelData = "";
		int lastCellNum = sheet.getRow(rowIndex).getLastCellNum();
		for (int col = 0; col < lastCellNum; col++) {
			if (sheet.getRow(rowIndex).getCell(col).getCellType() == XSSFCell.CELL_TYPE_STRING)
				excelData = excelData + sheet.getRow(rowIndex).getCell(col).getStringCellValue() + "  ";

			else if (sheet.getRow(rowIndex).getCell(col).getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
				if (DateUtil.isCellDateFormatted(sheet.getRow(rowIndex).getCell(col))) {
					SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
					excelData = excelData + sdf.format(sheet.getRow(rowIndex).getCell(col).getDateCellValue()) + "  ";
				} else
				{
					excelData = excelData + new DataFormatter().formatCellValue(sheet.getRow(rowIndex).getCell(col))+ "  ";
				}
			} else if (sheet.getRow(rowIndex).getCell(col).getCellType() == XSSFCell.CELL_TYPE_BOOLEAN)
				excelData = excelData + sheet.getRow(rowIndex).getCell(col).getBooleanCellValue() + "  ";

		}
		String[] excelDataArray = excelData.split("  ");
		return excelDataArray;
	}
	
}
