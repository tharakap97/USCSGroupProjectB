package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class HomePage extends BasePage{
    public static final By JOIN_BTN = By.xpath("//a[text()='Join']");
    public static final By CREATE_ACC_BTN_IN_HOME = By.xpath("//div[text()='Join the Network']/following-sibling::a[text()='Create account']");
    public static final By CREATE_ACC_BTN_IN_SIGN_IN = By.xpath("//div[@class='actions']/child::a[text()='Create account']");
    public static final By MY_PROFILE_BUTTON = By.xpath("//a[contains(text(),'My Profile')]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public JoinPage clickJoin(){
        locatorWaiting(JOIN_BTN);
        click(JOIN_BTN);
        return PageFactory.initElements(driver, JoinPage.class);
    }
    public JoinPage clickCreateAcc(){
        locatorWaiting(CREATE_ACC_BTN_IN_HOME);
        click(CREATE_ACC_BTN_IN_HOME);
        return PageFactory.initElements(driver, JoinPage.class);
    }

    public MyProfilePage clickMyProfile(){
        locatorWaiting(MY_PROFILE_BUTTON);
        click(MY_PROFILE_BUTTON);
        return PageFactory.initElements(driver, MyProfilePage.class);
    }


}
