package com.senelium.config;

import com.senelium.driver.factory.ChromeDriverFactory;
import com.senelium.driver.factory.DriverFactory;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SeneConfiguration {

    DriverConfig driverConfig;
    DriverFactory driverFactory;

    public SeneConfiguration(DriverConfig driverConfig, DriverFactory driverFactory) {
        this.driverConfig = driverConfig;
        this.driverFactory = driverFactory;
    }

    public SeneConfiguration(DriverConfig driverConfig) {
        this.driverConfig = driverConfig;
        this.driverFactory = new ChromeDriverFactory();
    }

    public SeneConfiguration() {
        this(new DriverConfig(), new ChromeDriverFactory());
    }
}
