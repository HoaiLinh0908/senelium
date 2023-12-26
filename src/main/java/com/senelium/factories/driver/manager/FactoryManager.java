package com.senelium.factories.driver.manager;

import com.senelium.config.Browser;
import com.senelium.factories.driver.ChromeDriverFactory;
import com.senelium.factories.driver.DriverFactory;
import com.senelium.factories.driver.FirefoxDriverFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class FactoryManager {

    private final Map<Browser, Supplier<? extends DriverFactory>> factories = new HashMap<>();

    public FactoryManager() {
        //Add supported browsers
        factories.put(Browser.CHROME, ChromeDriverFactory::new);
        factories.put(Browser.FIREFOX, FirefoxDriverFactory::new);
    }

    public DriverFactory findFactory(Browser browser) {
        return factories.get(browser).get();
    }
}
