package com.senelium.pages;

import com.senelium.assertion.TestAssert;
import com.senelium.element.Element;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class GooglePage {

    @Step("Search {0}")
    public void search(String keyword) {
        Element search = new Element(By.cssSelector("textarea[type='search']"));
        search.type(keyword);
        search.pressEnter();
    }

    @Step("Verify the search is displayed")
    public boolean isSearchBarDisplayed() {
        Element search = new Element(By.cssSelector("textarea[type='search']"));
        return search.isDisplayed();
    }

    @Step("Verify search bar contains {0}")
    public TestAssert isSearchBarContains(String keyword) {
        TestAssert testAssert = new TestAssert();
        Element search = new Element(By.cssSelector("textarea[type='search']"));
        testAssert.checkEqual(search.getText(), keyword, "Search bar does not contains " + keyword + " but " + search.getText());
        return testAssert;
    }
}
