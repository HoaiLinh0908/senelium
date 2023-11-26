package com.senelium.element;

import com.senelium.Senelium;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

@Getter
@Setter
public class Element {
    protected WebDriver driver;
    protected WebElement webElement;
    protected By locator;

    public Element(By locator) {
        this.locator = locator;
        this.driver = Senelium.getWebDriver();
    }

    public WebElement findElement() {
        if (webElement == null || isStale()) {
            return findVisibleElement();
        }
        return webElement;
    }

    private WebElement findVisibleElement() {
        return getWaiter().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public boolean isStale() {
        return ExpectedConditions.stalenessOf(webElement).apply(driver);
    }

    public List<WebElement> findElements() {
        return driver.findElements(locator);
    }

    public boolean isDisplayed() {
        return findElement().isDisplayed();
    }

    public void click() {
        findElement().click();
    }

    public void type(String keys) {
        findElement().sendKeys(keys);
    }

    public boolean isTag(String tagName) {
        return findElement().getTagName().equalsIgnoreCase(tagName);
    }

    public String getText() {
        if (isTag("input")) {
            return findElement().getAttribute("value");
        }
        return findElement().getText();
    }

    private WebDriverWait getWaiter() {
        return Senelium.getWaiter();
    }
}
