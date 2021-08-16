package com.restgauge.utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    static XSSFWorkbook workbook;
    static XSSFSheet sheet;

    public ExcelUtils(String excelPath, String sheetName) {
        try {
            workbook = new XSSFWorkbook(excelPath);
            sheet = workbook.getSheet(sheetName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getRowCount(){
        int rowCount = sheet.getPhysicalNumberOfRows();
        System.out.println("Satir sayisi: " + rowCount);
    }
    public static void getSheetName(){
        String sheetName = sheet.getSheetName();
        System.out.println("Sayfa adi: " + sheetName);
    }

    public static String getCellData(int rowNum, int colNum){
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(sheet.getRow(rowNum).getCell(colNum));
    }
}
