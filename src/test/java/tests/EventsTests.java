package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import pages.*;

import helpers.BaseHooks;


import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EventsTests extends BaseHooks {

    private static final Logger logger = LogManager.getLogger(EventsTests.class);
    private MainPage mainPage;
    private AllEventsPage allEventsPage;
    private EventInfoPage eventInfoPage;
    private TalksLibrary talksLibrary;
    private TalksLibraryInfoPage talksLibraryInfoPage;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        setup();
        mainPage = new MainPage(getDriver());
        allEventsPage = new AllEventsPage(getDriver());
        eventInfoPage = new EventInfoPage(getDriver());
    }

    @AfterEach
    public void tearDown() {
        teardown();
    }


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
    public void checkOfTheOrderOfDisplayedBlocksInEventsCard() {
        mainPage.openEventsPage();
        allEventsPage.clickUpcomingEventsButton();
        allEventsPage.blockNamesInEventsCard();
        List<String> classNamesFromEventCard = new ArrayList<>();
        for(String name : allEventsPage.blockNamesInEventsCard()) {
            for (String blockClass : allEventsPage.getExpectedBlockClassNamesInEventsCard()) {
                if (name.equals(blockClass) || name.equals("location"))  {
                    logger.info("block class name -  " + name + "; the number in list is " + allEventsPage.blockNamesInEventsCard().indexOf(name));
                    classNamesFromEventCard.add(name);
                    System.out.println("block class " + blockClass);
                }
            }
        }
        Assertions.assertIterableEquals(allEventsPage.getExpectedBlockClassNamesInEventsCard(),classNamesFromEventCard);
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
        allEventsPage.chooseLocationInList("Canada");
        allEventsPage.openLocationFilters();
        allEventsPage.waitForLoader();
       //Thread.sleep(2000);
        if (allEventsPage.upcomingEventsCardIsPresent())
            Assertions.assertEquals(allEventsPage.getNumberOfUpcomingEventsOnPage(),
                    allEventsPage.getNumOfUpcomingEventsFromCounter(),
                    "Numbers of events from page and from counter do not match!");
        else logger.info(" No cards in upcoming events");
    }

//    TO DO HERE
//    make Canada as parameter of method

    @Test
    public void checkOpeningEventCard() {
        mainPage.openEventsPage();
        allEventsPage.clickUpcomingEventsButton();
        allEventsPage.clickEventsCard();
        eventInfoPage.agendaIsPresent();
        eventInfoPage.attendButtonIsPresent();
        eventInfoPage.dateIsPresent();
        eventInfoPage.headerIsPresent();
        eventInfoPage.onlineIsPresent();

    }
    //TO DO here
    //переделать этот тест, добавить проверки

}




