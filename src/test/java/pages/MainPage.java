package pages;

import helpers.BaseHooks;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    @FindBy(css = ".nav-item:nth-child(2) .nav-link")
    protected WebElement eventsTabButton;

    @FindBy(css = ".nav-item:nth-child(3) .nav-link")
    protected WebElement talksLibTabButton;

    public WebElement getEventsTabButton() {
        return eventsTabButton;
    }

    public WebElement getTalksLibTabButton() {
        return talksLibTabButton;
    }

    public void openEventsPage(){
        BaseHooks.getDriver().get(baseUrl);
        getEventsTabButton().click();
    }

    public void openTalksLibPage(){
        BaseHooks.getDriver().get(baseUrl);
        getTalksLibTabButton().click();

    }

}
