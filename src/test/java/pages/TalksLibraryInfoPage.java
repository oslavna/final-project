package pages;

import com.epam.healenium.SelfHealingDriver;
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

    private static final Logger logger = LogManager.getLogger(TalksLibraryInfoPage.class);

    private SelfHealingDriver driver;

    public TalksLibraryInfoPage(SelfHealingDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    @FindBy(css = ".location span")
    protected WebElement location;

    @FindBy(css = ".evnt-topics-wrapper")
    protected WebElement categories;

    @FindBy(css = ".language.evnt-now-past-talk")
    protected WebElement language;

    public boolean TalksInfoPageIsOpenNow() {
        logger.info("Current url is " + driver.getCurrentUrl());
        return (!driver.getCurrentUrl().equals(baseUrl + "/talks"));
    }

    public String getLanguage(){ return language.getText();   }

    public String getCategories(){
        return categories.getText();
    }

    public String getLocation(){
        return location.getText();
    }

}
