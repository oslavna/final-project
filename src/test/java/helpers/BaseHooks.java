package helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.junit.*;
import org.openqa.selenium.WebDriver;


public class BaseHooks {

    private static TestConfig config = ConfigFactory.create(TestConfig.class);
    protected static WebDriver driver;
    protected static String browser = System.getProperty("browser").toUpperCase();
    public String baseUrl = config.url();


}
