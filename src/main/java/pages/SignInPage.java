package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage extends BasePage {

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    private static final By LOGIN_EMAIL = By.xpath("//input[@id='email']");
    private static final By LOGIN_PASSWORD= By.xpath("//input[@id='password']");
    private static final By LOGIN_SIGNIN_BUTTON= By.xpath("//button[@type='submit']");
    private static final By LOGIN_CREATE_ACCOUNT= By.xpath("//a[text()='Create account']");




    public void typeEmail(String text){
        driver.findElement(LOGIN_EMAIL).sendKeys(text);
    }
    public void typePassword(String text){
        driver.findElement(LOGIN_PASSWORD).sendKeys(text);
    }


}
