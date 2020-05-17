package pages;

import com.epam.healenium.SelfHealingDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BasePage {

    private SelfHealingDriver driver;

    public MainPage(SelfHealingDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    @FindBy(css = ".nav-item:nth-child(2) .nav-link")
    protected WebElement eventsTabButton;

    @FindBy(css = ".nav-item:nth-child(3) .nav-link")
    protected WebElement talksLibTabButton;


    @Step("Открытие страницы мероприятий")
    public void openEventsPage(){
        driver.get(baseUrl);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", eventsTabButton);
    }

    @Step("Открытие страницы докладов")
    public void openTalksLibPage(){
        driver.get(baseUrl);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", talksLibTabButton);
    }

}
