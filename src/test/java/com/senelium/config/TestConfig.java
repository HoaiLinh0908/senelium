package com.senelium.config;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.Capabilities;

@Getter
@Setter
public class TestConfig {
    String report;
    String grid;
    String browser;

    private static final TestConfig INSTANCE = new TestConfig();

    private TestConfig() {
        this.browser = System.getProperty("browser", "chrome");
        this.report = System.getProperty("report", "allure");
        this.grid = System.getProperty("grid", "");
    }

    public static TestConfig getInstance() {
        return INSTANCE;
    }

    public Capabilities createCapabilities() {
        //TODO: Create capabilities from from the browser property
        return null;
    }
}
