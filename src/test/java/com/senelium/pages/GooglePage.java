package com.senelium.pages;

import com.senelium.element.Element;
import com.senelium.reports.ExtentTestReport;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class GooglePage {

    @Step("Search {0}")
    public void search(String keyword) {
        ExtentTestReport.logMessage("Search " + keyword);
        Element search = new Element(By.cssSelector("textarea[type='search']"));
        search.type(keyword);
    }

    @Step("Verify the search is displayed")
    public boolean isSearchBarDisplayed() {
        ExtentTestReport.logMessage("Verify the search is displayed");
        Element search = new Element(By.cssSelector("textarea[type='search']"));
        return search.isDisplayed();
    }
}
