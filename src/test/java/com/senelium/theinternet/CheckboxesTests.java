package com.senelium.theinternet;

import com.senelium.pom.theinternet.CheckboxesPage;
import org.testng.annotations.Test;

public class CheckboxesTests extends TheInternetTestBase {
    CheckboxesPage checkboxesPage = new CheckboxesPage();

    @Test(description = "Interact with checkboxes and verify the status")
    void checkboxesTests() {
        homePage.openPage("Checkboxes");
        //Check the default status
        checkboxesPage.shouldFirstCheckboxStatusCorrect(false);
        checkboxesPage.shouldSecondCheckboxStatusCorrect(true);

        //Select the first checkbox and check its status
        checkboxesPage.setFirstCheckbox(true);
        checkboxesPage.shouldFirstCheckboxStatusCorrect(true);

        //Deselect the first checkbox and check its status
        checkboxesPage.setFirstCheckbox(false);
        checkboxesPage.shouldFirstCheckboxStatusCorrect(false);

        //Select the second checkbox which is already selected and check its status
        checkboxesPage.setSecondCheckbox(true);
        checkboxesPage.shouldSecondCheckboxStatusCorrect(true);
    }
}
