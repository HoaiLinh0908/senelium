package com.senelium.config;

import com.senelium.driver.factory.manager.FactoryManager;
import com.senelium.driver.factory.manager.SeneFactoryManager;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SeneConfiguration {

    DriverConfig driverConfig;
    FactoryManager factoryManager;

    public SeneConfiguration(DriverConfig driverConfig, FactoryManager factoryManager) {
        this.driverConfig = driverConfig;
        this.factoryManager = factoryManager;
    }

    public SeneConfiguration(DriverConfig driverConfig) {
        this.driverConfig = driverConfig;
        this.factoryManager = new SeneFactoryManager();
    }

    public SeneConfiguration() {
        this(new DriverConfig(), new SeneFactoryManager());
    }
}
