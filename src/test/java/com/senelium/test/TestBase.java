package com.senelium.test;

import com.senelium.Senelium;
import com.senelium.config.Browser;
import com.senelium.config.DriverConfig;
import com.senelium.config.TestConfig;
import com.senelium.config.Timeout;
import com.senelium.customfactory.EdgeDriverFactory;
import com.senelium.listener.TestListener;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import java.util.Arrays;

@Listeners(TestListener.class)
public class TestBase {

    @BeforeClass(alwaysRun = true)
    void initialTest() {

        TestConfig testConfig = TestConfig.getInstance();

        DriverConfig config1 = new DriverConfig();
        config1.setCapabilities(testConfig.createCapabilities());
        config1.setRemoteURL(testConfig.getGrid());
        config1.setHeadless(true);
        config1.setTimeout(Timeout.getDefault());

        boolean check = Arrays.stream(Browser.values()).map(Browser::name).anyMatch(b -> b.equalsIgnoreCase(testConfig.getBrowser()));
        if (check) {
            Senelium.createDriver(Browser.of(testConfig.getBrowser()), config1);
        } else {
            Senelium.createDriver(new EdgeDriverFactory(), config1);
        }
    }
}
