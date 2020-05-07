package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import pages.*;
import helpers.*;

import helpers.BaseHooks;



import java.text.ParseException;

public class EventsTests extends BaseHooks {

    private static final Logger logger = LogManager.getLogger(EventsTests.class);
    MainPage mainPage = new MainPage();
    EventsPage eventsPage = new EventsPage();


    @Test
    public void checkOfUpcomingEventsCounter() {
        mainPage.openEventsPage();
        eventsPage.clickUpcomingEventsButton();
        if (eventsPage.upcomingEventsCardIsPresent())
            Assertions.assertEquals(eventsPage.getNumberOfUpcomingEventsOnPage(),
                    eventsPage.getNumOfUpcomingEventsFromCounter(),
                    "Numbers of events from page and from counter do not match!");
        else logger.info(" No cards in upcoming events");
    }

    @Test
    public void checkValidityDatesOfEventsOnThisWeek() throws ParseException {
        mainPage.openEventsPage();
        eventsPage.clickUpcomingEventsButton();
        if (eventsPage.thisWeekSectionIsPresent())
        for (String date : eventsPage.getDatesOfUpcomingeventsThisWeek()) {
            Assertions.assertTrue(eventsPage.checkDateOnValidity(date),
                    "Date is not valid");
            }
        }
    }


