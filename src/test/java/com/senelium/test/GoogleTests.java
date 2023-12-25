package com.senelium.test;

import com.senelium.Senelium;
import com.senelium.pages.GooglePage;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class GoogleTests extends TestBase {
    GooglePage googlePage = new GooglePage();

    @BeforeClass(alwaysRun = true)
    void beforeClass() {
        Senelium.open("https://www.google.com");
    }

    @Test(description = "Test Google")
    void googleTest() {
        googlePage.search("Christmas");
    }

    @Test(description = "Failed Test")
    void failedTest() {
        Assert.assertTrue(!googlePage.isSearchBarDisplayed(), "This test failed");
    }

    @Test(description = "Skipped Test")
    void skippedTest() {
        throw new SkipException("Skip this test!");
    }

    @AfterClass
    void afterClass() {
        Senelium.sleep(Duration.ofSeconds(2));
        Senelium.closeBrowser();
    }
}
