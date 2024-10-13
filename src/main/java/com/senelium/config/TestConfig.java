package com.senelium.config;

import lombok.Getter;

@Getter
public class TestConfig {
    private final String swagLabsUrl;
    private final String theInternetUrl;

    private TestConfig() {
        this.swagLabsUrl = System.getProperty("swagLabs", "https://www.saucedemo.com");
        this.theInternetUrl = System.getProperty("theInternet", "https://the-internet.herokuapp.com");
        //TODO: variable for report. Currently, only support Allure.
    }

    private static final class InstanceHolder {
        private static final TestConfig instance = new TestConfig();
    }

    public static TestConfig getInstance() {
        return TestConfig.InstanceHolder.instance;
    }
}
