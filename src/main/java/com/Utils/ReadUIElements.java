package com.Utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadUIElements {
    private static final String PRODUCTLISTPAGE_PATH = "./UI_Elements/UI_ProductListPage.properties";
    private static final String LOGINPAGE_PATH = "./UI_Elements/UI_Login.properties";

    public static String getProductListPageElement(String nameOfElement) {
        try {
            Properties properties = new Properties();
            FileInputStream file = new FileInputStream(PRODUCTLISTPAGE_PATH);
            properties.load(file);
            return properties.getProperty(nameOfElement);

        } catch (Exception e) {
            System.out.println("ERROR - Cannot read data of " + nameOfElement);
            return null;
        }
    }

    public static String getLoginElement(String nameOfElement) {
        try {
            Properties properties = new Properties();
            FileInputStream file = new FileInputStream(LOGINPAGE_PATH);
            properties.load(file);
            return properties.getProperty(nameOfElement);

        } catch (Exception e) {
            System.out.println("ERROR - Cannot read data of " + nameOfElement);
            return null;
        }
    }
}