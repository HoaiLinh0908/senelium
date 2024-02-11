package com.senelium.factories.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.time.Duration;

public class EdgeDriverFactory implements DriverFactory<EdgeOptions> {

    @Override
    public EdgeOptions initCapabilities(MutableCapabilities caps) {
        EdgeOptions options = new EdgeOptions();
        options.merge(caps);
        return options;
    }

    @Override
    public void setHeadless(EdgeOptions options) {
        options.addArguments("--headless");
    }

    @Override
    public void setPageLoadTimeout(EdgeOptions options, int timeout) {
        options.setPageLoadTimeout(Duration.ofSeconds(timeout));
    }

    @Override
    public WebDriver createLocalWebDriver(EdgeOptions options, String binary) {
        if (binary.isEmpty()) {
            WebDriverManager.edgedriver().setup();
        } else {
            options.setBinary(binary);
        }
        return new EdgeDriver(options);
    }
}
