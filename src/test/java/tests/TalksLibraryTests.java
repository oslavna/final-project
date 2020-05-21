package tests;

import helpers.BaseHooks;
import helpers.Steps;
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
    Steps steps;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        setup();
        mainPage = new MainPage(getDriver());
        talksLibrary = new TalksLibrary(getDriver());
        talksLibraryInfoPage = new TalksLibraryInfoPage(getDriver());
        steps = new Steps(getDriver());
    }

    @AfterEach
    public void tearDown() {
        logger.info("test completed");
        teardown();
    }

    @Test @DisplayName("Фильтрация докладов по категориям")
    public void checkFiltersApplyingOnTaskLibraryPage() {
        mainPage.openTalksLibPage();
        steps.clickFilterCategory().
                selectInFilterCategory("Design").
                openMoreFilters().
                clickFilterLocation().
                selectInFilterLocation("Belarus").
                clickFilterLanguage().selectInFilterLanguage("ENGLISH").
                waitForFiltersApply();
        steps.openAnyTalksLibraryCard();
        Assertions.assertTrue(talksLibraryInfoPage.TalksInfoPageIsOpenNow(), "The URL didn't change");
        Assertions.assertTrue(talksLibraryInfoPage.getLanguage().contains("ENGLISH"),
                "Language field doesn't contain the required parameter");
        Assertions.assertTrue(talksLibraryInfoPage.getLocation().contains("Belarus"),
                "Location field doesn't contain the required parameter");
        Assertions.assertTrue(talksLibraryInfoPage.getCategories().contains("Design"),
                "Category field doesn't contain the required parameter");
    }

    @Test @DisplayName("Поиск докладов по ключевому слову")
    public void searchForKeywordInTalksLib(){
        mainPage.openTalksLibPage();
        steps.enterInSearchField("Azure");
        steps.assertThatTalksContainTheKeyWord("Azure");
    }

}
