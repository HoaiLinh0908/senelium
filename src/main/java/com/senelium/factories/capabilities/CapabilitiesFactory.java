package com.senelium.factories.capabilities;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import java.util.logging.Level;

@FunctionalInterface
public interface CapabilitiesFactory {
    default LoggingPreferences getLoggingPreferences() {
        LoggingPreferences logPref = new LoggingPreferences();
        logPref.enable(LogType.PERFORMANCE, Level.ALL);
        return logPref;
    }

    MutableCapabilities createCapabilities();
}
