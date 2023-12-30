package com.senelium.factories.capabilities;

import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeCapsFactory implements CapabilitiesFactory {

    @Override
    public ChromeOptions createCapabilities() {
        ChromeOptions options = new ChromeOptions();
        options.setCapability(ChromeOptions.LOGGING_PREFS, getLoggingPreferences());
        options.addArguments("--start-maximized");
        options.addArguments("--disable-extensions");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        return options;
    }
}
