package task.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class FeedbackPopup extends Page {

    private final WebDriverWait wait;

    private final String idFeedbackPopup = "#feedbackForm";

    private final String close = "//a[@class=\"btn rtk-modal__close\"]";

    @FindBy(how = How.XPATH, using = "//form[@id=\"feedbackForm\"]//input[@name=\"fullName\"]")
    public WebElement fullName;

    @FindBy(how = How.XPATH, using = "//form[@id=\"feedbackForm\"]//input[@name=\"email\"]")
    public WebElement email;

    @FindBy(how = How.XPATH, using = "//form[@id=\"feedbackForm\"]//input[@name=\"phoneNumber\"]")
    public WebElement phone;

    @FindBy(how = How.XPATH, using = "//form[@id=\"feedbackForm\"]//div[@class=\"selectbox__wrap\"]")
    public List<WebElement> selectionItems;

    @FindBy(how = How.XPATH, using = "//form[@id=\"feedbackForm\"]//textarea[@name=\"message\"]")
    public WebElement message;

    @FindBy(how = How.XPATH, using = "//form[@id=\"feedbackForm\"]//input[@name=\"icheckbox\"]")
    public WebElement consent;

    @FindBy(how = How.XPATH, using = "//form[@id=\"feedbackForm\"]//button[@type=\"submit\"]")
    public WebElement submit;

    public FeedbackPopup(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//form[@id=\"feedbackForm\"]//button[@type=\"submit\"]"))));

    }
    /**
     * @param indexElementSelectManipulation is index of  tag element <selection>  at form #feedbackPopup
     * @param indexSelectedValue is index value for selection from opened list items*/
    public void selectionManipulation(int indexElementSelectManipulation, int indexSelectedValue)
    {
        this.selectionItems.get(indexElementSelectManipulation).click();
        this.driver.findElements(By.xpath("//div[@class=\"selectbox open\"]//li")).get(indexSelectedValue).click();
    }

    /**
     * @param indexElementSelectManipulation is index of  tag element <selection>  at form #feedbackPopup
     * @param textSelectedValue is text value for selection from opened list items*/
    public void selectionManipulation(int indexElementSelectManipulation, String textSelectedValue)
    {
        this.selectionItems.get(indexElementSelectManipulation).click();
        this.driver.findElements(By.xpath("//div[@class=\"selectbox open\"]//li")).stream().filter(x -> x.getText().equals(textSelectedValue)).forEach(x ->x.click());
    }

    private Boolean isDisplayed()  {

            WebElement popup =  driver.findElement(By.cssSelector(this.idFeedbackPopup));
            return popup.isDisplayed();

    }

    /**
     * This method is closed #feedbackPopup or
     * @throws NoSuchElementException if #feedbackPopup is not visible (disappeared)*/
    public void close()  {
        if (isDisplayed()) {
            WebElement closeElement = driver.findElement(By.xpath(this.close));
            closeElement.click();
        }
    }

    public String numberRequest()
    {
        return driver.findElement(By.xpath("//*[@id=\"block-b2cformaobratnoysvyazi\"]//div[2]//p/span")).getText();
    }

}
