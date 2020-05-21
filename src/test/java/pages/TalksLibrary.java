package pages;


import com.epam.healenium.SelfHealingDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.util.ArrayList;
import java.util.List;

public class TalksLibrary extends BasePage {

    private static final Logger logger = LogManager.getLogger(TalksLibrary.class);

    public TalksLibrary(SelfHealingDriver driver){
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "#filter_category")
    protected WebElement filterCategory;

    @FindBy(css = "#filter_location")
    protected WebElement filterLocation;

    @FindBy(css = ".evnt-toggle-filters-button")
    protected WebElement moreFilters;

    @FindBy(css = ".evnt-card-wrapper")
    protected WebElement talksCard;

    @FindBy(xpath = "//input[@placeholder='Search by Talk Name']")
    protected WebElement searchField;

    @FindBy(css = ".evnt-card-body .evnt-card-cell")
    protected List<WebElement> talksNamesInCards;

    public WebElement getFilterCategory(){
        return filterCategory;
    }
    public WebElement getFilterLocation(){
        return filterLocation;
    }
    public WebElement getMoreFilters(){
        return moreFilters;
    }
    public WebElement getFilterLanguage(){
        return filterLocation;
    }
    public WebElement getTalksCard(){
        return talksCard;
    }
    public WebElement getSearchField(){
        return searchField;
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
