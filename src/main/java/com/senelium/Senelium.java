package com.senelium;

import com.senelium.config.DriverConfig;
import com.senelium.factories.driver.DriverFactory;
import com.senelium.factories.driver.SeDriver;
import com.senelium.factories.driver.manager.DriverFactoryManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Senelium {
    private static final ThreadLocal<SeDriver> threadWebDriver = new ThreadLocal<>();

    private Senelium() {
    }

    public static void createDriver(DriverConfig config) {
        DriverFactory<?> driverFactory = DriverFactoryManager.findFactory(config.getBrowser());
        threadWebDriver.set(driverFactory.createDriver(config));
        log.info("Successfully created driver with configuration {}", config);
    }

    public static SeDriver getSeDriver() {
        if (threadWebDriver.get() == null) {
            throw new RuntimeException("Driver not found. Driver might not be initialized.");
        }
        return threadWebDriver.get();
    }

    public static WebDriver getDriver() {
        return getSeDriver().getWebDriver();
    }

    public static Actions getActions() {
        return getSeDriver().getActions();
    }

    public static WebDriverWait getDefaultWaiter() {
        return getSeDriver().getDefaultWaiter();
    }

    public static WebDriverWait getWaiter(int mil) {
        if (mil < 0) throw new InvalidArgumentException("Waiter timeout must be or greater than 0.");
        return Senelium.getSeDriver().getWaiter(Duration.ofMillis(mil));
    }

    public static void open(String url) {
        //support 'baseUrl'?
        log.info("Navigate to {}", url);
        getDriver().get(url);
    }

    public static void closeBrowser() {
        log.info("Quit the driver");
        getDriver().quit();
        threadWebDriver.remove();
    }

    public static void closeCurrentTab() {
        getDriver().close();
    }

    public static void sleep(long mil) {
        try {
            Thread.sleep(mil);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void refresh() {
        getDriver().navigate().refresh();
    }

    public static Cookie getCookie(String name) {
        return getDriver().manage().getCookieNamed(name);
    }

    public static List<Cookie> getCookies() {
        return new ArrayList<>(getDriver().manage().getCookies());
    }

    public static void addCookie(Cookie cookie) {
        getDriver().manage().addCookie(cookie);
    }

    public static void clearCookie(String name) {
        getDriver().manage().deleteCookieNamed(name);
    }

    public static void clearCookies() {
        getDriver().manage().deleteAllCookies();
    }

    public static Alert getAlert() {
        return getDriver().switchTo().alert();
    }

    public static Object executeJavascript(String script, Object... args) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
        return jsExecutor.executeScript(script, args);
    }

    public static Object executeAsyncJavascript(String script, Object... args) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
        return jsExecutor.executeAsyncScript(script, args);
    }

    public static void closeCurrentAndSwitchToNewTab() {
        closeCurrentTab();
        getDriver().switchTo().window(new ArrayList<>(getDriver().getWindowHandles()).get(0));
    }

    public static void scrollToElement(WebElement element) {
        getActions().scrollToElement(element).perform();
    }

    public static void switchToFrame(int index) {
        getDriver().switchTo().frame(index);
    }

    public static void switchToMainWindow() {
        getDriver().switchTo().defaultContent();
    }
}
