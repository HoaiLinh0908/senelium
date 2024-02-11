package com.senelium.config;

import com.senelium.factories.capabilities.manager.CapsFactoryManager;
import lombok.Getter;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

@Getter
public class DriverConfig {
    private final String browser;
    private final MutableCapabilities capabilities;
    private final String remoteURL;
    private final boolean headless;
    private final Timeout timeout;
    private final boolean windowMaximize;
    private final String binary;

    private DriverConfig() {
        this.browser = System.getProperty("browser", "chrome");
        this.capabilities = CapsFactoryManager.findFactory(this.browser).createCapabilities();
        this.remoteURL = System.getProperty("remoteURL", "");
        this.headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        this.timeout = new Timeout();
        this.timeout.setPageLoad(Integer.parseInt(System.getProperty("pageLoadTimeout", "60")));
        this.timeout.setElementWait(Integer.parseInt(System.getProperty("elementWaitTimeout", "5")));
        this.windowMaximize = Boolean.parseBoolean(System.getProperty("headless", "true"));
        this.binary = System.getProperty("binary", "");
    }

    private static final class InstanceHolder {
        private static final DriverConfig instance = new DriverConfig();
    }

    public static DriverConfig getInstance() {
        return InstanceHolder.instance;
    }

    public URL getRemoteAddress() {
        try {
            return new URL(this.remoteURL);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
