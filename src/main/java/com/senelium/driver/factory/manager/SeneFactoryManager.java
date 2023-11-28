package com.senelium.driver.factory.manager;

import com.senelium.config.Browser;
import com.senelium.driver.factory.ChromeDriverFactory;
import com.senelium.driver.factory.DriverFactory;
import com.senelium.driver.factory.EdgeDriverFactory;
import com.senelium.driver.factory.FirefoxDriverFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class SeneFactoryManager implements FactoryManager {

    @Override
    public Map<Browser, Class<? extends DriverFactory>> initializeFactories() {
        Map<Browser, Class<? extends DriverFactory>> factories = new HashMap<>();

        factories.put(Browser.CHROME, ChromeDriverFactory.class);
        factories.put(Browser.FIREFOX, FirefoxDriverFactory.class);
        factories.put(Browser.EDGE, EdgeDriverFactory.class);

        return factories;
    }
}
