package com.Utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadLinkTest {
    private static final String LINK_TESTDATA = "./TestData/LinkTest/LinkTest.properties";

    public static String getLink(String nameOfLink) {
        try {
            Properties properties = new Properties();
            FileInputStream file = new FileInputStream(LINK_TESTDATA);
            properties.load(file);
            return properties.getProperty(nameOfLink);

        } catch (Exception e) {
            System.out.println("ERROR - Cannot read data of " + nameOfLink);
            return null;
        }
    }
}
