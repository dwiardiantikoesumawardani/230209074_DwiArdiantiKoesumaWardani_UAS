package com.demoblaze.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    private By welcomeUser = By.id("nameofuser");
    private By cartMenu = By.id("cartur");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isUserLoggedIn() {
        try {
            return find(welcomeUser).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getWelcomeText() {
        return find(welcomeUser).getText();
    }

    public void openProductPageByUrl(String productUrl) {
        driver.get(productUrl);
    }

    public void clickCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartMenu)).click();
    }
}
