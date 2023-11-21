package com.senelium;

import com.senelium.config.Browser;
import com.senelium.config.SeneConfiguration;
import com.senelium.driverfactory.ChromeDriverFactory;
import com.senelium.driverfactory.DriverFactory;
import com.senelium.driverfactory.EdgeDriverFactory;
import com.senelium.driverfactory.FirefoxDriverFactory;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.InvocationTargetException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class Senelium {
    private static final ThreadLocal<WebDriver> threadWebDriver = new ThreadLocal<>();
    private static final Map<Browser, Class<? extends DriverFactory>> factories = new HashMap<>();

    private Senelium() {
    }

    public static void createWebDriver(SeneConfiguration config) {
        try {
            DriverFactory factory = factories.get(config.getBrowser()).getDeclaredConstructor().newInstance();
            threadWebDriver.set(factory.createDriver(config));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            log.error("Failed to create the Web Driver {}", config.getBrowser());
            e.printStackTrace();
        }
    }

    public static WebDriver getWebDriver() {
        if (threadWebDriver.get() == null) {
            throw new RuntimeException("Driver not found. Please create a driver first.");
        }
        return threadWebDriver.get();
    }

    public static void navigate(String url) {
        log.info("Navigate to {}", url);
        getWebDriver().navigate().to(url);
    }

    public static void quit() {
        log.info("Quit the driver");
        getWebDriver().quit();
    }

    public static void sleep(Duration mil) {
        try {
            Thread.sleep(mil.toMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //TODO: Implement other methods

    static {
        factories.put(Browser.CHROME, ChromeDriverFactory.class);
        factories.put(Browser.FIREFOX, FirefoxDriverFactory.class);
        factories.put(Browser.EDGE, EdgeDriverFactory.class);
    }
}
