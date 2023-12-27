package com.senelium.test;

import com.senelium.Senelium;
import com.senelium.assertion.TestAssert;
import com.senelium.pages.GooglePage;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GoogleTests extends TestBase {
    GooglePage googlePage = new GooglePage();

    @BeforeClass(alwaysRun = true)
    void beforeClass() {
        Senelium.open("https://www.google.com");
        googlePage.search("Christmas");
    }

    @Test(description = "Test Google")
    void googleTest() {
        TestAssert test = googlePage.isSearchBarContains("Christmas");
        Assert.assertTrue(test.getResult(), test.getErrors());
    }

    @Test(description = "Failed Test")
    void failedTest() {
        TestAssert test = googlePage.isSearchBarContains("New year");
        test = googlePage.isSearchBarContains("Hola");
        Assert.assertTrue(test.getResult(), test.getErrors());
    }

    @Test(description = "Skipped Test")
    void skippedTest() {
        throw new SkipException("Skip this test!");
    }
}
