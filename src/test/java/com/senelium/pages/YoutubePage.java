package com.senelium.pages;

import com.senelium.assertion.TestAssert;
import com.senelium.element.Element;
import org.openqa.selenium.By;

public class YoutubePage {
    Element searchBar = new Element(By.cssSelector("input#search"));
    Element searchResult = new Element(By.cssSelector("ytd-app > div#content"));

    public void search(String keyword) {
        searchBar.type(keyword);
        searchBar.pressEnter();
    }

    public TestAssert isSearchResultDisplayed() {
        TestAssert testAssert = new TestAssert();
        testAssert.assertTrue(searchResult.isDisplayed(), "Search result");
        return testAssert;
    }
}
