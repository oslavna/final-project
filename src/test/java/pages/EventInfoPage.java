package pages;

import com.epam.healenium.SelfHealingDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

public class EventInfoPage extends BasePage{

    private static final Logger logger = LogManager.getLogger(EventInfoPage.class);

    public EventInfoPage(SelfHealingDriver driver){
        PageFactory.initElements(driver,this);
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


    public boolean agendaIsPresent(){
        logger.info(agenda.getText() + "   //: agenda text");
        return agenda.isDisplayed();
    }

    public boolean headerIsPresent(){
        logger.info(headOfEvent.getText() + "   //: displayed header text");
        return headOfEvent.isDisplayed();
    }

    public boolean onlineIsPresent() {
        logger.info(online.getText() + "   //: header text");
        return online.isDisplayed();
    }

    public boolean dateIsPresent(){
        logger.info(date.getText() + "   //: date");
        return date.isDisplayed();
    }
    public boolean locationIsPresent() {
        logger.info(location.getText() + "   //: location text");
        return location.isDisplayed();
    }

    public boolean attendButtonIsPresent(){
        logger.info(attendButton.getText() + "   //: attendButton text");
        return attendButton.isDisplayed();
    }
}
