package com.senelium.factories.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class FirefoxDriverFactory implements DriverFactory<FirefoxOptions> {

    @Override
    public FirefoxOptions initCapabilities(MutableCapabilities caps) {
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
    public WebDriver createLocalWebDriver(FirefoxOptions options, String binary) {
        if (binary.isEmpty()) {
            WebDriverManager.firefoxdriver().setup();
        } else {
            options.setBinary(binary);
        }
        return new FirefoxDriver(options);
    }
}
