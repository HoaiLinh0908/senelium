package com.senelium.factories.driver.manager;

import com.senelium.factories.driver.ChromeDriverFactory;
import com.senelium.factories.driver.DriverFactory;
import com.senelium.factories.driver.EdgeDriverFactory;
import com.senelium.factories.driver.FirefoxDriverFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DriverFactoryManager {

    private final Map<String, Supplier<DriverFactory<?>>> factories;

    private DriverFactoryManager() {
        factories = new HashMap<>();
        factories.put("chrome", ChromeDriverFactory::new);
        factories.put("firefox", FirefoxDriverFactory::new);
        factories.put("edge", EdgeDriverFactory::new);
    }

    private static final class InstanceHolder {
        private static final DriverFactoryManager instance = new DriverFactoryManager();
    }

    public static DriverFactoryManager getInstance() {
        return InstanceHolder.instance;
    }

    public static DriverFactory<?> findFactory(String browser) {
        Supplier<DriverFactory<?>> temp = getFactories().get(browser);
        if (temp == null) {
            String message = String.format("No available Driver factory for \"%s\". " +
                            "Please refer to these available factories: %s. " +
                            "Or register a new one with method registerFactory.",
                    browser, getAvailableFactory());
            throw new RuntimeException(message);
        }
        return temp.get();
    }

    /***
     * The register method should be called once in the beforeSuite()
     * @param key name of the factory
     * @param factorySupplier
     */
    public static synchronized void registerFactory(String key, Supplier<DriverFactory<?>> factorySupplier) {
        if (getFactories().containsKey(key.toLowerCase())) {
            throw new RuntimeException("A factory with key \"" + key + "\" already exists. Existing key(s) are " + getAvailableFactory());
        }
        getFactories().put(key, factorySupplier);
    }

    private static Map<String, Supplier<DriverFactory<?>>> getFactories() {
        return getInstance().factories;
    }

    public static String getAvailableFactory() {
        return String.join(", ", getFactories().keySet());
    }
}
