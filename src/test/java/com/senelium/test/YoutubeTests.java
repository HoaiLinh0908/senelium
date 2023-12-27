package com.senelium.test;

import com.senelium.Senelium;
import com.senelium.assertion.TestAssert;
import com.senelium.pages.YoutubePage;
import org.testng.Assert;
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
        TestAssert testAssert = youtube.isSearchResultDisplayed();
        Assert.assertTrue(testAssert.getResult(), testAssert.getErrors());
    }
}
