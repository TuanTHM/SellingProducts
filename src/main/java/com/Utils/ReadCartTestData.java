package com.Utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadCartTestData {
    private static final String CART_TESTDATA = "./TestData/AddToCartTest/CartTestData.properties";

    public static String getCartData(String nameOfData) {
        try {
            Properties properties = new Properties();
            FileInputStream file = new FileInputStream(CART_TESTDATA);
            properties.load(file);
            return properties.getProperty(nameOfData);

        } catch (Exception e) {
            System.out.println("ERROR - Cannot read data of " + nameOfData);
            return null;
        }
    }
}
