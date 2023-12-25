package com.senelium.pages;

import com.senelium.element.Element;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class GooglePage {

    @Step("Search {0}")
    public void search(String keyword) {
        Element search = new Element(By.cssSelector("textarea[type='search']"));
        search.type(keyword);
    }

    @Step("Verify the search is displayed")
    public boolean isSearchBarDisplayed() {
        Element search = new Element(By.cssSelector("textarea[type='search']"));
//        Allure.addAttachment("Any text", new ByteArrayInputStream(((TakesScreenshot) Senelium.getDriver()).getScreenshotAs(OutputType.BYTES)));
//        Allure.addAttachment("Message", "This test 001 is failed");
//        Allure.addAttachment("Message", "This test 001 is failed");
        return search.isDisplayed();
    }
}
