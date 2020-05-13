package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.*;

import helpers.BaseHooks;



import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class EventsTests extends BaseHooks {

    private static final Logger logger = LogManager.getLogger(EventsTests.class);
    MainPage mainPage = new MainPage();
    AllEventsPage allEventsPage = new AllEventsPage();
    EventInfoPage eventInfoPage = new EventInfoPage();
    TalksLibrary talksLibrary = new TalksLibrary();
    TalksLibraryInfoPage talksLibraryInfoPage = new TalksLibraryInfoPage();


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
        allEventsPage.chooseCanadaInList();
        allEventsPage.openLocationFilters();
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


    @Test
    public void checkFilters() {
        mainPage.openTalksLibPage();
        talksLibrary.chooseInFilterCategory("Design");
        talksLibrary.openMoreFilters().
                chooseInFilterLocation("Belarus").
                chooseInFilterLanguage("ENGLISH")
                .waitForFiltersApply("Eng");
        talksLibrary.openAnyTalksLibraryCard();
        talksLibraryInfoPage.logCurrentUrl();
        talksLibraryInfoPage.getLanguage();
        Assertions.assertTrue(talksLibraryInfoPage.getLanguage().contains("ENGLISH"));
        Assertions.assertTrue(talksLibraryInfoPage.getLocation().contains("Belarus"));
        Assertions.assertTrue(talksLibraryInfoPage.getCategories().contains("Design"));

//        TO DO here:
//        change checks for all cards
//        log info in checks
//        improve asserts (as error collector )

    }

    @Test
    public void searchForKeyword(){
        mainPage.openTalksLibPage();
        talksLibrary.enterInSearchField("Azure");
        talksLibrary.getTalksHeaders();
        for(String talk : talksLibrary.getTalksHeaders()){
            Assertions.assertTrue(talk.contains("Azure"));
        }
    }

//        TO DO HERE:
//    add exception for case whe cards are not found


}




