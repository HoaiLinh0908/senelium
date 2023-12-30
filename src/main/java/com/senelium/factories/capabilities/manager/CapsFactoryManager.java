package com.senelium.factories.capabilities.manager;

import com.senelium.factories.capabilities.CapabilitiesFactory;
import com.senelium.factories.capabilities.ChromeCapsFactory;
import com.senelium.factories.capabilities.FirefoxCapsFactory;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.MutableCapabilities;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Slf4j
public class CapsFactoryManager {
    private final Map<String, Supplier<? extends CapabilitiesFactory>> factories;

    private CapsFactoryManager() {
        factories = new HashMap<>();
        factories.put("chrome", ChromeCapsFactory::new);
        factories.put("firefox", FirefoxCapsFactory::new);
    }

    private final static class InstanceHolder {
        private static final CapsFactoryManager instance = new CapsFactoryManager();
    }

    public static CapsFactoryManager getInstance() {
        return InstanceHolder.instance;
    }

    public static CapabilitiesFactory findFactory(String browser) {
        Supplier<? extends CapabilitiesFactory> temp = getFactories().get(browser);
        if (temp == null) {
            log.warn(String.format("No available Capabilities factory for \"%s\". " +
                            "Will return a MutableCapabilities object. " +
                            "To remove this warning, register a new one with method registerFactory. " +
                            "Or refer to these available factories: %s.",
                    browser, getAvailableFactory()));
            temp = () -> MutableCapabilities::new;
        }
        return temp.get();
    }

    public static synchronized void registerFactory(String key, Supplier<? extends CapabilitiesFactory> supplier) {
        if (getFactories().containsKey(key.toLowerCase())) {
            throw new RuntimeException("The key \"" + key + "\" already exists. Existing key(s) are" + getAvailableFactory());
        }
        getFactories().put(key, supplier);
    }

    private static Map<String, Supplier<? extends CapabilitiesFactory>> getFactories() {
        return getInstance().factories;
    }

    public static String getAvailableFactory() {
        return String.join(", ", getFactories().keySet());
    }
}
