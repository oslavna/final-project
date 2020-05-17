package helpers;

import com.epam.healenium.SelfHealingDriver;
import com.typesafe.config.Config;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.EventsTests;

import java.net.MalformedURLException;
import java.time.Instant;
import java.util.concurrent.TimeUnit;


public class BaseHooks {

    private static TestConfig config = ConfigFactory.create(TestConfig.class);
    private static final Logger logger = LogManager.getLogger(BaseHooks.class);
    protected static String browser = System.getProperty("browser").toUpperCase();
    public String baseUrl = config.url();
    protected static WebDriverWait wait;
    protected static Actions actions;
    protected SelfHealingDriver driver;

    public SelfHealingDriver getDriver() {
        return driver;
    }

    public  void setup() throws MalformedURLException {

        WebDriver delegate = WebDriverFactory.createNewDriver(DriverName.valueOf(browser));
        Config config = com.typesafe.config.ConfigFactory.load("healenium.properties");
        driver = SelfHealingDriver.create(delegate, config);
        logger.info("WebDriver setup");
        if (driver != null) {
            driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
            wait = new WebDriverWait(driver, 8);
            driver.manage().window().maximize();
            actions = new Actions(driver);
        }

    }

    public  void teardown() {
        if (driver != null) {
            driver.quit();
            logger.info("WebDriver is going down...");
        }
    }


}
