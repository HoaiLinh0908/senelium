package com.senelium.element;

import org.openqa.selenium.WebElement;

import java.util.List;

public interface Element {
    WebElement findElement();

    List<WebElement> findElements();

    void click();

    void type(String key);

    boolean isDisplayed();
}
