package com.senelium.config;

import com.senelium.factories.capabilities.ChromeCapsFactory;
import com.senelium.factories.capabilities.FirefoxCapsFactory;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.edge.EdgeOptions;

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
        switch (browser) {
            case "chrome":
                return new ChromeCapsFactory().createCapabilities();
            case "firefox":
                return new FirefoxCapsFactory().createCapabilities();
            case "edge":
                return new EdgeOptions();
            default:
                throw new RuntimeException("Browser " + browser + " is not supported!");
        }
    }
}
