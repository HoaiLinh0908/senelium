package com.senelium.driverfactory;

import com.senelium.config.SeneConfiguration;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

@Slf4j
public class ChromeDriverFactory implements DriverFactory {

    @Override
    public WebDriver createDriver(SeneConfiguration config) {

        ChromeOptions options = new ChromeOptions();
        options.merge(config.getCapabilities());
        if (config.isHeadless()) options.addArguments("--headless");
        if (StringUtils.isNotEmpty(config.getRemoteURL())) {
            return new RemoteWebDriver(config.getRemoteAddress(), options);
        } else {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver(options);
        }

    }
}
