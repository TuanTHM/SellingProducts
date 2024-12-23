package com.POM;

import com.Utils.ReadUIElements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ActionsProductListPage {

    public WebDriver driver;
    public ActionsProductListPage(WebDriver driver) {
        this.driver = driver;
    }

    public void inputSearchTextBox(String itemName) throws Exception {
        WebElement searchTextBox = driver.findElement(
                By.xpath(ReadUIElements.getProductListPageElement("searchTextBox")));
        Thread.sleep(500);
        searchTextBox.sendKeys(itemName);
    }

    public void submitSearch() throws Exception {
        WebElement submitSearchButtonn = driver.findElement(
                By.xpath(ReadUIElements.getProductListPageElement("submitSearchButton")));
        Thread.sleep(500);
        submitSearchButtonn.click();
    }

}
