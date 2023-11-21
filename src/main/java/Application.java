import com.senelium.Senelium;
import com.senelium.config.Browser;
import com.senelium.config.SeneConfiguration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import java.time.Duration;
import java.util.logging.Level;

public class Application {

    public static void main(String[] args) {
        ChromeOptions chrome = new ChromeOptions();
        LoggingPreferences logPref = new LoggingPreferences();
        logPref.enable(LogType.PERFORMANCE, Level.ALL);
        chrome.setCapability(ChromeOptions.LOGGING_PREFS, logPref);
        chrome.addArguments("--no-sandbox");
        chrome.addArguments("--disable-extensions");
        chrome.addArguments("--disable-gpu");

        FirefoxOptions firefox = new FirefoxOptions();
        firefox.setCapability("gpu", false);
        firefox.setLogLevel(FirefoxDriverLogLevel.fromLevel(Level.ALL));

        EdgeOptions edge = new EdgeOptions();

        SeneConfiguration configuration = new SeneConfiguration(edge, Browser.EDGE, "", false);
        Senelium.createWebDriver(configuration);
        Senelium.navigate("https://www.google.com/");
        Senelium.sleep(Duration.ofSeconds(3));
        Senelium.quit();
    }
}
