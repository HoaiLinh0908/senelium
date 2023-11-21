package com.senelium.driverfactory;

import com.senelium.config.SeneConfiguration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class FirefoxDriverFactory implements DriverFactory {
    @Override
    public WebDriver createDriver(SeneConfiguration config) {
        FirefoxOptions options = new FirefoxOptions();
        options.merge(config.getCapabilities());
        if (config.isHeadless()) options.addArguments("-headless");
        if (StringUtils.isNotEmpty(config.getRemoteURL())) {
            return new RemoteWebDriver(config.getRemoteAddress(), options);
        } else {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver(options);
        }
    }
}
