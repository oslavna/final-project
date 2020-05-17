package pages;


import helpers.DatesHelper;
import helpers.TestConfig;
import org.aeonbits.owner.ConfigFactory;

public class BasePage {

    private static TestConfig config = ConfigFactory.create(TestConfig.class);
    String baseUrl = config.url();
    DatesHelper datesHelper = new DatesHelper();

}
