package com.demoblaze.tests;

import com.demoblaze.pages.CartPage;
import com.demoblaze.pages.HomePage;
import com.demoblaze.pages.LoginPage;
import com.demoblaze.pages.ProductPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddToCartTest extends BaseTest {

    @Test
    public void addProductToCartSuccessfully() {

        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.openLoginPopup();
        loginPage.login("dwiQA", "qwerty");

        homePage.openProductPageByUrl(
                "https://www.demoblaze.com/prod.html?idp_=1"
        );

        ProductPage productPage = new ProductPage(driver);
        productPage.clickAddToCart();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        Assert.assertTrue(
                alert.getText().contains("Product added"),
                "Produk gagal ditambahkan ke cart"
        );

        alert.accept();
    }

}
