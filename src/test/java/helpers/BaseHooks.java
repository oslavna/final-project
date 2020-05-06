package helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;


public class BaseHooks {

    private static TestConfig config = ConfigFactory.create(TestConfig.class);
    protected static WebDriver driver;
    protected static String browser = System.getProperty("browser").toUpperCase();
    public String baseUrl = config.url();

    public static WebDriver getDriver() {
        return driver;
    }


    @BeforeClass
    public static void setup() {
        driver = WebDriverFactory.createNewDriver(DriverName.valueOf(browser));
        if (driver != null) {
            driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }

    }

    @AfterClass
    public static void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }


}
