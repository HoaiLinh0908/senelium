package com.senelium.driver.factory;

import com.senelium.config.DriverConfig;
import com.senelium.driver.SeneDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

public class FirefoxDriverFactory implements DriverFactory {
    @Override
    public SeneDriver createDriver(DriverConfig config) {
        FirefoxOptions options = new FirefoxOptions();
        options.merge(config.getCapabilities());
        if (config.isHeadless()) options.addArguments("--headless");
        options.setPageLoadTimeout(Duration.ofSeconds(config.getTimeout().getPageLoad()));

        WebDriver driver;
        if (StringUtils.isNotEmpty(config.getRemoteURL())) {
            driver = new RemoteWebDriver(config.getRemoteAddress(), options);
        } else {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver(options);
        }

        return SeneDriver.newInstance(driver, config.getTimeout().getDefaultElementWait());
    }
}
