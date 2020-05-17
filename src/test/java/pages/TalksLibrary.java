package pages;


import com.epam.healenium.SelfHealingDriver;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class TalksLibrary extends BasePage {

    private static final Logger logger = LogManager.getLogger(TalksLibrary.class);
    private SelfHealingDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    public TalksLibrary(SelfHealingDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        this.actions = new Actions(driver);
    }

    @FindBy(css = "#filter_category")
    protected WebElement filterCategory;

    @FindBy(css = "#filter_location")
    protected WebElement filterLocation;

    @FindBy(css = ".evnt-toggle-filters-button")
    protected WebElement moreFilters;

    @FindBy(css = "#filter_language")
    protected WebElement filterLanguage;

    @FindBy(css = ".evnt-card-wrapper")
    protected WebElement talksCard;

    @FindBy(xpath = "//div[@class='evnt-global-loader']")
    protected WebElement loader;

    @FindBy(xpath = "//input[@placeholder='Search by Talk Name']")
    protected WebElement searchField;

    @FindBy(css = ".evnt-card-body .evnt-card-cell")
    protected List<WebElement> talksNamesInCards;



    @Step("Переход на страницу доклада")
    public TalksLibrary openAnyTalksLibraryCard(){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        try {
            executor.executeScript("arguments[0].click()", talksCard);
            return this;
        }catch (NoSuchElementException e) {
            logger.error("No cards found");
        }
        return this;
    }

    @Step("Открытие фильтрации по параметру \"Category\"")
    public TalksLibrary selectInFilterCategory(String category){
        WebElement element = driver.findElement(By.cssSelector("[data-value='" + category +"']"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", element);
        return this;
    }

    @Step("Ожидание применения фильтров")
    public void waitForFiltersApply(){
        wait.until(ExpectedConditions.stalenessOf(loader));
    }

    @Step("Открытие фильтрации по параметру \"Location\"")
    public TalksLibrary clickFilterLocation(){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", filterLocation);
        return this;
    }
    @Step("Открытие фильтрации по параметру \"Category\"")
    public TalksLibrary clickFilterCategory(){
        actions.moveToElement(filterCategory).click().perform();
        return this;
    }

    @Step("Выбор локации в фильтре")
    public TalksLibrary selectInFilterLocation(String value){
        WebElement element = driver.findElement(By.xpath("//label[contains(text(),'" + value + "')]"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", element);
        return this;
    }

    @Step("Открытие фильтрации по параметру \"Language\"")
    public TalksLibrary clickFilterLanguage(){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", filterLanguage);
        return this;
    }

    @Step("Выбор языка в фильтре")
    public TalksLibrary selectInFilterLanguage(String value){
        WebElement element = driver.findElement(By.xpath("//label[contains(text(),'" + value + "')]"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", element);
        return this;
    }

    @Step("Ввод ключевого слова в строку поиска")
    public TalksLibrary enterInSearchField(String text){
        searchField.sendKeys(text);
        return this;
    }

    @Step("Открытие дополнительных фильтров")
    public TalksLibrary openMoreFilters(){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", moreFilters);
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
