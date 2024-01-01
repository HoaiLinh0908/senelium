package com.senelium.factories.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.time.Duration;

public class EdgeDriverFactory implements DriverFactory<EdgeOptions> {

    @Override
    public EdgeOptions initCapabilities(Capabilities caps) {
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
    public WebDriver createLocalWebDriver(EdgeOptions options) {
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver(options);
    }
}
