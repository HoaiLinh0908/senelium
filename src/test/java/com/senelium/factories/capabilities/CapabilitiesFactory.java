package com.senelium.factories.capabilities;

import org.openqa.selenium.Capabilities;

public interface CapabilitiesFactory<T extends Capabilities> {

    T createCapabilities();
}
