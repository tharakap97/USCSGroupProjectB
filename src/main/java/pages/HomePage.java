package pages;

import org.apache.commons.math3.ode.nonstiff.HighamHall54FieldIntegrator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class HomePage extends BasePage{
    public static final By JOIN_BTN = By.xpath("//a[text()='Join']");
    private static final By HOME_SIGNIN_BUTTON= By.xpath("//a[text()='Sign in']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public JoinPage clickJoin(){
        locatorWaiting(JOIN_BTN);
        click(JOIN_BTN);
        return PageFactory.initElements(driver, JoinPage.class);
    }

    public  SignInPage selectSignIn(){
        locatorWaiting(HOME_SIGNIN_BUTTON);
        click(HOME_SIGNIN_BUTTON);
        return PageFactory.initElements(driver, SignInPage.class);
    }

}
