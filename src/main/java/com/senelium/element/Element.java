package com.senelium.element;

import com.senelium.Senelium;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

    public static Element byXpath(String xpath) {
        return new Element(By.xpath(xpath));
    }

    public static Element byCssSelector(String cssSelector) {
        return new Element(By.cssSelector(cssSelector));
    }

    public static Element byId(String id) {
        return new Element(By.id(id));
    }

    public static Element byClass(String className) {
        return new Element(By.className(className));
    }

    public static Element byLinkText(String link) {
        return new Element(By.linkText(link));
    }

    public static Element byPartialLinkText(String partialLinkText) {
        return new Element(By.partialLinkText(partialLinkText));
    }

    public static Element byTag(String tag) {
        return new Element(By.tagName(tag));
    }

    public static Element byName(String name) {
        return new Element(By.name(name));
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

    public void clickByJs() {
        Senelium.executeJavascript("arguments[0].click();", findElement());
    }

    public void type(String keys) {
        scrollToView();
        findElement().sendKeys(keys);
    }

    public void setValue(String value) {
        Senelium.executeJavascript(String.format("arguments[0].value = \"%s\";", value), findElement());
    }

    public void pressEnter() {
        getActions().sendKeys(Keys.ENTER).perform();
    }

    public boolean isTag(String tagName) {
        return findElement().getTagName().equalsIgnoreCase(tagName);
    }

    public String getText() {
        if (isTag("input") || isTag("textarea")) {
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
        getActions().scrollToElement(findVisibleElement()).perform();
    }

    public void hover() {
        scrollToView();
        getActions().moveToElement(findElement());
    }

    public void waitUntilNotDisplayed() {
        getWaiter().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    private WebDriverWait getWaiter() {
        return Senelium.getDefaultWaiter();
    }

    private WebDriverWait getWaiter(long millis) {
        return Senelium.getSeneDriver().getWaiter(Duration.ofMillis(millis));
    }

    private Actions getActions() {
        return Senelium.getActions();
    }
}
