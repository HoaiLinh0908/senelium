package com.senelium.theinternet;

import com.senelium.pom.theinternet.ChallengingDomPage;
import org.testng.annotations.Test;

public class ChallengingDOMTests extends TheInternetTestBase {
    ChallengingDomPage challengingDomPage = new ChallengingDomPage();

    @Test
    void tableTests() {
        homePage.openPage("Challenging DOM");
        challengingDomPage.shouldHeaderDisplay("Amet");
        challengingDomPage.shouldCellDisplayUnderHeader("Dolor", "Adipisci5");
    }
}
