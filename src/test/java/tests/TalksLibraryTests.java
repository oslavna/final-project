package tests;

import helpers.BaseHooks;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.StaleElementReferenceException;
import pages.*;

import java.net.MalformedURLException;


@Epic("Talks Library")
@Owner("Olga Slavnova")
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

    @Test @DisplayName("Фильтрация докладов по категориям")
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
        logger.info(new Object(){}.getClass().getEnclosingMethod().getName() + " test is executing now");
        mainPage.openTalksLibPage();
        talksLibrary.enterInSearchField("Azure");
        boolean success = false;
        while (!success) {
            try {
                talksLibrary.getTalksHeaders();
                success = true;
                for (String talk : talksLibrary.getTalksHeaders()) {
                    Assertions.assertTrue(talk.contains("Azure"), "headers don't contain the keyword");
                }
            } catch (StaleElementReferenceException e) {

            }
        }

        logger.info("test completed");
    }

}
