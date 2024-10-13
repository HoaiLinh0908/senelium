package com.senelium.theinternet;

import com.senelium.TestBase;
import com.senelium.pom.theinternet.AddRemovePage;
import com.senelium.pom.theinternet.HomePage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddRemoveTests extends TestBase {
    final HomePage homePage = new HomePage();
    final AddRemovePage addRemovePage = new AddRemovePage();

    @BeforeClass
    void beforeClass() {
        open(testConfig.getTheInternetUrl());
    }

    @Test
    void testAddRemoveElement() {
        homePage.openPage("Add/Remove Elements");
        addRemovePage.clickAddElementButton();
        addRemovePage.shouldDeleteButtonVisible();

        addRemovePage.clickDeleteButton();
        addRemovePage.shouldDeleteButtonNotVisible();
    }
}
