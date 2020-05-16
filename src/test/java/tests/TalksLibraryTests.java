package tests;

import helpers.BaseHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import pages.*;

import java.net.MalformedURLException;

public class TalksLibraryTests extends BaseHooks {

    private static final Logger logger = LogManager.getLogger(TalksLibraryTests.class);
    MainPage mainPage;
    TalksLibrary talksLibrary;
    TalksLibraryInfoPage talksLibraryInfoPage;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        setup();
        mainPage = new MainPage(getDriver());
        talksLibrary = new TalksLibrary(getDriver());
        talksLibraryInfoPage = new TalksLibraryInfoPage(getDriver());
    }

    @AfterEach
    public void tearDown() {
        logger.info("test completed");
        teardown();
    }

    @Test
    public void checkFiltersApplyingOnTaskLibraryPage() {
        logger.info(new Object(){}.getClass().getEnclosingMethod().getName() + " test is executing now");
        mainPage.openTalksLibPage();
        talksLibrary.clickFilterCategory().
                selectInFilterCategory("Design").
                openMoreFilters().
                clickFilterLocation().
                selectInFilterLocation("Belarus").
                clickFilterLanguage().selectInFilterLanguage("ENGLISH").
                waitForFiltersApply();
        talksLibrary.openAnyTalksLibraryCard();
        Assertions.assertTrue(talksLibraryInfoPage.TalksInfoPageIsOpenNow());
        Assertions.assertTrue(talksLibraryInfoPage.getLanguage().contains("ENGLISH"));
        Assertions.assertTrue(talksLibraryInfoPage.getLocation().contains("Belarus"));
        Assertions.assertTrue(talksLibraryInfoPage.getCategories().contains("Design"));
    }

    @Test
    public void searchForKeywordInTalksLib(){
        logger.info(new Object(){}.getClass().getEnclosingMethod().getName() + " test is executing now");
        mainPage.openTalksLibPage();
        talksLibrary.enterInSearchField("Azure");
        talksLibrary.getTalksHeaders();
        for(String talk : talksLibrary.getTalksHeaders()){
            Assertions.assertTrue(talk.contains("Azure"));
        }
        logger.info("test completed");
    }

}
