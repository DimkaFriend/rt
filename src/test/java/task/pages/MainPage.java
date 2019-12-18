package task.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

import java.util.List;

public class MainPage extends Page {

    @FindBys(value ={
            @FindBy(how = How.CSS, using = "a.rtk-footer__menu-link")
    } )
    @CacheLookup
    public List<WebElement> itemsMenuFooter;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void setLocationCity()
    {
        driver.findElement(By.xpath("//*[@class=\"rt-button rt-button-no-wive rt-button rt-button-orange text-center\"]")).click();
    }

    public FeedbackPopup displayPopup()
    {
        return PageFactory.initElements(driver,FeedbackPopup.class);
    }
}
