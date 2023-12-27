package com.senelium;

import com.senelium.config.Browser;
import com.senelium.config.DriverConfig;
import com.senelium.factories.driver.DriverFactory;
import com.senelium.factories.driver.SeneDriver;
import com.senelium.factories.driver.manager.FactoryManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Cookie;
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

    public static void createDriver(Browser browser, DriverConfig config) {
        FactoryManager factoryManager = new FactoryManager();
        DriverFactory driverFactory = factoryManager.findFactory(browser);
        createDriver(driverFactory, config);
    }

    //Can use this for custom driver factory
    public static void createDriver(DriverFactory factory, DriverConfig config) {
        threadWebDriver.set(factory.createDriver(config));
    }

    public static SeneDriver getSeneDriver() {
        if (threadWebDriver.get() == null) {
            throw new RuntimeException("Driver not found. Please create a driver first.");
        }
        return threadWebDriver.get();
    }

    public static WebDriver getWebDriver() {
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
        getSeneDriver().getWebDriver().get(url);
    }

    public static void closeBrowser() {
        log.info("Quit the driver");
        getSeneDriver().getWebDriver().quit();
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
        getWebDriver().navigate().refresh();
    }

    public static Cookie getCookie(String name) {
        return getWebDriver().manage().getCookieNamed(name);
    }

    public static List<Cookie> getCookies() {
        return new ArrayList<>(getWebDriver().manage().getCookies());
    }

    public static void addCookie(Cookie cookie) {
        getWebDriver().manage().addCookie(cookie);
    }

    public static void clearCookie(String name) {
        getWebDriver().manage().deleteCookieNamed(name);
    }

    public static void clearCookies() {
        getWebDriver().manage().deleteAllCookies();
    }

    public static Alert getAlert() {
        return getWebDriver().switchTo().alert();
    }
}
