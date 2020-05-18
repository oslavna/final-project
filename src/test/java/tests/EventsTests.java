package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebElement;
import pages.*;
import helpers.BaseHooks;


import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Epic("Events in EPAM")
@Owner("Olga Slavnova")
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

    @Test @DisplayName("Просмотр предстоящих мероприятий")
    public void checkOfUpcomingEventsCounter() {
        logger.info(new Object(){}.getClass().getEnclosingMethod().getName() + " test is executing now");
        mainPage.openEventsPage();
        allEventsPage.clickUpcomingEventsButton();
        if (allEventsPage.eventsCardIsPresent())
            Assertions.assertEquals(allEventsPage.getNumberOfEventsCardsOnPage(),
                    allEventsPage.getNumOfEventsFromCounter(),
                    "Numbers of events from page and from counter do not match!");
        else logger.info(" No cards in upcoming events");
    }


    @Test @DisplayName("Проверка порядка отображаемых блоков с информацией в карточке мероприятия")
    public void checkOfTheOrderOfDisplayedBlocksInEventsCard() {
        logger.info(new Object(){}.getClass().getEnclosingMethod().getName() + " test is executing now");
        mainPage.openEventsPage();
        allEventsPage.clickUpcomingEventsButton();
        List<String> actualClassNamesFromEventCard = new ArrayList<>();
        for(String name : allEventsPage.blockNamesInEventsCard()){
            for (String blockClass : allEventsPage.getExpectedBlockClassNamesInEventsCard()){
                if (name.equals(blockClass)){
                    actualClassNamesFromEventCard.add(name);
                    System.out.print(name);
                }
            }
            if (actualClassNamesFromEventCard.size() == allEventsPage.getExpectedBlockClassNamesInEventsCard().size()) break;
        }
         Assertions.assertIterableEquals(allEventsPage.getExpectedBlockClassNamesInEventsCard(),actualClassNamesFromEventCard,
                 "Event cards blocks are out of order or some blocks are missing");
    }



    @Test @DisplayName("Валидация дат предстоящих мероприятий")
    public void checkValidityDatesOfEventsOnThisWeek() throws ParseException {
        logger.info(new Object(){}.getClass().getEnclosingMethod().getName() + " test is executing now");
        mainPage.openEventsPage();
        allEventsPage.clickUpcomingEventsButton();
        if (allEventsPage.thisWeekSectionIsPresent())
            for (String date : allEventsPage.getDatesOfUpcomingeventsThisWeek()) {
                Assertions.assertTrue(allEventsPage.checkDateOnValidity(date),
                        "Date not within this week");
            }
    }


    @Test @DisplayName("Просмотр прошедших мероприятий в Канаде")
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


    @Test @DisplayName("Просмотр детальной информации о мероприятии")
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




