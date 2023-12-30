package com.senelium.factories.driver;

import com.senelium.config.DriverConfig;

public interface DriverFactory {
    SeneDriver createDriver(DriverConfig config);
}
