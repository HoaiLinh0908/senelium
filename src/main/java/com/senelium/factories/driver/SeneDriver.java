package com.senelium.factories.driver;

import com.senelium.config.Timeout;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class SeneDriver {
    WebDriver driver;
    Actions actions;
    WebDriverWait defaultWaiter;

    private SeneDriver(WebDriver driver, Timeout timeout) {
        Objects.requireNonNull(driver);
        this.driver = driver;
        this.actions = new Actions(this.driver);
        this.defaultWaiter = new WebDriverWait(this.driver, Duration.ofSeconds(timeout.getElementWait()), Duration.ofMillis(timeout.getInterval()));
    }

    public static SeneDriver newInstance(WebDriver driver, Timeout timeout) {
        return new SeneDriver(driver, timeout);
    }

    public WebDriver getWebDriver() {
        return this.driver;
    }

    public Actions getActions() {
        return this.actions;
    }

    public WebDriverWait getDefaultWaiter() {
        return this.defaultWaiter;
    }

    public WebDriverWait getWaiter(Duration duration) {
        return new WebDriverWait(driver, duration);
    }
}
