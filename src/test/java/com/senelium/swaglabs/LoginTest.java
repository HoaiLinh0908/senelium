package com.senelium.swaglabs;

import com.senelium.TestBase;
import com.senelium.pom.swaglabs.LoginPage;
import com.senelium.pom.swaglabs.ProductsPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {
    ProductsPage productsPage = new ProductsPage();

    @BeforeMethod(alwaysRun = true)
    public void beforeClass() {
        open(testConfig.getSwagLabsUrl());
    }

    @Test(description = "Users can login successfully.")
    void loginSuccessfulTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.isPageTitleVisible();
    }
}
