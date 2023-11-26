package com.senelium.config;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.MalformedURLException;
import java.net.URL;

@Getter
@Setter
public class DriverConfig {
    Capabilities capabilities;
    Browser browser;
    String remoteURL;
    boolean headless;
    Timeout timeout;

    public DriverConfig(Capabilities capabilities, Browser browser, String remoteURL, boolean headless, Timeout timeout) {
        this.capabilities = capabilities;
        this.browser = browser;
        this.remoteURL = remoteURL;
        this.headless = headless;
        this.timeout = timeout;
    }

    public DriverConfig() {
        this(new ChromeOptions(), Browser.CHROME, "", false, Timeout.getDefault());
    }

    public URL getRemoteAddress() {
        try {
            return new URL(this.remoteURL);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
