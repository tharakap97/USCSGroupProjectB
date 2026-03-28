package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class HomePage extends BasePage{
    public static final By JOIN_BTN = By.xpath("//a[text()='Join']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public JoinPage clickJoin(){
        locatorWaiting(JOIN_BTN);
        click(JOIN_BTN);
        return PageFactory.initElements(driver, JoinPage.class);
    }
}
