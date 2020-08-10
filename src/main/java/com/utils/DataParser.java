package com.utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DataParser {
    static XSSFWorkbook workbook;
    static XSSFSheet sheet;
    XSSFCell cell;
    public static DataFormatter formatter = new DataFormatter();

    public static Object[][] readExcel(String sheetName) {

        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(System.getProperty("user.dir") + "\\testData\\DataSheet.xlsx");
            workbook = new XSSFWorkbook(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = workbook.getSheet(sheetName);
        XSSFRow Row = sheet.getRow(0);

        int RowNum = sheet.getPhysicalNumberOfRows();
        int ColNum = Row.getLastCellNum();
        Object dataSet[][] = new Object[RowNum - 1][ColNum];

        for (int i = 0; i < RowNum - 1; i++) {
            XSSFRow row = sheet.getRow(i + 1);

            for (int j = 0; j < ColNum; j++) {
                if (row == null)
                    break;
                else {
                    XSSFCell cell = row.getCell(j);
                    if (cell == null)
                        dataSet[i][j] = "";
                    else {
                        String value = formatter.formatCellValue(cell);
                        dataSet[i][j] = value;
                    }
                }

            }

        }
        return dataSet;
    }


}
