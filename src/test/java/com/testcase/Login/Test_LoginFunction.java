package com.testcase.Login;

import com.Base.DriverInstance;
import com.POM.ActionsLoginPage;
import com.Utils.CaptureScreenshot;
import com.Utils.ReadLinkTest;
import com.Utils.ReadTestLoginData;
import com.Utils.ReadUIElements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import static org.testng.Assert.*;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


public class Test_LoginFunction extends DriverInstance {

    @BeforeClass
    public void goToLoginPage() {
        driver.get(ReadLinkTest.getLink("LOGIN_URL"));
    }

    @Test (priority = 0)
    public void TC01_isEmailTextBoxDisplayed() {
        WebElement emailTextBox = driver.findElement(
                By.xpath(ReadUIElements.getLoginElement("emailTextBox")));
        assertTrue(emailTextBox.isDisplayed(), "Email Textbox is not found");
    }
    @Test (priority = 1, dependsOnMethods = "TC01_isEmailTextBoxDisplayed")
    public void TC02_isEmailTextBoxEnabled() {
        WebElement emailTextBox = driver.findElement(
                By.xpath(ReadUIElements.getLoginElement("emailTextBox")));
        assertTrue(emailTextBox.isEnabled(), "Email Textbox is disabled");
    }
    @Test (priority = 2)
    public void TC03_isPasswordTextBoxDisplayed() {
        WebElement passwordTextBox = driver.findElement(
                By.xpath(ReadUIElements.getLoginElement("passwordTextBox")));
        assertTrue(passwordTextBox.isDisplayed(), "Password Textbox is not found");
    }
    @Test (priority = 3, dependsOnMethods = "TC03_isPasswordTextBoxDisplayed")
    public void TC04_isPasswordTextBoxEnabled() {
        WebElement passwordTextBox = driver.findElement(
                By.xpath(ReadUIElements.getLoginElement("passwordTextBox")));
        assertTrue(passwordTextBox.isEnabled(), "Password Textbox is disabled");
    }
    @Test (priority = 4)
    public void TC05_isLoginButtonDisplayed() {
        WebElement loginButton = driver.findElement(By.xpath(
                ReadUIElements.getLoginElement("logInButton")));
        assertTrue(loginButton.isDisplayed(), "Login button is not found");
    }
    @Test (priority = 5, dependsOnMethods = "TC05_isLoginButtonDisplayed")
    public void TC06_isLoginButtonEnabled() {
        WebElement loginButton = driver.findElement(By.xpath(
                ReadUIElements.getLoginElement("logInButton")));
        assertTrue(loginButton.isEnabled(), "Login button is disabled");
    }

    @Test (dataProvider = "TestLogin", dataProviderClass = ReadTestLoginData.class,
            dependsOnMethods = {"TC02_isEmailTextBoxEnabled", "TC04_isPasswordTextBoxEnabled", "TC06_isLoginButtonEnabled"})
    public void LoginWithAccounts(String email, String password) {
        ActionsLoginPage loginPage = new ActionsLoginPage(driver);
        PageFactory.initElements(driver, loginPage);

        boolean loginFail = false;
        try {
            loginPage.enterEmail(email);
            loginPage.enterPassword(password);
            loginPage.clickLogInButton();

            //Check the logging function is ok or not by the appearance of Log Out button
            WebElement logOutButton = driver.findElement(By.xpath(
                    ReadUIElements.getLoginElement("logOutButton")));

            if (!logOutButton.isDisplayed()) {
                throw new Exception("Fail to login");
            } else {
                loginPage.clickLogOutButton();
            }
        } catch (Exception e) {
            loginFail = true;
            System.out.println(e.getMessage());
        }
        assertFalse(loginFail, "Login failed with: " + email);
    }

    @AfterMethod
    public void takeScreenshot(ITestResult result) {

        if (ITestResult.FAILURE == result.getStatus()) { //result.getStatus() : Success=1/failure=2
            try {
                //{"email":"string"[0] , "password":"string"[1]}
                //Get the 1st param(email) of "result" and cast it to String
                String email =(String) result.getParameters()[0];
                String imageName = "(LoginFunc)-[" + email + "]-(FAIL)";
                CaptureScreenshot.takeScreenshot(driver, imageName);

                //Delete the inputted text inside Username&Password textboxes
                WebElement inputtedEmail = driver.findElement(By.xpath(
                        ReadUIElements.getLoginElement("emailTextBox")));
                WebElement inputtedPassword = driver.findElement(By.xpath(
                        ReadUIElements.getLoginElement("passwordTextBox")));
                inputtedEmail.clear();
                inputtedPassword.clear();

            } catch (Exception e) {
                System.out.println("Unable to create screenshot");
            }
        }
    }
}
