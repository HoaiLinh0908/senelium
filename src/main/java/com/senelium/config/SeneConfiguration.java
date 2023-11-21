package com.senelium.config;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.MalformedURLException;
import java.net.URL;

@Setter
@Getter
public class SeneConfiguration {
    Capabilities capabilities;
    Browser browser;
    String remoteURL;
    boolean headless;

    public SeneConfiguration(Capabilities capabilities, Browser browser, String remoteURL, boolean headless) {
        this.capabilities = capabilities;
        this.browser = browser;
        this.remoteURL = remoteURL;
        this.headless = headless;
    }

    public SeneConfiguration() {
        this.browser = Browser.CHROME;
        this.capabilities = new ChromeOptions();
        this.remoteURL = "";
        this.headless = false;
    }

    public URL getRemoteAddress() {
        try {
            return new URL(this.remoteURL);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
