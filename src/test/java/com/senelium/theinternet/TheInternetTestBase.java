package com.senelium.theinternet;

import com.senelium.TestBase;
import com.senelium.pom.theinternet.HomePage;
import org.testng.annotations.BeforeMethod;

public class TheInternetTestBase extends TestBase {
    final HomePage homePage = new HomePage();

    @BeforeMethod(alwaysRun = true)
    void beforeEachTheInternetTest() {
        open(testConfig.getTheInternetUrl());
    }
}
