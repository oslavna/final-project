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

    @BeforeEach
    public void setUp() throws MalformedURLException {
        setup();
        mainPage = new MainPage(getDriver());
        allEventsPage = new AllEventsPage(getDriver());
        eventInfoPage = new EventInfoPage(getDriver());
    }

    @AfterEach
    public void tearDown() {
        logger.info("test completed");
        teardown();
    }

    @Test
    public void checkOfUpcomingEventsCounter() {
        mainPage.openEventsPage();
        allEventsPage.clickUpcomingEventsButton();
        if (allEventsPage.eventsCardIsPresent())
            Assertions.assertEquals(allEventsPage.getNumberOfEventsCardsOnPage(),
                    allEventsPage.getNumOfEventsFromCounter(),
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
        Assertions.assertIterableEquals(allEventsPage.getExpectedBlockClassNamesInEventsCard(),classNamesFromEventCard,
                "The order is wrong");
    }


    @Test
    public void checkValidityDatesOfEventsOnThisWeek() throws ParseException {
        mainPage.openEventsPage();
        allEventsPage.clickUpcomingEventsButton();
        if (allEventsPage.thisWeekSectionIsPresent())
            for (String date : allEventsPage.getDatesOfUpcomingeventsThisWeek()) {
                Assertions.assertTrue(allEventsPage.checkDateOnValidity(date),
                        "Date not within this week");
            }
    }


    @Test
    public void checkPastEventsInCountry() throws ParseException {
        logger.info(new Object(){}.getClass().getEnclosingMethod().getName() + " test is executing now");
        mainPage.openEventsPage();
        allEventsPage.clickPastEventsButton().
                openLocationFilters().
                chooseLocationInList("Armenia").
                waitForFiltersApply();
        Assertions.assertTrue(allEventsPage.datesIsBeforeToday(), "Dates from events card contain before today dates");
        if (allEventsPage.eventsCardIsPresent())
            Assertions.assertEquals(allEventsPage.getNumberOfEventsCardsOnPage(),
                    allEventsPage.getNumOfEventsFromCounter(),
                    "Numbers of events from page and from counter do not match!");
        else logger.info(" No cards in upcoming events");
    }


    @Test
    public void checkOpeningEventCard() {
        logger.info(new Object(){}.getClass().getEnclosingMethod().getName() + " test is executing now");
        mainPage.openEventsPage();
        allEventsPage.clickUpcomingEventsButton();
        allEventsPage.clickEventsCard();
        Assertions.assertTrue(eventInfoPage.attendButtonIsPresent(),
                "No attend button on event info page");
        Assertions.assertTrue(eventInfoPage.agendaIsPresent(),
                "No agenda on event info page");
        Assertions.assertTrue(eventInfoPage.dateIsPresent(),
                "No date on event info page");
        Assertions.assertTrue(eventInfoPage.headerIsPresent(),
                "No header on event info page");
        Assertions.assertTrue(eventInfoPage.onlineIsPresent()||eventInfoPage.locationIsPresent(),
                "No location on event info page");
    }


}




