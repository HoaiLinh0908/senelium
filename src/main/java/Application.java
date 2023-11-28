import com.senelium.Senelium;
import com.senelium.config.Browser;
import com.senelium.config.DriverConfig;
import com.senelium.config.SeneConfiguration;
import com.senelium.config.Timeout;
import com.senelium.element.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
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

        //Edge
        EdgeOptions edge = new EdgeOptions();


        DriverConfig driverConfig = new DriverConfig(chrome, Browser.CHROME, "", false, Timeout.getDefault());
        SeneConfiguration configuration = new SeneConfiguration(driverConfig);

        Senelium.createDriver(configuration);
        Senelium.navigate("https://www.google.com");
        Element search = new Element(By.cssSelector("textarea[type='search']"));
        search.type("Christmas");
        System.out.println(search.isTag("textarea"));
        Senelium.sleep(Duration.ofSeconds(3));
        Senelium.quit();
    }
}
