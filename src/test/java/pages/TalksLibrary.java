package pages;


import helpers.BaseHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Connection;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;


import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class TalksLibrary extends BasePage {

    private static final Logger logger = LogManager.getLogger();

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;


    public TalksLibrary(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        this.actions = new Actions(driver);
    }



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

    @FindBy(xpath = "//input[@placeholder='Search by Talk Name']")
    protected WebElement searchField;

    @FindBy(css = ".evnt-card-body .evnt-card-cell")
    protected List<WebElement> talksNamesInCards;


    public WebElement getFilterByCategory(){
        return filterCategory;
    }

    public TalksLibrary openMoreFilters(){
       // moreFilters.click();
//        element = driver.find_element_by_class_name('pagination-r')
//        driver.execute_script("arguments[0].click();", element)

//        WebElement element = BaseHooks.getWait().until(ExpectedConditions.elementToBeClickable(moreFilters));
//        element.click();


        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", moreFilters);

        return this;
    }

    public TalksLibrary openAnyTalksLibraryCard(){
        try {
            String script = "document.querySelector(\".evnt-card-wrapper\").click();";
            ((JavascriptExecutor) driver).executeScript(script);
            return this;
        }catch (NoSuchElementException e) {
            logger.error("No cards found");
        }
        return this;
    }

    public void waitForFiltersApply(String language){
        //BaseHooks.getWait().until(ExpectedConditions.textToBePresentInElement(talksCard,language));
    }


    public TalksLibrary selectDesignInFilterCategory(){
        String script = "document.querySelector(\"[data-value='Design']\").click();";
        ((JavascriptExecutor) driver).executeScript(script);
        return this;
    }

    public void waitForLoader(){
        WebElement loader = driver.findElement(By.cssSelector(".evnt-global-loader"));
        wait.until(ExpectedConditions.stalenessOf(loader));
    }


    public TalksLibrary clickFilterLocation(){
        //BaseHooks.getDriver().findElement(By.cssSelector("#filter_location")).click();

        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", filterLocation);
        return this;
    }

    public TalksLibrary clickFilterCategory(){
        actions.moveToElement(driver.findElement(By.cssSelector("#filter_category"))).click().perform();
        return this;
    }


    public TalksLibrary selectInFilterLocation(String value){
        WebElement element = driver.findElement(By.xpath("//label[contains(text(),'" + value + "')]"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", element);
       // BaseHooks.getDriver().findElement(By.xpath("//label[contains(text(),'" + value + "')]")).click();
        return this;
    }

    public TalksLibrary clickFilterLanguage(){
        //BaseHooks.getDriver().findElement(By.cssSelector("#filter_location")).click();

        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", filterLanguage);
        return this;
    }


    public TalksLibrary selectInFilterLanguage(String value){
        WebElement element = driver.findElement(By.xpath("//label[contains(text(),'" + value + "')]"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", element);

       // filterLanguage.click();
       // BaseHooks.getDriver().findElement(By.xpath("//label[contains(text(),'" + value + "')]")).click();
        return this;
    }

    public TalksLibrary enterInSearchField(String text){
        searchField.sendKeys(text);
        return this;
    }

    public List<String> getTalksHeaders() {
        List<String> list = new ArrayList<>();
        for (WebElement webElement : talksNamesInCards) {
            String talksHeader = webElement.getText();
            list.add(talksHeader);
            logger.info("the talk was find: " + talksHeader);
        }
        return list;
    }
}
