package com.senelium.config;

import com.senelium.factories.capabilities.manager.CapsFactoryManager;
import lombok.Getter;
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
        this.timeout.setPageLoad(Integer.parseInt(System.getProperty("pageLoadTimeout", "60000")));
        this.timeout.setElementWait(Integer.parseInt(System.getProperty("elementWaitTimeout", "5000")));
        this.timeout.setInterval(Integer.parseInt(System.getProperty("interval", "500")));
        this.windowMaximize = Boolean.parseBoolean(System.getProperty("headless", "true"));
        this.binary = System.getProperty("binary", "");
    }

    public String toString() {
        return "DriverConfig(" +
                " browser=" + this.getBrowser() +
                ", capabilities=" + this.getCapabilities() +
                ", remoteURL=" + this.getRemoteURL() +
                ", headless=" + this.isHeadless() +
                ", timeout=" + this.getTimeout() +
                ", windowMaximize=" + this.isWindowMaximize() +
                ", binary=" + this.getBinary() + " )";
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
