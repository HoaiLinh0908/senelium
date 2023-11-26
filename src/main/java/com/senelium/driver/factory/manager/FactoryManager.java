package com.senelium.driver.factory.manager;

import com.senelium.config.Browser;
import com.senelium.driver.factory.DriverFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public interface FactoryManager {
    Map<Browser, Class<? extends DriverFactory>> availableFactories();

    default DriverFactory findFactory(Browser browser) {
        try {
            return availableFactories().get(browser).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to find a Driver factory for " + browser);
        }
    }
}
