package com.senelium;

import com.senelium.config.DriverConfig;
import com.senelium.config.TestConfig;
import com.senelium.listener.TestListener;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Slf4j
@Listeners(TestListener.class)
public class TestBase {
    protected TestConfig config;

    @BeforeSuite(alwaysRun = true)
    public void beforeTestSuite() {
        config = TestConfig.getInstance();
    }

    @BeforeClass(alwaysRun = true)
    public void initialTest() {
        Senelium.createDriver(DriverConfig.getInstance());
    }

    @AfterClass(alwaysRun = true)
    public void cleanUp() {
        Senelium.closeBrowser();
    }
}
