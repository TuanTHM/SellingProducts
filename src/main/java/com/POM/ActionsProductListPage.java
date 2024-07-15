package com.POM;

import com.Utils.ReadUIElements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ActionsProductListPage {

    protected WebDriverWait wait;

    public ActionsProductListPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void inputSearchTextBox(String itemName) {
        WebElement searchTextBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(ReadUIElements.getProductListPageElement("searchTextBox"))));
        searchTextBox.sendKeys(itemName);
    }

    public void submitSearch() {
        WebElement submitSearchButtonn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(ReadUIElements.getProductListPageElement("submitSearchButton"))));
        submitSearchButtonn.click();
    }

}
