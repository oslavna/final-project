package tests;

import helpers.Steps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import pages.*;
import helpers.BaseHooks;
import java.net.MalformedURLException;
import java.text.ParseException;



@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EventsTests extends BaseHooks {

    private static final Logger logger = LogManager.getLogger(EventsTests.class);
    private MainPage mainPage;
    private EventInfoPage eventInfoPage;
    private Steps steps;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        setup();
        mainPage = new MainPage(getDriver());
        eventInfoPage = new EventInfoPage(getDriver());
        steps = new Steps(getDriver());
    }

    @AfterEach
    public void tearDown() {
        logger.info("test completed");
        teardown();
    }

    @Test @DisplayName("Просмотр предстоящих мероприятий")
    public void checkOfUpcomingEventsCounter() {
        mainPage.openEventsPage();
        steps.clickUpcomingEventsButton();
        steps.assertThatEventsCounterCorrect();
    }


    @Test @DisplayName("Проверка порядка отображаемых блоков с информацией в карточке мероприятия")
    public void checkOfTheOrderOfDisplayedBlocksInEventsCard() {
        mainPage.openEventsPage();
        steps.clickUpcomingEventsButton();
        steps.assertTheOrderOfBlocksInCardIsCorrect();
    }



    @Test @DisplayName("Валидация дат предстоящих мероприятий")
    public void checkValidityDatesOfEventsOnThisWeek() throws ParseException {
        mainPage.openEventsPage();
        steps.clickUpcomingEventsButton();
        steps.assertThatDateIsWithinThisWeek();
    }


    @Test @DisplayName("Просмотр прошедших мероприятий в стране")
    public void checkPastEventsInCountry() throws ParseException {
        mainPage.openEventsPage();
        steps.clickPastEventsButton().
                openLocationFilters().
                selectLocationInList("Armenia").
                waitForFiltersApply();
        Assertions.assertTrue(steps.datesIsBeforeToday(), "Dates from events card contain before today dates");
        steps.assertThatEventsCounterCorrect();
    }


    @Test @DisplayName("Просмотр детальной информации о мероприятии")
    public void checkOpeningEventCard() {
        mainPage.openEventsPage();
        steps.clickUpcomingEventsButton();
        steps.clickEventsCard();
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




