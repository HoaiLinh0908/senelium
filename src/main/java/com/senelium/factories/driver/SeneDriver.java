package com.senelium.factories.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class SeneDriver {
    WebDriver driver;
    Actions actions;
    WebDriverWait defaultWaiter;

    private SeneDriver(WebDriver driver, int defaultWait) {
        Objects.requireNonNull(driver);
        this.driver = driver;
        this.actions = new Actions(this.driver);
        this.defaultWaiter = new WebDriverWait(this.driver, Duration.ofSeconds(defaultWait));
    }

    public static SeneDriver newInstance(WebDriver driver, int defaultWait) {
        return new SeneDriver(driver, defaultWait);
    }

    public WebDriver getDriver() {
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
