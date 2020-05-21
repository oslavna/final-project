package helpers;

import com.epam.healenium.SelfHealingDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AllEventsPage;
import pages.TalksLibrary;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Steps {

    private static final Logger logger = LogManager.getLogger(Steps.class);

    private AllEventsPage allEventsPage;
    private TalksLibrary talksLibrary;
    private SelfHealingDriver driver;
    private WebDriverWait wait;
    private Actions actions;
    private DatesHelper datesHelper;


    public Steps(SelfHealingDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        allEventsPage = new AllEventsPage(driver);
        talksLibrary = new TalksLibrary(driver);
        datesHelper = new DatesHelper();
        this.wait = new WebDriverWait(driver, 10);
        this.actions = new Actions(driver);
    }

    public void assertThatEventsCounterCorrect() {
        if (allEventsPage.eventsCardIsPresent())
            Assertions.assertEquals(allEventsPage.getNumberOfEventsCardsOnPage(),
                    allEventsPage.getNumOfEventsFromCounter(),
                    "Numbers of events from page and from counter do not match!");
        else logger.info(" No cards in upcoming events");
    }


    public void assertTheOrderOfBlocksInCardIsCorrect() {
        List<String> actualClassNamesFromEventCard = new ArrayList<>();
        for (String name : allEventsPage.blockNamesInEventsCard()) {
            for (String blockClass : allEventsPage.getExpectedBlockClassNamesInEventsCard()) {
                if (name.equals(blockClass)) {
                    actualClassNamesFromEventCard.add(name);
                    System.out.print(name);
                }
            }
            if (actualClassNamesFromEventCard.size() == allEventsPage.getExpectedBlockClassNamesInEventsCard().size())
                break;
        }
        Assertions.assertIterableEquals(allEventsPage.getExpectedBlockClassNamesInEventsCard(), actualClassNamesFromEventCard,
                "Event cards blocks are out of order or some blocks are missing");
    }

    public void assertThatDateIsWithinThisWeek() throws ParseException {
        if (allEventsPage.thisWeekSectionIsPresent())
            for (String date : allEventsPage.getDatesOfUpcomingeventsThisWeek()) {
                Assertions.assertTrue(checkDateOnValidity(date),
                        "Date not within this week");
            }
    }

    public void assertThatTalksContainTheKeyWord(String value) {
        talksLibrary.getTalksHeaders();
        for (String talk : talksLibrary.getTalksHeaders()) {
            Assertions.assertTrue(talk.contains(value), "headers don't contain the keyword");
        }
    }

    public void clickUpcomingEventsButton(){
        allEventsPage.getUpcomingEventsButton().click();
    }


    public void clickEventsCard(){
        allEventsPage.getEventsCard().click();
    }


    public Steps clickPastEventsButton(){
        allEventsPage.getPastEventsButton().click();
        return this;
    }

    public Steps openLocationFilters(){
        actions.moveToElement(allEventsPage.getFilterLocation()).click().perform();
        return this;
    }

    public Steps selectLocationInList(String country) {
        WebElement element = driver.findElement(By.cssSelector("[data-value='" + country +"']"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", element);
        return this;
    }

    public void waitForFiltersApply(){
        wait.until(ExpectedConditions.stalenessOf(allEventsPage.getLoader()));
    }


    public boolean checkDateOnValidity(String textDate) throws ParseException {
        return datesHelper.checkDate(textDate);
    }


    public boolean datesIsBeforeToday() throws ParseException {
        for (WebElement webElement : allEventsPage.getDatesOfPresentCards()) {
            String date = webElement.getText();
            if (datesHelper.dateIsBeforeToday(date))
                return true;
        }
        return false;
    }


    public Steps openAnyTalksLibraryCard(){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        try {
            executor.executeScript("arguments[0].click()", talksLibrary.getTalksCard());
            return this;
        }catch (NoSuchElementException e) {
            logger.error("No cards found");
        }
        return this;
    }


    public Steps selectInFilterCategory(String category){
        WebElement element = driver.findElement(By.cssSelector("[data-value='" + category +"']"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", element);
        return this;
    }

    public Steps clickFilterLocation(){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", talksLibrary.getFilterLocation());
        return this;
    }

    public Steps clickFilterCategory(){
        actions.moveToElement(talksLibrary.getFilterCategory()).click().perform();
        return this;
    }

    public Steps selectInFilterLocation(String value){
        WebElement element = driver.findElement(By.xpath("//label[contains(text(),'" + value + "')]"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", element);
        return this;
    }

    public Steps clickFilterLanguage(){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", talksLibrary.getFilterLanguage());
        return this;
    }

    public Steps selectInFilterLanguage(String value){
        WebElement element = driver.findElement(By.xpath("//label[contains(text(),'" + value + "')]"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", element);
        return this;
    }

    public Steps enterInSearchField(String text){
        talksLibrary.getSearchField().sendKeys(text);
        return this;
    }

    public Steps openMoreFilters(){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", talksLibrary.getMoreFilters());
        return this;
    }

}
