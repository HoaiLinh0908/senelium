package com.senelium.pages;

import com.senelium.element.Element;
import io.qameta.allure.Step;

public class GooglePage {

    private final Element searchBar = Element.byCssSelector("textarea[type='search']");

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
    public boolean isSearchBarContains(String keyword) {
        return searchBar.getText().contains(keyword);
    }
}
