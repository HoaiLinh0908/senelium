package com.senelium.theinternet;

import com.senelium.pom.theinternet.BrokenImagesPage;
import org.testng.annotations.Test;

public class BrokenImagesTests extends TheInternetTestBase {
    BrokenImagesPage brokenImagesPage = new BrokenImagesPage();

    @Test
    void brokenImagesTests() {
        homePage.openPage("Broken Images");
        brokenImagesPage.shouldImageNotBroken("img/avatar-blank.jpg");
        brokenImagesPage.shouldImageNotBroken("asdf.jpg");
    }
}
