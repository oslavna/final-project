package pages;

import helpers.BaseHooks;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    @FindBy(css = ".nav-item:nth-child(2) .nav-link")
    protected WebElement eventsTabButton;

    public WebElement getEventsTabButton() {
        return eventsTabButton;
    }

    public void openEventsPage(){
        BaseHooks.getDriver().get(baseUrl);
        getEventsTabButton().click();

    }

}
