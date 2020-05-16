package pages;

import helpers.BaseHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Connection;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TalksLibraryInfoPage extends BasePage {

    private static final Logger logger = LogManager.getLogger();

    private WebDriver driver;

    public TalksLibraryInfoPage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    @FindBy(css = ".location span")
    protected WebElement location;

    @FindBy(css = ".evnt-topics-wrapper")
    protected WebElement categories;

    @FindBy(css = ".language.evnt-now-past-talk")
    protected WebElement language;


    public void logCurrentUrl() {
        //logger.info("Current url is " + BaseHooks.getCurrentUrl());
    }

    public String getLanguage(){
        return language.getText();
    }

    public String getCategories(){
        return categories.getText();
    }

    public String getLocation(){
        return location.getText();
    }

}
