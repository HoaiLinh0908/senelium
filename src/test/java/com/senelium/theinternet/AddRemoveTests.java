package com.senelium.theinternet;

import com.senelium.pom.theinternet.AddRemovePage;
import com.senelium.pom.theinternet.HomePage;
import org.testng.annotations.Test;

public class AddRemoveTests extends TheInternetTestBase {
    final AddRemovePage addRemovePage = new AddRemovePage();

    @Test
    void testAddRemoveElement() {
        homePage.openPage("Add/Remove Elements");
        addRemovePage.clickAddElementButton();
        addRemovePage.shouldDeleteButtonVisible();

        addRemovePage.clickDeleteButton();
        addRemovePage.shouldDeleteButtonNotVisible();
    }
}
