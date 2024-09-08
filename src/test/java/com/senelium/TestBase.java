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

    protected void open(String url) {
        Senelium.open(url);
    }

    @BeforeSuite(alwaysRun = true)
    public void beforeTestSuite() {
        // Set the test configuration before executing the suite
        config = TestConfig.getInstance();
    }

    @BeforeClass(alwaysRun = true)
    public void initialTest() {
        // Because tests are paralleled by classes, we create a new driver before execute tests in each class
        Senelium.createDriver(DriverConfig.getInstance());
    }

    @AfterClass(alwaysRun = true)
    public void cleanUp() {
        // Close browser after executing tests in a class
        Senelium.closeBrowser();
    }
}
