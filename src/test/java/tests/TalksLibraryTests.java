package tests;

import helpers.BaseHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import pages.*;

import java.net.MalformedURLException;

public class TalksLibraryTests extends BaseHooks {


    private static final Logger logger = LogManager.getLogger(EventsTests.class);
    MainPage mainPage;
    private TalksLibrary talksLibrary;
    private TalksLibraryInfoPage talksLibraryInfoPage;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        setup();
        mainPage = new MainPage(getDriver());
        talksLibrary = new TalksLibrary(getDriver());
        talksLibraryInfoPage = new TalksLibraryInfoPage(getDriver());
    }

    @AfterEach
    public void tearDown() {
        teardown();
    }


    @Test
    public void checkFilters() {
        mainPage.openTalksLibPage();
        talksLibrary.clickFilterCategory();
        talksLibrary.selectDesignInFilterCategory();
        talksLibrary.openMoreFilters().
                clickFilterLocation().
                selectInFilterLocation("Belarus").
                clickFilterLanguage().selectInFilterLanguage("ENGLISH");
        //.waitForFiltersApply("Eng");
        talksLibrary.waitForLoader();
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
