package com.senelium.test;

import com.senelium.Senelium;
import com.senelium.config.DriverConfig;
import com.senelium.factories.driver.EdgeDriverFactory;
import com.senelium.factories.driver.manager.DriverFactoryManager;
import com.senelium.listener.TestListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public class TestBase {

    @BeforeSuite
    void beforeTestSuite() {
        DriverFactoryManager.registerFactory("edge", EdgeDriverFactory::new);
    }

    @BeforeClass
    void initialTest() {
        DriverConfig config = DriverConfig.getInstance();
        Senelium.createDriver(config);
    }

    @AfterClass(alwaysRun = true)
    void cleanUp() {
        Senelium.closeBrowser();
    }
}
