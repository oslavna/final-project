package pages;

import helpers.TestConfig;
import org.aeonbits.owner.ConfigFactory;

public class BasePage {

    private static TestConfig config = ConfigFactory.create(TestConfig.class);
    String baseUrl = config.url();

}
