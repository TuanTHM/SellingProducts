package com.POM;

import com.Utils.ReadUIElements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ActionsLoginPage {

    public WebDriver driver;
    public ActionsLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterEmail(String email) throws Exception {
        WebElement emailTextBox = driver.findElement(
                        By.xpath(ReadUIElements.getLoginElement("emailTextBox")));
        Thread.sleep(500);
        emailTextBox.sendKeys(email);
    }

    public void enterPassword(String password) throws Exception {
        WebElement passwordTextbox = driver.findElement(
                        By.xpath(ReadUIElements.getLoginElement("passwordTextBox")));
        Thread.sleep(500);
        passwordTextbox.sendKeys(password);
    }

    public void clickLogInButton() throws Exception {
        WebElement logInButton = driver.findElement(
                        By.xpath(ReadUIElements.getLoginElement("logInButton")));
        Thread.sleep(500);
        logInButton.click();
    }

    public void clickLogOutButton() throws Exception {
        WebElement logOutButton = driver.findElement(
                        By.xpath(ReadUIElements.getLoginElement("logOutButton")));
        Thread.sleep(500);
        logOutButton.click();
    }
}
