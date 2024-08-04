package com.senelium.config;

import lombok.Getter;

@Getter
public class TestConfig {
    private final String swagLabsUrl;

    private TestConfig() {
        this.swagLabsUrl = System.getProperty("swagLabs", "https://www.saucedemo.com");
    }

    private static final class InstanceHolder {
        private static final TestConfig instance = new TestConfig();
    }

    public static TestConfig getInstance() {
        return TestConfig.InstanceHolder.instance;
    }
}
