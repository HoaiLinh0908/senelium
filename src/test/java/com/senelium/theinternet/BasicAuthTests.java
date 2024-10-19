package com.senelium.theinternet;

import com.senelium.pom.theinternet.BasicAuthPage;
import org.testng.annotations.Test;

public class BasicAuthTests extends TheInternetTestBase {
    final BasicAuthPage basicAuthPage = new BasicAuthPage();

    @Test
    void basicAuthTest() {
        homePage.openPage("Basic Auth");
        basicAuthPage.signIn("admin", "admin");
        basicAuthPage.shouldBasicAuthCongratulationDisplay();
    }
}
