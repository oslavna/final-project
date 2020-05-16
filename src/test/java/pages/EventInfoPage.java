package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

public class EventInfoPage extends BasePage{

    private static final Logger logger = LogManager.getLogger(EventInfoPage.class);
    private WebDriver driver;

    public EventInfoPage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    @FindBy(css = ".attend")
    WebElement attendButton;

    @FindBy(css="#agenda")
    WebElement agenda;

    @FindBy(css=".date")
    WebElement date;

    @FindBy(css=".online-wrapper span")
    WebElement online;

    @FindBy(css=".location-wrapper span")
    WebElement location;

    @FindBy(css=".hero")
    WebElement headOfEvent;


    public WebElement getAttendButton(){
        return attendButton;
    }

    public boolean agendaIsPresent(){
        return agenda.isDisplayed();
    }
    public boolean headerIsPresent(){
        return headOfEvent.isDisplayed();
    }
    public boolean onlineIsPresent(){
        return online.isDisplayed();
    }

    public boolean dateIsPresent(){
        return date.isDisplayed();
    }

    public boolean attendButtonIsPresent(){
        return attendButton.isDisplayed();
    }
}
