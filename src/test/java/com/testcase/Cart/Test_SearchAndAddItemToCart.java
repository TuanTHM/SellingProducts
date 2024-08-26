package com.testcase.Cart;

import com.Base.DriverInstance;
import com.POM.ActionsProductListPage;
import com.Utils.CaptureScreenshot;
import com.Utils.ReadCartTestData;
import com.Utils.ReadLinkTest;
import com.Utils.ReadUIElements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class Test_SearchAndAddItemToCart extends DriverInstance {

    @BeforeClass
    public void getToProductPage() {
        System.out.println("Thread of [Search Product Test] -> " + Thread.currentThread().threadId());
        driver.get(ReadLinkTest.getLink("PRODUCT_URL"));
    }

    @Test (priority = 1)
    public void TC01_isSearchTextboxDisplayed() {
        WebElement searchTextbox = driver.findElement(
                By.xpath(ReadUIElements.getProductListPageElement("searchTextBox")));
        assertTrue(searchTextbox.isDisplayed(),"Search textbox is not found");
    }
    @Test (priority = 2, dependsOnMethods = "TC01_isSearchTextboxDisplayed")
    public void TC02_isSearchTextBoxEnabled() {
        WebElement searchTextbox = driver.findElement(
                By.xpath(ReadUIElements.getProductListPageElement("searchTextBox")));
        assertTrue(searchTextbox.isEnabled(),"Search textbox is disabled");
    }

    @Test (priority = 3)
    public void TC03_isSubmitSearchButtonDisplayed() {
        WebElement submitSearchButton = driver.findElement(
                By.xpath(ReadUIElements.getProductListPageElement("submitSearchButton")));
        assertTrue(submitSearchButton.isDisplayed(),"Submit search button is not found");
    }
    @Test (priority = 4, dependsOnMethods = "TC03_isSubmitSearchButtonDisplayed")
    public void TC04_isSubmitSearchButtonEnabled() {
        WebElement submitSearchButton = driver.findElement(
                By.xpath(ReadUIElements.getProductListPageElement("submitSearchButton")));
        assertTrue(submitSearchButton.isEnabled(),"Submit search button is disabled");
    }

    @Test (priority = 5, dependsOnMethods = {"TC02_isSearchTextBoxEnabled", "TC04_isSubmitSearchButtonEnabled"})
    public void TC05_performSearchItem() {
        ActionsProductListPage PLP = new ActionsProductListPage(driver);
        PageFactory.initElements(driver, PLP);

        boolean inputItemFail = false;
        try {
            PLP.inputSearchTextBox(ReadCartTestData.getCartData("itemSearch"));
            PLP.submitSearch();
        } catch (Exception e) {
            inputItemFail = true;
        }
        assertFalse(inputItemFail, "Submit search item failed");
    }

    @Test (priority = 6, dependsOnMethods = "TC05_performSearchItem")
    public void TC06_isSearchFunctionPerformed() {
        WebElement searchedProductBox = driver.findElement(
                By.xpath(ReadUIElements.getProductListPageElement("boxSearchedItem")));

        //Scroll down to avoid being blocked by ads
        JavascriptExecutor JS = (JavascriptExecutor) driver;
        JS.executeScript("arguments[0].scrollIntoView();", searchedProductBox);

        assertTrue(searchedProductBox.isDisplayed(),"Perform search function failed");
    }
    @Test(priority = 7, dependsOnMethods = "TC06_isSearchFunctionPerformed")
    public void TC07_isDisplayedItemMatched() {
        WebElement searchedItemName = driver.findElement(
                By.xpath(ReadUIElements.getProductListPageElement("itemNameLocation")));

        String actualItemName = searchedItemName.getText();
        String expectedItemName = ReadCartTestData.getCartData("itemSearch");
        boolean matchedItemName = actualItemName.equalsIgnoreCase(expectedItemName);

        assertTrue(matchedItemName, "Displayed Item is not matched Searched Item");
    }
    @Test (priority = 8, dependsOnMethods = "TC07_isDisplayedItemMatched")
    public void TC08_isInfomationOfItemDisplayed() { //Verify if the item's name, price, image are displayed
        SoftAssert softAssert = new SoftAssert();

        WebElement searchedItemName = driver.findElement(
                By.xpath(ReadUIElements.getProductListPageElement("itemNameLocation")));
        softAssert.assertTrue(searchedItemName.isDisplayed(), "Name of Item is not displayed");

        WebElement searchedItemImage = driver.findElement(
                By.xpath(ReadUIElements.getProductListPageElement("itemImageLocation")));
        softAssert.assertTrue(searchedItemImage.isDisplayed(), "Image of Item is not displayed");

        WebElement searchedItemPrice = driver.findElement(
                By.xpath(ReadUIElements.getProductListPageElement("itemPriceLocation")));
        softAssert.assertTrue(searchedItemPrice.isDisplayed(), "Image of Item is not displayed");

        softAssert.assertAll();
    }

    @Test (priority = 9, dependsOnMethods = "TC06_isSearchFunctionPerformed")
    public void TC09_isAddToCartIconDisplayed() {
        WebElement addToCartButton = driver.findElement(
                By.xpath(ReadUIElements.getProductListPageElement("addToCartButton")));
        assertTrue(addToCartButton.isDisplayed(),"Add To Cart button is not found!");
    }
    @Test (priority = 10, dependsOnMethods = "TC09_isAddToCartIconDisplayed")
    public void TC10_isAddToCartIconEnabled() {
        WebElement addToCartButton = driver.findElement(
                By.xpath(ReadUIElements.getProductListPageElement("addToCartButton")));
        assertTrue(addToCartButton.isEnabled(),"Add To Cart button is disabled!");
    }

    @Test (priority = 11, dependsOnMethods = "TC10_isAddToCartIconEnabled")
    public void TC11_AddItemToCart() {
        boolean addItemToCartFail = false;
        try {
            WebElement addToCartButton = driver.findElement(
                    By.xpath(ReadUIElements.getProductListPageElement("addToCartButton")));
            addToCartButton.click();
        } catch (Exception e) {
            addItemToCartFail = true;
        }
        assertFalse(addItemToCartFail,"Add item to cart failed");

    }

    @Test(priority = 12, dependsOnMethods = "TC11_AddItemToCart")
    public void TC12_isMessageDisplayed() {
        WebElement notificationBox = driver.findElement(
                By.xpath(ReadUIElements.getProductListPageElement("notificationBox")));
        assertTrue(notificationBox.isEnabled(), "Message is not displayed");
    }

    @Test (priority = 13, dependsOnMethods = "TC12_isMessageDisplayed")
    public void TC13_isMessageDispayedAsRequired() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement addToCartSuccessMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(ReadUIElements.getProductListPageElement("messageLocation"))));

        String expectedMessage = ReadCartTestData.getCartData("addCartSuccessMessage");
        String actualMessage = addToCartSuccessMessage.getText();

        boolean matchedMessage = actualMessage.equalsIgnoreCase(expectedMessage);
        assertTrue(matchedMessage, "Incorrect message");
    }

    @Test (priority = 14, dependsOnMethods = "TC12_isMessageDisplayed")
    public void TC14_isViewCartDisplayed() {
        WebElement viewCartButton = driver.findElement(
                By.xpath(ReadUIElements.getProductListPageElement("viewCartLocation")));
        assertTrue(viewCartButton.isDisplayed(), "View Cart button is not found");
    }
    @Test (priority = 15, dependsOnMethods = "TC14_isViewCartDisplayed")
    public void TC15_isViewCartClickable() {
        WebElement viewCartButton = driver.findElement(
                By.xpath(ReadUIElements.getProductListPageElement("viewCartLocation")));
        assertTrue(viewCartButton.isEnabled(), "View Cart button is not clickable");
    }

    @Test(priority = 16, dependsOnMethods = "TC11_AddItemToCart")
    public void TC16_isTheItemInCart() {
        WebElement viewCartButton = driver.findElement(
                By.xpath(ReadUIElements.getProductListPageElement("viewCartLocation")));
        viewCartButton.click();

        WebElement addedItemName = driver.findElement(
                By.xpath(ReadUIElements.getProductListPageElement("AddedItemNameInCart")));
        String actualItemName = addedItemName.getText();
        String expectedItemName = ReadCartTestData.getCartData("itemSearch");

        boolean matchedItemName = actualItemName.equalsIgnoreCase(expectedItemName);
        assertTrue(matchedItemName, "Item in cart is not correct");
    }

    @AfterMethod
    public void takeScreenshot(ITestResult result) throws InterruptedException {
        Thread.sleep(1000);
        if (ITestResult.FAILURE == result.getStatus()) {
            String imageName = "[Cart]-" + result.getName();
            try {
                CaptureScreenshot.takeScreenshot(driver, imageName);
            } catch (Exception e) {
                System.out.println("Cannot take screenshot of " + result.getName());
            }
        }
    }
}
