package com.senelium.pages;

import com.senelium.assertion.TestAssert;
import com.senelium.element.Element;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class GooglePage {

    private final Element searchBar = new Element(By.cssSelector("textarea[type='search']"));

    @Step("Search {0}")
    public void search(String keyword) {
        searchBar.type(keyword);
        searchBar.pressEnter();
    }

    @Step("Verify the search is displayed")
    public boolean isSearchBarDisplayed() {
        return searchBar.isDisplayed();
    }

    @Step("Verify search bar contains {0}")
    public TestAssert isSearchBarContains(String keyword) {
        TestAssert testAssert = new TestAssert();
        testAssert.assertEqual(searchBar.getText(), keyword, "Search bar");
        return testAssert;
    }
}
