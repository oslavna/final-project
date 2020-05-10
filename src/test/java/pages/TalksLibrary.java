package pages;


import helpers.BaseHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TalksLibrary extends BasePage {

    private static final Logger logger = LogManager.getLogger();

    @FindBy(css = "#filter_category")
    protected WebElement filterCategory;

    @FindBy(css = "#filter_media")
    protected WebElement filterMedia;

    @FindBy(css = "#filter_location")
    protected WebElement filterLocation;

    @FindBy(css = ".evnt-toggle-filters-button")
    protected WebElement moreFilters;

    @FindBy(css = "#filter_language")
    protected WebElement filterLanguage;

    @FindBy(xpath = "//label[@class='form-check-label group-items'][contains(text(),'Design')]")
    protected WebElement chooseCategory;

    @FindBy(css = ".language-cell .language")
    protected WebElement languageInCards;

    @FindBy(xpath = "//label[contains(text(),'Belarus')]")
    protected WebElement chooseLocation;

    @FindBy(css = ".evnt-card-wrapper")
    protected WebElement talksCard;

    @FindBy(xpath = "//div[@class='evnt-global-loader']")
    protected WebElement loader;


    public TalksLibrary openMoreFilters(){
        moreFilters.click();
        return this;
    }

    public TalksLibrary openAnyTalksLibraryCard(){
        try {
            String script = "document.querySelector(\".evnt-card-wrapper\").click();";
            ((JavascriptExecutor) BaseHooks.getDriver()).executeScript(script);
            return this;
        }catch (NoSuchElementException e) {
            logger.error("No cards found");
        }
        return this;
    }

    public void waitForFiltersApply(String language){
        BaseHooks.getWait().until(ExpectedConditions.textToBePresentInElement(talksCard,language));
    }


    public TalksLibrary chooseInFilterCategory(String value){
        filterCategory.click();
        BaseHooks.getDriver().findElement(By.xpath("//label[@class='form-check-label group-items'][contains(text(),'" + value + "')]")).click();
        return this;
    }


    public TalksLibrary chooseInFilterLocation(String value){
        filterLocation.click();
        BaseHooks.getDriver().findElement(By.xpath("//label[contains(text(),'" + value + "')]")).click();
        return this;
    }

    public TalksLibrary chooseInFilterLanguage(String value){
        filterLanguage.click();
        BaseHooks.getDriver().findElement(By.xpath("//label[contains(text(),'" + value + "')]")).click();
        return this;
    }







}
