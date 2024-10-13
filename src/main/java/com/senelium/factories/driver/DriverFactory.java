package com.senelium.factories.driver;

import com.senelium.config.DriverConfig;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public interface DriverFactory<T extends MutableCapabilities> {

    default SeDriver createDriver(DriverConfig config) {
        T caps = initCapabilities(config.getCapabilities());
        if (config.isHeadless()) setHeadless(caps);
        setPageLoadTimeout(caps, config.getTimeout().getPageLoad());

        WebDriver webDriver;
        if (StringUtils.isNotEmpty(config.getRemoteURL())) {
            webDriver = createRemoteWebDriver(config.getRemoteAddress(), caps);
        } else {
            webDriver = createLocalWebDriver(caps, config.getBinary());
            if (config.isWindowMaximize()) setWindowSize(webDriver);
        }
        return SeDriver.newInstance(webDriver, config.getTimeout());
    }

    T initCapabilities(MutableCapabilities caps);

    void setHeadless(T caps);

    void setPageLoadTimeout(T caps, int timeout);

    default WebDriver createRemoteWebDriver(URL url, T caps) {
        return new RemoteWebDriver(url, caps);
    }

    WebDriver createLocalWebDriver(T caps, String binary);

    default void setWindowSize(WebDriver driver) {
        driver.manage().window().maximize();
    }
}
