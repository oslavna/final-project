package helpers;

import com.epam.healenium.SelfHealingDriver;
import com.typesafe.config.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;


public class BaseHooks {

    private static final Logger logger = LogManager.getLogger(BaseHooks.class);
    protected static String browser = System.getProperty("browser").toUpperCase();
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
