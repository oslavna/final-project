package pages;

import helpers.BaseHooks;
import helpers.DatesHelper;
import helpers.TestConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    private static TestConfig config = ConfigFactory.create(TestConfig.class);
    String baseUrl = config.url();
    DatesHelper datesHelper = new DatesHelper();
    BaseHooks baseHooks = new BaseHooks();

    public BasePage() {
        PageFactory.initElements(BaseHooks.getDriver(), this);
    }
}
