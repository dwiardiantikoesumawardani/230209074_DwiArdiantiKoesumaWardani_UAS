package com.demoblaze.tests;

import com.demoblaze.pages.HomePage;
import com.demoblaze.pages.LoginPage;
import com.demoblaze.pages.ProductPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class CheckoutTest extends BaseTest {

    @Test
    public void checkoutProductSuccessfully() {

        // ===== LOGIN =====
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.openLoginPopup();
        loginPage.login("dwiQA", "qwerty");

        // ===== ADD PRODUCT =====
        homePage.openProductPageByUrl(
                "https://www.demoblaze.com/prod.html?idp_=1"
        );

        ProductPage productPage = new ProductPage(driver);
        productPage.clickAddToCart();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();

        // ===== MASUK CART =====
        homePage.clickCart();

        // ===== PLACE ORDER =====
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[text()='Place Order']"))).click();

        // ===== ISI FORM ORDER =====
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).sendKeys("Dwi");
        driver.findElement(By.id("country")).sendKeys("Indonesia");
        driver.findElement(By.id("city")).sendKeys("Jakarta");
        driver.findElement(By.id("card")).sendKeys("123456789");
        driver.findElement(By.id("month")).sendKeys("12");
        driver.findElement(By.id("year")).sendKeys("2026");

        driver.findElement(By.xpath("//button[text()='Purchase']")).click();

        // ===== VALIDASI CHECKOUT =====
        boolean isSuccess = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h2[text()='Thank you for your purchase!']")
                )
        ).isDisplayed();

        Assert.assertTrue(isSuccess, "Checkout gagal, pesan sukses tidak muncul");
    }
}
