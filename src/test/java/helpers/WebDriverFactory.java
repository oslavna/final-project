package helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;


public class WebDriverFactory {

    private static TestConfig config = ConfigFactory.create(TestConfig.class);

    public static WebDriver createNewDriver(DriverName name) throws MalformedURLException {
        WebDriver driver;


        switch (name) {
            case CHROME:
//                WebDriverManager.firefoxdriver().setup();
//                driver = new FirefoxDriver();
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case REMOTE_CHROME:
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setBrowserName("chrome");
                capabilities.setVersion("80.0");
                capabilities.setCapability("enableVNC", true);
                capabilities.setCapability("enableVideo", false);
                driver = new RemoteWebDriver(new URL(config.SelenoidURL()), capabilities);
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
        }
        return driver;
    }

}
