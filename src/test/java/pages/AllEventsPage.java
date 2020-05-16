package pages;

import com.epam.healenium.SelfHealingDriver;
import helpers.BaseHooks;

import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.text.ParseException;
import java.util.*;
import java.util.List;

public class AllEventsPage extends BasePage {

    private SelfHealingDriver driver;
    private WebDriverWait wait;
    private Actions actions;


    public AllEventsPage(SelfHealingDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        this.actions = new Actions(driver);
    }


    String pageUrl = baseUrl + "/events";

    private static final Logger logger = LogManager.getLogger(AllEventsPage.class);

    List<String > expectedBlockClassNamesInEventsCard = Arrays.asList
            ("online",  "language", "evnt-event-name", "date", "status free-attend", "evnt-people-table");

    @FindBy(css = ".active .white")
    protected WebElement upcomingEventsCounter;

    @FindBy(css = ".nav-item+ .nav-item .white")
    protected WebElement pastEventsCounter;

    @FindBy(xpath = "//div[@id='filter_location']")
    protected WebElement filterLocation;

    @FindBy(xpath = "//label[contains(text(),'Canada')]")
    protected WebElement checkboxCanada;

    @FindBy(css = ".evnt-event-card .evnt-card-wrapper")
    protected WebElement upcomingEventsCard;

    @FindBy(css = ".evnt-event-card .evnt-card-wrapper")
    protected List<WebElement> eventList;

    @FindBy(css =".cell-3:nth-child(1) .size-m .evnt-card-wrapper :nth-child(n + 1)")
    protected List<WebElement> cardInfoList;

    @FindBy(css=".evnt-cards-container:nth-child(1) .date")
    protected List<WebElement> datesOfThisWeek;

    @FindBy(xpath = "//h3[contains(text(),'This week')]")
    protected WebElement thisWeekSection;

    @FindBy(css=".nav-item+ .nav-item .desktop")
    protected WebElement pastEventsButton;

    @FindBy(xpath="//span[@class='evnt-tab-text desktop']")
    protected WebElement upcomingEventsButton;

    @FindBy(xpath="//div[@class='evnt-global-loader']")
    protected WebElement loader;


    public List<String> getExpectedBlockClassNamesInEventsCard(){
        return expectedBlockClassNamesInEventsCard;
    }

    public boolean upcomingEventsCardIsPresent() {
        return upcomingEventsCard.isDisplayed();
    }

    public void clickUpcomingEventsButton(){
        upcomingEventsButton.click();
    }

    public void clickEventsCard(){
        upcomingEventsCard.click();
    }

    public void clickPastEventsButton(){
        pastEventsButton.click();
    }

    public void openLocationFilters(){
        actions.moveToElement(driver.findElement
                (By.cssSelector("#filter_location"))).click().perform();


    }

    public WebElement getUpcomingEventsCard(){
        return upcomingEventsCard;
   }

   public void waitForLoader(){
           WebElement loader = driver.findElement(By.cssSelector(".evnt-global-loader"));
            wait.until(ExpectedConditions.stalenessOf(loader));
       }



    public void chooseLocationInList(String value) {
        String script = "document.querySelector(\"[data-value='Canada']\").click();";
        ((JavascriptExecutor) driver).executeScript(script);

    }

    public List<WebElement> getEventList(){
        return eventList;
    }

    public List<WebElement> getCardInfo(){
        return cardInfoList;
    }


    public List<String> getDatesOfUpcomingeventsThisWeek() throws ParseException {
        ArrayList<String>  list = new ArrayList<>();
        for (WebElement webElement : datesOfThisWeek){
            String relText = webElement.getText();
            list.add(relText);
            System.out.println(relText);
        }
        return list;
    }

    public boolean checkDateOnValidity(String textDate) throws ParseException {
        return datesHelper.checkDate(textDate);
    }

    public boolean thisWeekSectionIsPresent() {
        try {
            return thisWeekSection.isDisplayed();
        } catch (NoSuchElementException e) {
            logger.error(" No events this week");
            return false;
        }
    }

    public List<String> blockNamesInEventsCard(){
        ArrayList<String>  list = new ArrayList<>();
        for (WebElement webElement : cardInfoList){
            String relText = webElement.getAttribute("class");
            list.add(relText);
            //System.out.println(relText);
        }
        return list;
    }


    public int getNumberOfUpcomingEventsOnPage(){
        logger.info("Event cards list size  is " + getEventList().size());
        return getEventList().size();
    }

    public int getNumOfUpcomingEventsFromCounter(){
        int num = Integer.parseInt(upcomingEventsCounter.getText());
        logger.info("Events number in counter equal " + num);
        return num;
    }


}
