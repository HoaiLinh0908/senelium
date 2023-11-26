package com.senelium;

import com.senelium.config.SeneConfiguration;
import com.senelium.driver.SeneDriver;
import com.senelium.driver.factory.DriverFactory;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Slf4j
public final class Senelium {
    private static final ThreadLocal<SeneDriver> threadWebDriver = new ThreadLocal<>();

    private Senelium() {
    }

    public static void createWebDriver(SeneConfiguration config) {
        DriverFactory factory = config.getFactoryManager().findFactory(config.getDriverConfig().getBrowser());
        threadWebDriver.set(factory.createDriver(config.getDriverConfig()));
    }

    public static WebDriver getWebDriver() {
        if (threadWebDriver.get() == null) {
            throw new RuntimeException("Driver not found. Please create a driver first.");
        }
        return threadWebDriver.get().getDriver();
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

    public static WebDriverWait getWaiter() {
        return new WebDriverWait(getWebDriver(), Duration.ofSeconds(5));
    }

    //TODO: Implement other methods
}
