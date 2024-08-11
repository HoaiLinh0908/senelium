package com.senelium.swaglabs;

import com.senelium.Senelium;
import com.senelium.TestBase;
import com.senelium.assertion.TestAssert;
import com.senelium.pom.swaglabs.LoginPage;
import com.senelium.pom.swaglabs.ProductsPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @BeforeMethod(alwaysRun = true)
    public void beforeClass() {
        Senelium.open(config.getSwagLabsUrl());
    }

    @Test(description = "Users can login successfully.")
    void loginSuccessfulTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage();
        TestAssert.assertTrue(productsPage.isPageTitleDisplayed(), "The Products page is not displayed");
    }
}
