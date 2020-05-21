package pages;

import com.epam.healenium.SelfHealingDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import java.util.*;
import java.util.List;

public class AllEventsPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(AllEventsPage.class);


    public AllEventsPage(SelfHealingDriver driver){
        PageFactory.initElements(driver,this);
    }

    List<String > expectedBlockClassNamesInEventsCard = Arrays.asList
            ("online",  "language", "evnt-event-name", "date", "status free-attend", "evnt-people-table");

    @FindBy(css = ".active .white")
    protected WebElement eventsCounter;

    @FindBy(xpath = "//div[@id='filter_location']")
    protected WebElement filterLocation;

    @FindBy(css = ".evnt-event-card .evnt-card-wrapper")
    protected WebElement eventsCard;

    @FindBy(css = ".evnt-event-card .evnt-card-wrapper")
    protected List<WebElement> eventList;

    @FindBy(css =".size-m .evnt-card-wrapper :nth-child(n + 1)")
    protected List<WebElement> cardInfoList;

    @FindBy(css=".evnt-cards-container:nth-child(1) .date")
    protected List<WebElement> datesOfPresentCards;

    @FindBy(xpath = "//h3[contains(text(),'This week')]")
    protected WebElement thisWeekSection;

    @FindBy(css=".nav-item+ .nav-item .desktop")
    protected WebElement pastEventsButton;

    @FindBy(xpath="//span[@class='evnt-tab-text desktop']")
    protected WebElement upcomingEventsButton;

    @FindBy(xpath="//div[@class='evnt-global-loader']")
    protected WebElement loader;


    public boolean thisWeekSectionIsPresent() {
        try {
            return thisWeekSection.isDisplayed();
        } catch (NoSuchElementException e) {
            logger.error(" No events this week");
            return false;
        }
    }
    public List<WebElement> getDatesOfPresentCards(){
        return datesOfPresentCards;
    }

    public WebElement getUpcomingEventsButton(){
        return upcomingEventsButton;
    }
    public WebElement getFilterLocation(){
        return filterLocation;
    }
    public WebElement getEventsCard(){
        return eventsCard;
    }
    public WebElement getPastEventsButton(){
        return pastEventsButton;
    }
    public WebElement getLoader(){
        return loader;
    }


    public List<String> blockNamesInEventsCard(){
        ArrayList<String>  list = new ArrayList<>();
        for (WebElement webElement : cardInfoList){
            String relText = webElement.getAttribute("class");
            list.add(relText);
        }
        return list;
    }

    public List<WebElement> getEventList(){
        return eventList;
    }

    public List<String> getDatesOfUpcomingeventsThisWeek() {
        ArrayList<String>  list = new ArrayList<>();
        for (WebElement webElement : datesOfPresentCards){
            String date = webElement.getText();
            list.add(date);
            logger.info("Date found in cards" + date);
        }
        return list;
    }

    public int getNumberOfEventsCardsOnPage(){
        logger.info("Event cards list size  is " + getEventList().size());
        return getEventList().size();
    }

    public int getNumOfEventsFromCounter(){
        int num = Integer.parseInt(eventsCounter.getText());
        logger.info("Events number in counter equal " + num);
        return num;
    }

    public List<String> getExpectedBlockClassNamesInEventsCard(){
        return expectedBlockClassNamesInEventsCard;
    }

    public boolean eventsCardIsPresent() {
        return eventsCard.isDisplayed();
    }

}
