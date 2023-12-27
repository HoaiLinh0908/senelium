package com.senelium.factories.capabilities;

import org.openqa.selenium.edge.EdgeOptions;

public class EdgeCapsFactory implements CapabilitiesFactory<EdgeOptions> {
    @Override
    public EdgeOptions createCapabilities() {
        return new EdgeOptions();
    }
}
