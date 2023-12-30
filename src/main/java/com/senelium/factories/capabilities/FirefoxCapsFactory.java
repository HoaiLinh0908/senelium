package com.senelium.factories.capabilities;

import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxCapsFactory implements CapabilitiesFactory {
    @Override
    public FirefoxOptions createCapabilities() {
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability("gpu", false);
        return options;
    }
}
