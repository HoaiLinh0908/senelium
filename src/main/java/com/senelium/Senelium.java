package com.senelium;

import com.senelium.config.Browser;
import com.senelium.config.DriverConfig;
import com.senelium.driver.SeneDriver;
import com.senelium.driver.factory.DriverFactory;
import com.senelium.driver.factory.manager.FactoryManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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

    public static WebDriver getDriver() {
        return getSeneDriver().getDriver();
    }

    public static Actions getActions() {
        return getSeneDriver().getActions();
    }

    public static WebDriverWait getDefaultWaiter() {
        return getSeneDriver().getDefaultWaiter();
    }

    public static void open(String url) {
        log.info("Navigate to {}", url);
        getSeneDriver().getDriver().get(url);
    }

    public static void closeBrowser() {
        log.info("Quit the driver");
        getSeneDriver().getDriver().quit();
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
        log.info("Refresh the page");
        getSeneDriver().getDriver().navigate().refresh();
    }

    //TODO: Implement other methods
}
