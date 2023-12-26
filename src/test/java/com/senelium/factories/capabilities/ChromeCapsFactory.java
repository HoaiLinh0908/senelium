package com.senelium.factories.capabilities;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import java.util.logging.Level;

public class ChromeCapsFactory implements CapabilitiesFactory<ChromeOptions> {

    @Override
    public ChromeOptions createCapabilities() {
        LoggingPreferences logPref = new LoggingPreferences();
        logPref.enable(LogType.PERFORMANCE, Level.ALL);

        ChromeOptions options = new ChromeOptions();
        options.setCapability("goog:loggingPrefs", logPref);
        options.addArguments("--start-maximized");
        options.addArguments("--disable-extensions");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        return options;
    }
}
