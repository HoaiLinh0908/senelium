package com.senelium.element;

import com.senelium.Senelium;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@Getter
public class Element {
    By locator;

    public Element(By locator) {
        this.locator = locator;
    }

    public WebElement findElement() {
        return getWaiter().until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement findVisibleElement() {
        return getWaiter().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public List<WebElement> findElements() {
        return getWaiter().until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public List<WebElement> findVisibleElements() {
        return getWaiter().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public boolean isDisplayed() {
        return findElement().isDisplayed();
    }

    public void click() {
        scrollToView();
        findElement().click();
    }

    public void type(String keys) {
        scrollToView();
        findElement().sendKeys(keys);
    }

    public boolean isTag(String tagName) {
        return findElement().getTagName().equalsIgnoreCase(tagName);
    }

    public String getText() {
        if (isTag("input")) {
            return getAttribute("value");
        }
        return findElement().getText();
    }

    public String getAttribute(String name) {
        return findElement().getAttribute(name);
    }

    public String getProperty(String name) {
        return findElement().getDomProperty(name);
    }

    public boolean isEnabled() {
        return findElement().isEnabled();
    }

    public boolean isSelected() {
        return findElement().isSelected();
    }

    public void scrollToView() {
        getActions().scrollToElement(findElement()).perform();
    }

    public void hover() {
        scrollToView();
        getActions().moveToElement(findElement());
    }

    public void waitUntilNotDisplayed() {
        getWaiter().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    private WebDriverWait getWaiter() {
        return Senelium.getSeneDriver().getDefaultWaiter();
    }

    private WebDriverWait getWaiter(long millis) {
        return Senelium.getSeneDriver().getWaiter(Duration.ofMillis(millis));
    }

    private Actions getActions() {
        return Senelium.getSeneDriver().getActions();
    }
}
