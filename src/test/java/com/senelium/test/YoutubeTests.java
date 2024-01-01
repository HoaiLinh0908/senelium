package com.senelium.test;

import com.senelium.Senelium;
import com.senelium.assertion.TestAssert;
import com.senelium.pages.YoutubePage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class YoutubeTests extends TestBase {
    YoutubePage youtube = new YoutubePage();

    @BeforeClass(alwaysRun = true)
    void beforeClass() {
        Senelium.open("https://www.youtube.com");
    }

    @Test(description = "Verify Youtube search")
    void youtubeTest() {
        youtube.search("Christmas");
        TestAssert testAssert = new TestAssert();
        testAssert.assertTrue(youtube.isSearchResultDisplayed(), "Search result is displayed");
        testAssert.assertAll();
    }
}
