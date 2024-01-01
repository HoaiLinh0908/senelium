package com.senelium.test;

import com.senelium.Senelium;
import com.senelium.assertion.TestAssert;
import com.senelium.pages.GooglePage;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GoogleTests extends TestBase {
    GooglePage googlePage = new GooglePage();

    @BeforeClass
    void beforeClass() {
        Senelium.open("https://www.google.com");
        googlePage.search("Christmas");
    }

    @Test(description = "Test Google")
    void googleTest() {
        TestAssert test = new TestAssert();
        test.assertTrue(googlePage.isSearchBarContains("Christmas"), "Search bar contains Christmas");
        test.assertAll();
    }

    @Test(description = "Failed Test")
    void failedTest() {
        TestAssert test = new TestAssert();

        test.assertTrue(googlePage.isSearchBarContains("New year"), "Search bar contains New year");
        test.assertTrue(googlePage.isSearchBarContains("Hola"), "Search bar contains Hola");
        test.assertAll();
    }

    @Test(description = "Skipped Test")
    void skippedTest() {
        throw new SkipException("Skip this test!");
    }
}
