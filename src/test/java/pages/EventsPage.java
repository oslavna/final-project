package pages;

import helpers.BaseHooks;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.*;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EventsPage extends BasePage {

    String pageUrl = baseUrl + "/events";

    @FindBy(css = ".active .white")
    protected WebElement upcomingEventsCounter;

    @FindBy(css = ".lavender .evnt-card-wrapper")
    protected List<WebElement> eventList;

    @FindBy(css =".evnt-events-tabs-container.tab-content :nth-last-child(n+1)")
    protected List<WebElement> cardInfoList;

    @FindBy(css=".evnt-cards-container:nth-child(1) .date")
    protected List<WebElement> datesOfThisWeek;



//    public WebElement getUpcomingEventsCounter() {
//        return upcomingEventsCounter;
//    }



    public List<WebElement> getEventList(){
        return eventList;
    }
    public List<WebElement> getCardInfo(){
        return cardInfoList;
    }

    public List<Date> getDatesOfUpcomingeventsThisWeek() throws ParseException {
        ArrayList<Date>  list = new ArrayList<>();
        for (WebElement webElement : datesOfThisWeek){
            String relText = webElement.getText();
            list.add(datesHelper.convertToDate(relText));
            System.out.println(relText);
        }
        return list;
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
       return getEventList().size();
    }

    public void openEventsPage(){
        BaseHooks.getDriver().get(pageUrl);
    }

    public String getNumOfUpcomingEventsFromCounter(){
        return upcomingEventsCounter.getText();
    }


}
