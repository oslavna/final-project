package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.*;

import helpers.BaseHooks;



import java.text.ParseException;

public class EventsTests extends BaseHooks {

    private static final Logger logger = LogManager.getLogger(EventsTests.class);
    MainPage mainPage = new MainPage();
    AllEventsPage allEventsPage = new AllEventsPage();
    EventInfoPage eventInfoPage = new EventInfoPage();


    @Test
    public void checkOfUpcomingEventsCounter() {
        mainPage.openEventsPage();
        allEventsPage.clickUpcomingEventsButton();
        if (allEventsPage.upcomingEventsCardIsPresent())
            Assertions.assertEquals(allEventsPage.getNumberOfUpcomingEventsOnPage(),
                    allEventsPage.getNumOfUpcomingEventsFromCounter(),
                    "Numbers of events from page and from counter do not match!");
        else logger.info(" No cards in upcoming events");
    }

    @Test
    public void checkValidityDatesOfEventsOnThisWeek() throws ParseException {
        mainPage.openEventsPage();
        allEventsPage.clickUpcomingEventsButton();
        if (allEventsPage.thisWeekSectionIsPresent())
            for (String date : allEventsPage.getDatesOfUpcomingeventsThisWeek()) {
                Assertions.assertTrue(allEventsPage.checkDateOnValidity(date),
                        "Date is not valid");
            }
    }


    @Test
    public void checkCanadaEvents() throws InterruptedException {
        mainPage.openEventsPage();
        allEventsPage.clickPastEventsButton();
        allEventsPage.openLocationFilters();
        allEventsPage.chooseCanadaInList();
        allEventsPage.openLocationFilters();
        if (allEventsPage.upcomingEventsCardIsPresent())
            Assertions.assertEquals(allEventsPage.getNumberOfUpcomingEventsOnPage(),
                    allEventsPage.getNumOfUpcomingEventsFromCounter(),
                    "Numbers of events from page and from counter do not match!");
        else logger.info(" No cards in upcoming events");
    }

    @Test
    public void checkOpeningEventCard(){
        mainPage.openEventsPage();
        allEventsPage.clickUpcomingEventsButton();
        allEventsPage.clickEventsCard();
        eventInfoPage.agendaIsPresent();
        eventInfoPage.attendButtonIsPresent();
        eventInfoPage.dateIsPresent();
        eventInfoPage.headerIsPresent();
        eventInfoPage.onlineIsPresent();
    }


}




