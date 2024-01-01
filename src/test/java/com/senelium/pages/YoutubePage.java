package com.senelium.pages;

import com.senelium.element.Element;

public class YoutubePage {
    Element searchBar = Element.byCssSelector("input#search");
    Element searchResult = Element.byCssSelector("ytd-app > div#content");

    public void search(String keyword) {
        searchBar.type(keyword);
        searchBar.pressEnter();
    }

    public boolean isSearchResultDisplayed() {
        return searchResult.isDisplayed();
    }
}
