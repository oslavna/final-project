package helpers;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.EventsTests;

import java.time.Instant;
import java.util.concurrent.TimeUnit;


public class BaseHooks {

    private static TestConfig config = ConfigFactory.create(TestConfig.class);
    protected static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(BaseHooks.class);
    protected static String browser = System.getProperty("browser").toUpperCase();
    public String baseUrl = config.url();
    protected static WebDriverWait wait;


    public static WebDriverWait getWait() {
        return wait;
    }




    public static WebDriver getDriver() {
        return driver;
    }




    @BeforeAll
    public static void setup() {
        driver = WebDriverFactory.createNewDriver(DriverName.valueOf(browser));
        logger.info("WebDriver setup");
        if (driver != null) {
            driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
            wait = new WebDriverWait(driver, 10);
            driver.manage().window().maximize();
        }

    }

    @AfterAll
    public static void teardown() {
        if (driver != null) {
            driver.quit();
            logger.info("WebDriver is going down...");
        }
    }


}
