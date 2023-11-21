package com.senelium.element;

import com.senelium.Senelium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class BaseElement implements Element {
    protected By locator;

    @Override
    public WebElement findElement() {
        return Senelium.getWebDriver().findElement(locator);
    }

    @Override
    public List<WebElement> findElements() {
        return Senelium.getWebDriver().findElements(locator);
    }

    @Override
    public boolean isDisplayed() {
        return findElement().isDisplayed();
    }
}
