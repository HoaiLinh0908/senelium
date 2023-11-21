package com.senelium.driverfactory;

import com.senelium.config.SeneConfiguration;
import org.openqa.selenium.WebDriver;

public interface DriverFactory {
    WebDriver createDriver(SeneConfiguration config);
}
