package com.Utils;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadTestLoginData {

    @DataProvider(name = "TestLogin")
    public Object[][] getData() throws IOException {
        FileInputStream fileTest = new FileInputStream("./TestData/LoginTest/TestLoginData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fileTest);
        XSSFSheet sheet = workbook.getSheet("Login");

        int numberOfRow = sheet.getPhysicalNumberOfRows();

        Object[][] data = new Object[numberOfRow-1][2]; //Get data only in 2 first columns on each rows

        for (int i = 1; i < numberOfRow; i++) { //Lấy từ row số 2
            XSSFRow row = sheet.getRow(i);
            XSSFCell username = row.getCell(0); //Set username = value of 1st cell of row(i)
            XSSFCell password = row.getCell(1); //Set password = value of 2nd cell of row(i)

            if (username != null) {
                data[i-1][0] = username.getStringCellValue();
            } else {
                data[i-1][0] = "";
            }

            if (password != null) {
                data[i-1][1] = password.getStringCellValue();
            } else {
                data[i-1][1] = "";
            }
        }
        return data;

    }

}
