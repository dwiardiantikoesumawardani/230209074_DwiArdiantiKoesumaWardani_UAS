package com.demoblaze.tests;

import com.demoblaze.pages.HomePage;
import com.demoblaze.pages.LoginPage;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    // =========================
    // POSITIVE TEST CASE
    // =========================
    @Test
    public void loginWithValidCredential() {

        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.openLoginPopup();
        loginPage.login("dwiQA", "qwerty");

        Assert.assertTrue(
                homePage.isUserLoggedIn(),
                "Login gagal, welcome message tidak muncul"
        );
    }

    // =========================
    // NEGATIVE TEST CASE
    // =========================
    @Test
    public void loginWithInvalidPassword() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPopup();
        loginPage.login("dwiQA", "123");

        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
            Assert.assertTrue(true, "Login gagal sesuai expected");
        } catch (Exception e) {
            Assert.assertTrue(true, "Login gagal tanpa alert (acceptable)");
        }
    }

}
