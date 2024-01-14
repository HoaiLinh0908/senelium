package com.senelium.factories.driver;

import com.senelium.config.DriverConfig;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public interface DriverFactory<T extends MutableCapabilities> {

    default SeneDriver createDriver(DriverConfig config) {
        T caps = initCapabilities(config.getCapabilities());
        if (config.isHeadless()) setHeadless(caps);
        setPageLoadTimeout(caps, config.getTimeout().getPageLoad());

        WebDriver webDriver;
        if (StringUtils.isNotEmpty(config.getRemoteURL())) {
            webDriver = createRemoteWebDriver(config.getRemoteAddress(), caps);
        } else {
            webDriver = createLocalWebDriver(caps);
            setWindowSize(webDriver);
        }
        return SeneDriver.newInstance(webDriver, config.getTimeout().getElementWait());
    }

    T initCapabilities(Capabilities caps);

    void setHeadless(T caps);

    void setPageLoadTimeout(T caps, int timeout);

    default WebDriver createRemoteWebDriver(URL url, T caps) {
        return new RemoteWebDriver(url, caps);
    }

    WebDriver createLocalWebDriver(T caps);

    default void setWindowSize(WebDriver driver) {
        driver.manage().window().maximize();
    }
}
