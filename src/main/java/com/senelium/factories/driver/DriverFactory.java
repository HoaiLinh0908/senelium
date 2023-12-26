package com.senelium.factories.driver;

import com.senelium.config.DriverConfig;
import com.senelium.factories.driver.SeneDriver;

public interface DriverFactory {
    SeneDriver createDriver(DriverConfig config);
}
