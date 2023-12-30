package com.senelium.factories.driver;

import com.senelium.config.DriverConfig;
import com.senelium.factories.driver.SeneDriver;
import com.senelium.factories.driver.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

public class EdgeDriverFactory implements DriverFactory {

    @Override
    public SeneDriver createDriver(DriverConfig config) {
        EdgeOptions options = new EdgeOptions();
        options.merge(config.getCapabilities());
        if (config.isHeadless()) options.addArguments("--headless");
        options.setPageLoadTimeout(Duration.ofSeconds(config.getTimeout().getPageLoad()));

        WebDriver driver;
        if (StringUtils.isNotEmpty(config.getRemoteURL())) {
            driver = new RemoteWebDriver(config.getRemoteAddress(), options);
        } else {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver(options);
        }

        return SeneDriver.newInstance(driver, config.getTimeout().getElementWait());
    }
}
