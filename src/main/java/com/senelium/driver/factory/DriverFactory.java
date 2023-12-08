package com.senelium.driver.factory;

import com.senelium.config.DriverConfig;
import com.senelium.driver.SeneDriver;

public interface DriverFactory {
    SeneDriver createDriver(DriverConfig config);
}
