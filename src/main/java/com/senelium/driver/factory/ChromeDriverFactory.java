package com.senelium.driver.factory;

import com.senelium.config.DriverConfig;
import com.senelium.driver.SeneDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

@Slf4j
public class ChromeDriverFactory implements DriverFactory {

    @Override
    public SeneDriver createDriver(DriverConfig config) {
        ChromeOptions options = new ChromeOptions();
        options.merge(config.getCapabilities());
        if (config.isHeadless()) options.addArguments("--headless=new");
        options.setPageLoadTimeout(Duration.ofSeconds(config.getTimeout().getPageLoad()));

        WebDriver driver;
        if (StringUtils.isNotEmpty(config.getRemoteURL())) {
            driver = new RemoteWebDriver(config.getRemoteAddress(), options);
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
        }

        return SeneDriver.newInstance(driver, config.getTimeout().getDefaultElementWait());
    }
}
