package com.senelium.driverfactory;

import com.senelium.config.SeneConfiguration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class EdgeDriverFactory implements DriverFactory {
    @Override
    public WebDriver createDriver(SeneConfiguration config) {
        EdgeOptions options = new EdgeOptions();
        options.merge(config.getCapabilities());
        if (config.isHeadless()) options.addArguments("headless");
        if (StringUtils.isNotEmpty(config.getRemoteURL())) {
            return new RemoteWebDriver(config.getRemoteAddress(), options);
        } else {
            WebDriverManager.edgedriver().setup();
            return new EdgeDriver(options);
        }
    }
}
