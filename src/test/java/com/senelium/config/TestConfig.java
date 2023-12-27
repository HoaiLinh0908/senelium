package com.senelium.config;

import com.senelium.factories.capabilities.ChromeCapsFactory;
import com.senelium.factories.capabilities.EdgeCapsFactory;
import com.senelium.factories.capabilities.FirefoxCapsFactory;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.Capabilities;

@Getter
@Setter
public class TestConfig {
    String browser;
    boolean headless;
    String gridURL;


    private static final TestConfig INSTANCE = new TestConfig();

    private TestConfig() {
        this.browser = System.getProperty("browser", "chrome");
        this.headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        this.gridURL = System.getProperty("grid", "");
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
                return new EdgeCapsFactory().createCapabilities();
            default:
                throw new RuntimeException("Browser " + browser + " is not supported!");
        }
    }
}
