package com.Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.time.Duration;

public class DriverInstance {
    public WebDriver driver;

    @BeforeTest
    @Parameters("browserName")
    public void openBrowser(String browserName) {
        switch (browserName) {

            case "CHROME":
                System.out.println("CHROME is on");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;

            case "FIREFOX":
                System.out.println("FIREFOX is on");
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            case "EDGE":
                System.out.println("EDGE is on");
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            default:
                System.out.println("Fail to start browser [" + browserName + "]");
                break;
        }
    }

    @AfterTest
    public void terminateBrowser() throws Exception {
        Thread.sleep(500);
        driver.quit();
    }
}
