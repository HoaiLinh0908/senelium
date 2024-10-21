package com.senelium.element;

import com.senelium.Senelium;
import com.senelium.constant.Expectation;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

@Getter
public class Element {
    private final By locator;
    private By parentLocator;
    private Map<Expectation, String> expectedConditionsMap;

    public Element(By locator) {
        this.locator = locator;
    }

    public static Element by(By locator) {
        return new Element(locator);
    }

    public static Element byText(String text) {
        return new Element(By.xpath("//*[text()=\"" + text + "\"]"));
    }

    public static Element byXpath(String xpath) {
        return new Element(By.xpath(xpath));
    }

    public static Element byXpath(String xpath, String... formatArgs) {
        return new Element(By.xpath(String.format(xpath, (Object[]) formatArgs)));
    }

    public static Element byCssSelector(String cssSelector) {
        return new Element(By.cssSelector(cssSelector));
    }

    public static Element byCssSelector(String cssSelector, String... formatArgs) {
        return new Element(By.cssSelector(String.format(cssSelector, (Object[]) formatArgs)));
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

    public Element getChild(By childLocator) {
        By fullChildLocator = new ByChained(this.locator, childLocator);
        return Element.by(fullChildLocator);
    }

    public WebElement findElement() {
        return findElement(null);
    }

    public WebElement findElement(Integer timeout) {
        return getWaiter(timeout).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement findVisibleElement() {
        return findVisibleElement(null);
    }

    public WebElement findVisibleElement(Integer timeout) {
        return getWaiter(timeout).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public List<WebElement> findElements() {
        return findElements(null);
    }

    public List<WebElement> findElements(Integer timeout) {
        try {
            return getWaiter(timeout).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        } catch (TimeoutException e) {
            // Return an empty list instead of throwing TimeoutException
            return Collections.emptyList();
        }
    }

    public List<WebElement> findVisibleElements() {
        return findVisibleElements(null);
    }

    // The ExpectedConditions.visibilityOfAllElementsLocatedBy() will throw TimeoutException if there is any invisible element.
    // That is not what I expect. I expect a list of visible elements even there are some invisible elements on the DOM.
    public List<WebElement> findVisibleElements(Integer timeout) {
        List<WebElement> elements = findElements(timeout);
        List<WebElement> visibleElements = new ArrayList<>();
        for (WebElement element : elements) {
            try {
                getWaiter(timeout).until(ExpectedConditions.visibilityOf(element));
                visibleElements.add(element);
            } catch (TimeoutException ignored) {
                // Ignore invisible elements
            }
        }
        return visibleElements;
    }

    public int countVisibleElements() {
        return countVisibleElements(null);
    }

    public int countVisibleElements(Integer timeout) {
        return findVisibleElements(timeout).size();
    }

    public boolean isDisplayed() {
        return isDisplayed(null);
    }

    public boolean isDisplayed(Integer timeout) {
        try {
            return findVisibleElement(timeout).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isExisted() {
        return isExisted(null);
    }

    public boolean isExisted(Integer timeout) {
        try {
            getWaiter(timeout).until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public boolean isEnabled() {
        return isEnabled(null);
    }

    public boolean isEnabled(Integer timeout) {
        try {
            getWaiter(timeout).until(ExpectedConditions.elementToBeClickable(this.locator));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public boolean isSelected() {
        return isSelected(null);
    }

    public boolean isSelected(Integer timeout) {
        try {
            getWaiter(timeout).until(ExpectedConditions.elementToBeSelected(this.locator));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public boolean isNotSelected() {
        return isNotSelected(null);
    }

    public boolean isNotSelected(Integer timeout) {
        try {
            getWaiter(timeout).until(ExpectedConditions.elementSelectionStateToBe(this.locator, false));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public void click() {
        // TODO: Handle animated elements
        getWaiter().until(ExpectedConditions.elementToBeClickable(this.locator)).click();
    }

    public void rightClick() {
        //TODO
    }

    public void clickByJs() {
        Senelium.executeJavascript("arguments[0].click();", findVisibleElement());
    }

    public void clearText() {
        findVisibleElement().clear();
    }

    public void type(String keys) {
        type(keys, false);
    }

    public void clearAndType(String keys) {
        type(keys, true);
    }

    public void type(String keys, boolean clear) {
        if (clear) {
            this.clearText();
        }
        findVisibleElement().sendKeys(keys);
    }

    public void setValue(String value) {
        Senelium.executeJavascript(String.format("arguments[0].value = \"%s\";", value), findVisibleElement());
    }

    public void scrollToView() {
        getActions().scrollToElement(findVisibleElement()).perform();
    }

    public void hover() {
        getActions().moveToElement(findVisibleElement());
    }

    public void submitForm() {
        findVisibleElement().submit();
    }

    public String getTagName() {
        return findElement().getTagName();
    }

    public boolean isTag(String tagName) {
        return this.getTagName().equalsIgnoreCase(tagName);
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

    public String getDomAttribute(String name) {
        return findElement().getDomAttribute(name);
    }

    public String getProperty(String name) {
        return findElement().getDomProperty(name);
    }

    public String getAriaRole() {
        return findElement().getAriaRole();
    }

    public String getAccessibleName() {
        return findElement().getAccessibleName();
    }

    public SearchContext getShadowRoot() {
        return findElement().getShadowRoot();
    }

    public Point getLocation() {
        return findVisibleElement().getLocation();
    }

    public Dimension getSize() {
        return findElement().getSize();
    }

    public Rectangle getRect() {
        return findElement().getRect();
    }

    public String getCssValue(String propertyName) {
        return findElement().getCssValue(propertyName);
    }

    // TODO: I have not tested this method :)
    public void waitFor(Expectation exp) {
        Map<Expectation, String> tempMap = getExpectationMap();
        String methodName = tempMap.get(exp);
        try {
            Method method = ExpectedConditions.class.getDeclaredMethod(methodName, By.class);
            getWaiter().until((ExpectedCondition<?>) method.invoke(null, this.locator));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private Map<Expectation, String> getExpectationMap() {
        if (this.expectedConditionsMap == null) {
            initExpectationMap();
        }
        return this.expectedConditionsMap;
    }

    private void initExpectationMap() {
        this.expectedConditionsMap = new HashMap<>() {{
            put(Expectation.VISIBLE, "visibilityOfElementLocated");
            put(Expectation.HIDDEN, "invisibilityOfElementLocated");
            put(Expectation.TEXT_CHANGE, "textToBePresentInElementLocated");
        }};
    }

    public void waitForVisible() {
        this.waitForVisible(null);
    }

    public void waitForVisible(Integer mil) {
        this.getWaiter(mil).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForInvisible() {
        this.waitForInvisible(null);
    }

    public void waitForInvisible(Integer mil) {
        getWaiter(mil).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void waitUntilTextChanged(String expectText) {
        this.waitUntilTextChanged(expectText, null);
    }

    public void waitUntilTextChanged(String expectText, Integer mil) {
        getWaiter(mil).until(ExpectedConditions.textToBePresentInElementLocated(locator, expectText));
    }

    private WebDriverWait getWaiter() {
        return this.getWaiter(null);
    }

    private WebDriverWait getWaiter(Integer mil) {
        return mil != null ? Senelium.getWaiter(mil) : Senelium.getDefaultWaiter();
    }

    private Actions getActions() {
        return Senelium.getActions();
    }
}
