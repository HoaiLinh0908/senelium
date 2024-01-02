package com.senelium;

import com.senelium.config.DriverConfig;
import com.senelium.factories.driver.DriverFactory;
import com.senelium.factories.driver.SeneDriver;
import com.senelium.factories.driver.manager.DriverFactoryManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Senelium {
    private static final ThreadLocal<SeneDriver> threadWebDriver = new ThreadLocal<>();

    private Senelium() {
    }

    public static void createDriver(DriverConfig config) {
        DriverFactory<?> driverFactory = DriverFactoryManager.findFactory(config.getBrowser());
        threadWebDriver.set(driverFactory.createDriver(config));
    }

    public static SeneDriver getSeneDriver() {
        if (threadWebDriver.get() == null) {
            throw new RuntimeException("Driver not found. Please create a driver first.");
        }
        return threadWebDriver.get();
    }

    public static WebDriver getDriver() {
        return getSeneDriver().getWebDriver();
    }

    public static Actions getActions() {
        return getSeneDriver().getActions();
    }

    public static WebDriverWait getDefaultWaiter() {
        return getSeneDriver().getDefaultWaiter();
    }

    public static void open(String url) {
        log.info("Navigate to {}", url);
        getDriver().get(url);
    }

    public static void closeBrowser() {
        log.info("Quit the driver");
        getDriver().quit();
        threadWebDriver.remove();
    }

    public static void sleep(Duration mil) {
        try {
            Thread.sleep(mil.toMillis());
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
}
