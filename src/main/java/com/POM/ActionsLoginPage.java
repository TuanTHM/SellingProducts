package com.POM;

import com.Utils.ReadUIElements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ActionsLoginPage {

    protected WebDriverWait wait;
    public ActionsLoginPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void enterEmail(String email) throws Exception {
        WebElement emailTextBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(ReadUIElements.getLoginElement("emailTextBox"))));
        emailTextBox.sendKeys(email);
        Thread.sleep(500);
    }

    public void enterPassword(String password) throws Exception {
        WebElement passwordTextbox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(ReadUIElements.getLoginElement("passwordTextBox"))));
        passwordTextbox.sendKeys(password);
        Thread.sleep(500);
    }

    public void clickLogInButton() throws Exception {
        WebElement logInButton = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(ReadUIElements.getLoginElement("logInButton"))));
        logInButton.click();
        Thread.sleep(500);
    }

    public void clickLogOutButton() throws Exception {
        WebElement logOutButton = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(ReadUIElements.getLoginElement("logOutButton"))));
        Thread.sleep(500);
        logOutButton.click();
    }
}
