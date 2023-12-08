package com.senelium;

import com.senelium.config.DriverConfig;
import com.senelium.config.Timeout;
import com.senelium.driver.factory.ChromeDriverFactory;
import com.senelium.driver.factory.DriverFactory;
import com.senelium.driver.factory.FirefoxDriverFactory;
import com.senelium.element.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import java.time.Duration;
import java.util.logging.Level;

public class Application {

    public static void main(String[] args) {
        //Chrome
        ChromeOptions chrome = new ChromeOptions();
        LoggingPreferences logPref = new LoggingPreferences();
        logPref.enable(LogType.PERFORMANCE, Level.ALL);
        chrome.setCapability(ChromeOptions.LOGGING_PREFS, logPref);
        chrome.addArguments("--no-sandbox");
        chrome.addArguments("--disable-extensions");
        chrome.addArguments("--disable-gpu");

        //Firefox
        FirefoxOptions firefox = new FirefoxOptions();
        firefox.setCapability("gpu", false);

        //Chrome config
        DriverConfig chromeDriverConfig = new DriverConfig(chrome, "", false, Timeout.getDefault());
        DriverFactory chromeFactory = new ChromeDriverFactory();

        //Firefox config
        DriverConfig ffDriverConfig = new DriverConfig(firefox, "", false, Timeout.getDefault());
        DriverFactory ffFactory = new FirefoxDriverFactory();

        Example example1 = new Example(chromeDriverConfig, chromeFactory);
        Example example2 = new Example(ffDriverConfig, ffFactory);

        Thread thread1 = new Thread(example1);
        Thread thread2 = new Thread(example2);

        thread1.start();
        thread2.start();
    }
}

class Example implements Runnable {
    DriverConfig configuration;
    DriverFactory factory;

    Example(DriverConfig configuration, DriverFactory factory) {
        this.configuration = configuration;
        this.factory = factory;
    }

    @Override
    public void run() {
        Senelium.createDriver(configuration, factory);
        Senelium.open("https://www.google.com");
        Element search = new Element(By.cssSelector("textarea[type='search']"));
        search.type("Christmas");
        System.out.println(search.isTag("textarea"));
        Senelium.sleep(Duration.ofSeconds(3));
        Senelium.closeBrowser();
    }
}

