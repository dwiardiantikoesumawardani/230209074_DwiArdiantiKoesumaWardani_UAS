package com.demoblaze.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {

    // locator
    private By cartTable = By.id("tbodyid");
    private By placeOrderButton = By.xpath("//button[text()='Place Order']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void clickPlaceOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton)).click();
    }

    public boolean isProductDisplayed(String productName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartTable));
        return driver.getPageSource().contains(productName);
    }
}
