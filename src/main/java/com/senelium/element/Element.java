package com.senelium.element;

import com.senelium.Senelium;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class Element {
    private final By locator;

    public Element(By locator) {
        this.locator = locator;
    }

    public static Element byXpath(String xpath) {
        return new Element(By.xpath(xpath));
    }

    public static Element byXpath(String xpath, String... formatArgs) {
        return new Element(By.xpath(String.format(xpath, formatArgs)));
    }

    public static Element byCssSelector(String cssSelector) {
        return new Element(By.cssSelector(cssSelector));
    }

    public static Element byCssSelector(String cssSelector, String... formatArgs) {
        return new Element(By.cssSelector(String.format(cssSelector, formatArgs)));
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

    public By getLocator() {
        return this.locator;
    }

    private WebElement findElement() {
        return getWaiter().until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    private WebElement findVisibleElement() {
        return getWaiter().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Return an empty list instead of throwing TimeoutException
    private List<WebElement> findElements() {
        try {
            return getWaiter().until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        } catch (TimeoutException e) {
            return Collections.emptyList();
        }
    }

    // The ExpectedConditions.visibilityOfAllElementsLocatedBy() will throw TimeoutException if there is any invisible element.
    // That is not what I expect. I expect a list of visible elements even there are some invisible elements.
    private List<WebElement> findVisibleElements() {
        List<WebElement> elements = findElements();
        List<WebElement> visibleElements = new ArrayList<>();
        for (WebElement element : elements) {
            try {
                getWaiter().until(ExpectedConditions.visibilityOf(element));
                visibleElements.add(element);
            } catch (TimeoutException ignored) {
            }
        }
        return visibleElements;
    }

    public int countElements() {
        int count = 0;
        try {
            return findVisibleElements().size();
        } catch (TimeoutException e) {
            return count;
        }
    }

    public boolean isDisplayed() {
        try {
            return findElement().isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void click() {
        findVisibleElement().click();
    }

    public void clickByJs() {
        Senelium.executeJavascript("arguments[0].click();", findVisibleElement());
    }

    public void type(String keys) {
        findVisibleElement().sendKeys(keys);
    }

    public void setValue(String value) {
        Senelium.executeJavascript(String.format("arguments[0].value = \"%s\";", value), findVisibleElement());
    }

    public boolean isTag(String tagName) {
        return findElement().getTagName().equalsIgnoreCase(tagName);
    }

    public String getText() {
        return getText(false);
    }

    // If 'force' is true then do not wait until visible
    public String getText(boolean force) {
        return force ? findElement().getText() : findVisibleElement().getText();
    }

    public List<String> getAllTexts() {
        return getAllTexts(false);
    }

    public List<String> getAllTexts(boolean force) {
        List<WebElement> elements = force ? findElements() : findVisibleElements();
        return elements.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public String getValue() {
        return getAttribute("value");
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
        getActions().moveToElement(findVisibleElement());
    }

    public void waitUntilDisplayed() {
        getWaiter().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitUntilDisplayed(long mil) {
        getWaiter(mil).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitUntilNotDisplayed() {
        getWaiter().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void waitUntilNotDisplayed(long mil) {
        getWaiter(mil).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void waitUntilTextChanged(String expectText) {
        getWaiter().until(ExpectedConditions.textToBePresentInElementLocated(locator, expectText));
    }

    public void waitUntilTextChanged(String expectText, long mil) {
        getWaiter(mil).until(ExpectedConditions.textToBePresentInElementLocated(locator, expectText));
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
