package task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import task.Utilities.TestUtils;
import task.pages.FeedbackPopup;
import task.pages.MainPage;

public class TestMainPage extends JUnitTestBase {
    private MainPage mainpage;
    private FeedbackPopupObject feedbackPopupObject;
    @BeforeEach
    public void initPageObjects() {
        feedbackPopupObject = new FeedbackPopupObject(TestUtils.properties);
        mainpage = PageFactory.initElements(driver, MainPage.class);

        driver.get(baseUrl);
        driver.manage().window().maximize();
        mainpage.setLocationCity();
        new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@class=\"rt-font-control color-light-white rt-md-space-top05\"]/a"))));
    }

    @Test
    public void testFeedbackPopupValid()  {

        mainpage.itemsMenuFooter.stream().filter(x-> x.getText().equals("Обратная связь")).forEach(x->x.click());
        FeedbackPopup feedbackPopup = mainpage.displayPopup();
        feedbackPopup.fullName.sendKeys(feedbackPopupObject.getFullName());
        feedbackPopup.email.sendKeys(feedbackPopupObject.getEmail());
        feedbackPopup.phone.sendKeys(feedbackPopupObject.getPhoneNumber());
        feedbackPopup.selectionManipulation(0,feedbackPopupObject.getMethodFeedback()[1]);
        feedbackPopup.selectionManipulation(1,feedbackPopupObject.getSubject().length-2);
        feedbackPopup.selectionManipulation(2,feedbackPopupObject.getService().length-4);
        feedbackPopup.message.sendKeys(feedbackPopupObject.getMessage());
        //feedbackPopup.consent.click();
        Assertions.assertFalse(Boolean.parseBoolean(feedbackPopup.consent.getAttribute("aria-invalid")));
        feedbackPopup.submit.click();
        System.out.println(feedbackPopup.numberRequest());

    }

    @Test
    public void testFeedbackPopupNoValid()  {
        //Конечный результат теста не допонял, из чего именно выводить ссобщения: из браузера или из jUnit.
        mainpage.itemsMenuFooter.stream().filter(x-> x.getText().equals("Обратная связь")).forEach(x->x.click());
        FeedbackPopup feedbackPopup = mainpage.displayPopup();
        feedbackPopup.fullName.sendKeys(feedbackPopupObject.getFullName());
        feedbackPopup.email.sendKeys(feedbackPopupObject.getEmail());
        feedbackPopup.phone.sendKeys(feedbackPopupObject.getPhoneNumber());
        feedbackPopup.selectionManipulation(0,feedbackPopupObject.getMethodFeedback()[1]);
        feedbackPopup.selectionManipulation(1,feedbackPopupObject.getSubject().length-2);
        feedbackPopup.selectionManipulation(2,feedbackPopupObject.getService().length-4);
        feedbackPopup.message.sendKeys(feedbackPopupObject.getMessage());
        feedbackPopup.consent.click();
        Assertions.assertFalse(feedbackPopup.submit.isEnabled());

    }
    @Test
    public void testFeedbackPopupValidationFullName()
    {
        mainpage.itemsMenuFooter.stream().filter(x-> x.getText().equals("Обратная связь")).forEach(x->x.click());
        FeedbackPopup feedbackPopup = mainpage.displayPopup();
        feedbackPopup.fullName.sendKeys("!*#&^$&*!^@$&*&$^!*");
        Assertions.assertTrue(feedbackPopup.fullName.findElement(By.xpath("..//div")).getText().equals("введите на русском языке"));
        feedbackPopup.fullName.clear();
        feedbackPopup.fullName.sendKeys("!*#&^213245*&$^!*");
        Assertions.assertTrue(feedbackPopup.fullName.findElement(By.xpath("..//div")).getText().equals("введите на русском языке"));
        feedbackPopup.fullName.clear();
        feedbackPopup.fullName.sendKeys("Дмитрий' or 'x' =1");
        Assertions.assertTrue(feedbackPopup.fullName.findElement(By.xpath("..//div")).getText().equals("введите на русском языке"));
        feedbackPopup.fullName.clear();
        feedbackPopup.fullName.sendKeys("Дмитрий Сергеевич Курочкин");
        Assertions.assertThrows(NoSuchElementException.class,() ->{feedbackPopup.fullName.findElement(By.xpath("..//div"));});

    }

    @Test
    public void testFeedbackPopupValidationSubmit()
    {
        mainpage.itemsMenuFooter.stream().filter(x-> x.getText().equals("Обратная связь")).forEach(x->x.click());
        FeedbackPopup feedbackPopup = mainpage.displayPopup();
        feedbackPopup.fullName.sendKeys("29384725023950297510219320");
        feedbackPopup.email.sendKeys(feedbackPopupObject.getEmail());
        feedbackPopup.phone.sendKeys(feedbackPopupObject.getPhoneNumber());
        feedbackPopup.selectionManipulation(0,feedbackPopupObject.getMethodFeedback()[1]);
        feedbackPopup.selectionManipulation(1,feedbackPopupObject.getSubject().length-2);
        feedbackPopup.selectionManipulation(2,feedbackPopupObject.getService().length-4);
        feedbackPopup.message.sendKeys(feedbackPopupObject.getMessage());

        Assertions.assertFalse(feedbackPopup.submit.isEnabled());
    }



    @AfterEach
    public void destroyedObjectPage()
    {
        driver.close();
        driver.quit();
    }

}
