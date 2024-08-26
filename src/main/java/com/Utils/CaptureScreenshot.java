package com.Utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import java.io.File;

public class CaptureScreenshot {

    private static String fileName;

    public static void takeScreenshot(WebDriver driver, String imageName) {
        try {
            //Check if dir-screenshot is not created, create a directory name "Screenshots"
            File directory = new File("./CasesFailed-Screenshots");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            TakesScreenshot screenshot = (TakesScreenshot) driver;

            fileName = "./CasesFailed-Screenshots/" + imageName + ".png";
            File fileSrc = screenshot.getScreenshotAs(OutputType.FILE);
            File fileDes = new File(fileName);

            FileUtils.copyFile(fileSrc,fileDes);
        } catch (Exception e) {
            System.out.println("Unable to take the screenshot");
        }
        attachScreenshot();
    }
    public static void attachScreenshot() {
        try {
            System.setProperty("org.uncommons.reportng.escape-output", "false");
            //Get the file by the path of the image
            File file = new File(fileName);

            Reporter.log("<br><a title = \"Screenshot\" href=\"" + file.getAbsolutePath() + "\">" +
                    "<img alt='" + file.getName() + "'src='" + file + "'height='240' width='418' </a><br>");
        } catch (Exception e) {
            System.out.println("Unable to attach the screenshot");
        }
    }
}
