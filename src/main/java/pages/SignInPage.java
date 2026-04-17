package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage extends BasePage {

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    public static final By LOGIN_EMAIL = By.xpath("//input[@id='email']");
    public static final By LOGIN_PASSWORD = By.xpath("//input[@id='password']");
    public static final By LOGIN_SIGNIN_BUTTON = By.xpath("//button[@type='submit']");
    public static final By LOGIN_CREATE_ACCOUNT = By.xpath("//a[text()='Create account']");

    public static final By LOGIN_REMEMBER_MY_CREDENTIALS =  By.xpath("//input[@type=\"checkbox\"]");
    public static final By LOGIN_SHOW_BUTTON = By.xpath("//button[text()='Show']");
    public static final By LOGIN_HIDE_BUTTON = By.xpath("//button[text()='Hide']");
    public static final By LOGOUT = By.xpath("//button[text()='Logout']");
    //Logout button is not on this page... Maybe we have to move it to dashboard page


    public void typeText(By locator, String text) {
        type(locator, text);
    }

    public void clickRememberMyCredentials(){
        locatorWaiting(LOGIN_REMEMBER_MY_CREDENTIALS);
        click(LOGIN_REMEMBER_MY_CREDENTIALS);
    }

    public void clickShowButton(){
        locatorWaiting(LOGIN_SHOW_BUTTON);
        click(LOGIN_SHOW_BUTTON);
    }
    public void clickHideButton() {
        locatorWaiting(LOGIN_HIDE_BUTTON);
        click(LOGIN_HIDE_BUTTON);
    }

    public void clickSignIn() {
        locatorWaiting(LOGIN_SIGNIN_BUTTON);
        click(LOGIN_SIGNIN_BUTTON);
    }

    public void clickCreateAcc() {
        locatorWaiting(LOGIN_CREATE_ACCOUNT);
        click(LOGIN_CREATE_ACCOUNT);
    }

    public void clickLogout() {
        locatorWaiting(LOGOUT);
        click(LOGOUT);
    } //If Logout xpath moved to DashboardPage, move this method there...


}
