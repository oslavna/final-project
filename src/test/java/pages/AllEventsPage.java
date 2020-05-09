package pages;

import helpers.BaseHooks;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.text.ParseException;
import java.util.*;
import java.util.List;

public class AllEventsPage extends BasePage {

    String pageUrl = baseUrl + "/events";
    private static final Logger logger = LogManager.getLogger(AllEventsPage.class);


    @FindBy(css = ".active .white")
    protected WebElement upcomingEventsCounter;

    @FindBy(css = ".nav-item+ .nav-item .white")
    protected WebElement pastEventsCounter;

    @FindBy(xpath = "//div[@id='filter_location']//span[@class='evnt-filter-text']")
    protected WebElement filterLocation;

    @FindBy(xpath = "//div[@class='evnt-dropdown-filter dropdown show']//div[4]//div[1]//div[2]//label[1]")
    protected WebElement checkboxCanada;

    @FindBy(css = ".size-m .evnt-card-wrapper")
    protected WebElement upcomingEventsCard;

    @FindBy(css = ".size-m .evnt-card-wrapper")
    protected List<WebElement> eventList;

    @FindBy(css =".evnt-events-tabs-container.tab-content :nth-last-child(n+1)")
    protected List<WebElement> cardInfoList;

    @FindBy(css=".evnt-cards-container:nth-child(1) .date")
    protected List<WebElement> datesOfThisWeek;


    @FindBy(xpath = "//h3[contains(text(),'This week')]")
    protected WebElement thisWeekSection;

    @FindBy(css=".nav-item+ .nav-item .desktop")
    protected WebElement pastEventsButton;

    @FindBy(xpath="//span[@class='evnt-tab-text desktop']")
    protected WebElement upcomingEventsButton;





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
        filterLocation.click();
    }

    public WebElement getUpcomingEventsCard(){
        return upcomingEventsCard;
   }

    public void chooseCanadaInList() throws InterruptedException {
        checkboxCanada.click();
        //BaseHooks.getWait().until(ExpectedConditions.invisibilityOf(eventCard));
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

    public List<String> listOfValue(){
        ArrayList<String>  list = new ArrayList<>();
        for (WebElement webElement : cardInfoList){
            String relText = webElement.getAttribute("class");
            list.add(relText);
            System.out.println(relText);
        }
        return list;
    }



    public int getNumberOfUpcomingEventsOnPage(){
        logger.info("Event cards list size  is " + getEventList().size());
        return getEventList().size();
    }

    public void openEventsPage(){
        BaseHooks.getDriver().get(pageUrl);
    }

    public int getNumOfUpcomingEventsFromCounter(){
        int num = Integer.parseInt(upcomingEventsCounter.getText());
        logger.info("Events number in counter equal " + num);
        return num;

    }




}
