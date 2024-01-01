package com.senelium.factories.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class FirefoxDriverFactory implements DriverFactory<FirefoxOptions> {

    @Override
    public FirefoxOptions initCapabilities(Capabilities caps) {
        FirefoxOptions options = new FirefoxOptions();
        options.merge(caps);
        return options;
    }

    @Override
    public void setHeadless(FirefoxOptions options) {
        options.addArguments("--headless");
    }

    @Override
    public void setPageLoadTimeout(FirefoxOptions options, int timeout) {
        options.setPageLoadTimeout(Duration.ofSeconds(timeout));
    }

    @Override
    public WebDriver createLocalWebDriver(FirefoxOptions options) {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver(options);
    }
}
