package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JoinPage extends BasePage {

    public JoinPage(WebDriver driver) {
        super(driver);
    }

    public static final By JOIN_BTN = By.xpath("//a[text()='Join']");
    //Common
    public static final By EMAIL_TEXT_FIELD = By.xpath("//label[text()='Email']/parent::div/child::input");
    public static final By PASSWORD_TEXT_FIELD = By.xpath("//label[text()='Password']/parent::div/child::input");
    public static final By DISPLAY_NAME_TEXT_FIELD = By.xpath("//label[text()='Display name']/parent::div/child::input");
    public static final By PROFILE_PIC_TEXT_FIELD = By.xpath("//label[text()='Profile picture (optional)']/parent::div/descendant::input");
    public static final By ADJUST_PRO_PIC_POP_UP = By.xpath("//h3[text()='Adjust profile picture']/parent::div[@class='modal crop-modal']");
    public static final By ZOOM_RESIZER = By.xpath("//label[text()='Zoom']/parent::div/child::input[@type='range']");
    public static final By USE_THIS_CROP_BTN = By.xpath("//div[@class='cluster']/child::button[text()='Use this crop']");
    public static final By CANCEL_BTN = By.xpath("//div[@class='cluster']/child::button[text()='Cancel']");
    public static final By REGISTERING_AS_TEXT_FIELD = By.xpath("//label[text()='I am registering as']/parent::div/child::div[@class='cluster']");
    public static final By CITY_TEXT_FIELD = By.xpath("//label[text()='City']/parent::div/child::input");
    public static final By COUNTRY_TEXT_FIELD = By.xpath("//label[text()='Country']/parent::div/child::input");
    public static final By BIO_TEXT_FIELD = By.xpath("//label[text()='Bio']/parent::div/child::textarea");
    public static final By CREATE_ACC_BTN = By.xpath("//button[@type='submit' and text()='Create account']");

    //Seeker
    public static final By IM_PROF_BTN = By.xpath("//button[@type='button' and contains(normalize-space(), \"I'm a professional\")]");

    //Professional
    public static final By CATEGORY_DROPDOWN = By.xpath("//label[text()='Category']/parent::div/child::select");
    public static final By SUB_CATEGORY_DROPDOWN = By.xpath("//label[text()='Sub-category']/parent::div/child::select");
    public static final By SUB_CATEGORY_REMOVE_BTN = By.xpath("//button[@type='button' and text()='Remove']");
    public static final By SUB_CATEGORY_CLEAR_ALL_BTN = By.xpath("//button[@type='button' and text()='Clear all']");
    public static final By EXP_YR_SELECTOR = By.xpath("//label[text()='Experience (years)']/parent::div/child::input");
    public static final By HOURLY_RATE_SELECTOR = By.xpath("//label[text()='Hourly rate']/parent::div/child::input");
    public static final By PRIMARY_WORK_AREA_TEXT_FIELD = By.xpath("//label[text()='Primary work area']/parent::div/child::input");
    public static final By PREF_SURROUND_AREA_TEXT_FIELD = By.xpath("//label[text()='Preferred surrounding areas']/parent::div/child::input");
    public static final By RATE_CURRENCY_DROPDOWN = By.xpath("//label[text()='Rate currency']/parent::div/child::select");
    public static final By SERVICE_RADIUS_SELECTOR = By.xpath("//label[text()='Service radius (km)']/parent::div/child::input");
    public static final By SERVICE_LOCATION_PIN_SELECTOR = By.xpath("//label[text()='Service location pin']/parent::div/child::div");
    public static final By USE_MY_LOCATION_BTN = By.xpath("//div[@class='cluster']/child::button[text()='Use my location']");
    public static final By CLEAR_PIN_BTN = By.xpath("//div[@class='cluster']/child::button[text()='Clear pin']");
    public static final By IM_SEEKER_BTN = By.xpath("//button[@type='button' and contains(normalize-space(), \"I'm a seeker\")]");

    //Verify your email
    public static final By DIGIT_CODE_TEXT_FIELD = By.xpath("//label[text()='6-digit code']/parent::div/child::input");
    public static final By VERIFY_CONFIRM_BTN = By.xpath("//div[@class='cluster']/child::button[text()='Verify and continue']");
    public static final By RESEND_CODE_BTN = By.xpath("//div[@class='cluster']/child::button[text()='Resend code']");
    public static final By BACK_BTN = By.xpath("//div[@class='cluster']/child::button[text()='Back']");


    public void typeText(By locator, String text){
        type(locator, text);
    }
    public void clickJoin(){
        locatorWaiting(JOIN_BTN);
        click(JOIN_BTN);
    }
    public void clickCreateAcc() throws InterruptedException {
        locatorWaiting(CREATE_ACC_BTN);
        click(CREATE_ACC_BTN);
    }

    public void fillHourlyRate(String rate){
        type(HOURLY_RATE_SELECTOR,rate);
    }
}
