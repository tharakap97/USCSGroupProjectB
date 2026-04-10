package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JoinPage extends BasePage {
    public JoinPage(WebDriver driver) {
        super(driver);
    }

    public static final By CREATE_ACC_PAGE = By.xpath("//h2[text()='Create your account']/parent::div");
    public static final By EMAIL_TEXT_FIELD = By.xpath("//label[text()='Email']/parent::div/child::input");
    public static final By PASSWORD_TEXT_FIELD = By.xpath("//label[text()='Password']/parent::div/child::input");
    public static final By DISPLAY_NAME_TEXT_FIELD = By.xpath("//label[text()='Display name']/parent::div/child::input");
    public static final By PROFILE_PIC_TEXT_FIELD = By.xpath("//label[text()='Profile picture (optional)']/parent::div/descendant::input");
    public static final By ADJUST_PRO_PIC_POP_UP = By.xpath("//h3[text()='Adjust profile picture']/parent::div[@class='modal crop-modal']");
    public static final By ZOOM_RESIZER = By.xpath("//label[text()='Zoom']/parent::div/child::input[@type='range']");
    public static final By USE_THIS_CROP_BTN = By.xpath("//div[@class='cluster']/child::button[text()='Use this crop']");
    public static final By CANCEL_BTN = By.xpath("//div[@class='cluster']/child::button[text()='Cancel']");
    public static final By IMG_ERR = By.xpath("//h3[text()='Adjust profile picture']/following-sibling::div[@class='muted' and @style='color: crimson;']");
    public static final By REG_AS_RADIO_SEEKER = By.xpath("//label[text()='I am registering as']/following-sibling::div/descendant::input[@value='seeker']");
    public static final By REG_AS_RADIO_PROF= By.xpath("//label[text()='I am registering as']/following-sibling::div/descendant::input[@value='professional']");
    public static final By REG_AS_RADIO_BOTH = By.xpath("//label[text()='I am registering as']/following-sibling::div/descendant::input[@value='both']");
    public static final By CITY_TEXT_FIELD = By.xpath("//label[text()='City']/parent::div/child::input");
    public static final By COUNTRY_TEXT_FIELD = By.xpath("//label[text()='Country']/parent::div/child::input");
    public static final By BIO_TEXT_FIELD = By.xpath("//label[text()='Bio']/parent::div/child::textarea");
    public static final By CREATE_ACC_BTN = By.xpath("//button[@type='submit' and text()='Create account']");

    public static final By IM_PROF_BTN = By.xpath("//button[@type='button' and contains(normalize-space(), \"I'm a professional\")]");

    public static final By SPECIALITIES = By.xpath("//label[text()='Specialties']/parent::div");
    public static final By CATEGORY_DROPDOWN = By.xpath("//label[text()='Category']/parent::div/child::select");
    public static final By SUB_CATEGORY_DROPDOWN = By.xpath("//label[text()='Sub-category']/parent::div/child::select");
    public static final By SUB_CATEGORY_DD_VALUES = By.xpath("//label[text()='Sub-category']/parent::div/child::select/child::option");
    public static final By SUB_CATEGORY_REMOVE_BTN = By.xpath("//button[@type='button' and text()='Remove']");
    public static final By SUB_CATEGORY_CLEAR_ALL_BTN = By.xpath("//button[@type='button' and text()='Clear all']");
    public static final By EXP_YR_SELECTOR = By.xpath("//label[text()='Experience (years)']/parent::div/child::input");
    public static final By HOURLY_RATE_SELECTOR = By.xpath("//label[text()='Hourly rate']/parent::div/child::input");
    public static final By PRIMARY_WORK_AREA_TEXT_FIELD = By
            .xpath("//label[text()='Primary work area']/parent::div/child::input");
    public static final By PREF_SURROUND_AREA_TEXT_FIELD = By
            .xpath("//label[text()='Preferred surrounding areas']/parent::div/child::input");
    public static final By RATE_CURRENCY_DROPDOWN = By
            .xpath("//label[text()='Rate currency']/parent::div/child::select");
    public static final By SERVICE_RADIUS_SELECTOR = By
            .xpath("//label[text()='Service radius (km)']/parent::div/child::input");
    public static final By SERVICE_LOCATION_PIN_SELECTOR = By
            .xpath("//label[text()='Service location pin']/parent::div/child::div");
    public static final By MAP_PIN_ICON =  By.className("leaflet-marker-icon");
    public static final By USE_MY_LOCATION_BTN = By
            .xpath("//div[@class='cluster']/child::button[text()='Use my location']");
    public static final By CLEAR_PIN_BTN = By.xpath("//div[@class='cluster']/child::button[text()='Clear pin']");
    public static final By IM_SEEKER_BTN = By.xpath("//button[@type='button' and contains(normalize-space(), \"I'm a seeker\")]");

    // Verify your email
    public static final By VERIFY_EMAIL_POPUP = By.xpath("//h2[text()='Verify your email']/parent::div");
    public static final By DIGIT_CODE_TEXT_FIELD = By.xpath("//label[text()='6-digit code']/parent::div/child::input");
    public static final By VERIFY_CONFIRM_BTN = By
            .xpath("//div[@class='cluster']/child::button[text()='Verify and continue']");
    public static final By RESEND_CODE_BTN = By.xpath("//div[@class='cluster']/child::button[text()='Resend code']");
    public static final By BACK_BTN = By.xpath("//div[@class='cluster']/child::button[text()='Back']");

    public static final By ERROR_MSG = By.xpath("//div[@class='muted' and contains(@style, 'crimson')]");
    public static final By SPE_MSG = By.xpath("//label[text()='Specialties']/following-sibling::div/child::div[@class='muted']");
    public static final By SERVICE_LOC_MSG = By.xpath("//label[text()='Service location pin']/following-sibling::div[@class='muted']");

    public void fillProfessionalSpecificFields(String experience, String hourlyRate, String primaryWorkArea,
                                               String preferredSurroundingAreas, String rateCurrency) {
        type(EXP_YR_SELECTOR, experience);
        type(HOURLY_RATE_SELECTOR, hourlyRate);
        type(PRIMARY_WORK_AREA_TEXT_FIELD, primaryWorkArea);
        type(PREF_SURROUND_AREA_TEXT_FIELD, preferredSurroundingAreas);
        locatorWaiting(RATE_CURRENCY_DROPDOWN);
        Select currencySelect = new Select(driver.findElement(RATE_CURRENCY_DROPDOWN));
        currencySelect.selectByVisibleText(rateCurrency);
    }
    public void fillEmailFields(String email) {
        type(JoinPage.EMAIL_TEXT_FIELD, email);
    }
    public void fillPwFields(String password) {
        type(JoinPage.PASSWORD_TEXT_FIELD, password);
    }
    public void fillDisplayNameFields(String displayName){
        type(JoinPage.DISPLAY_NAME_TEXT_FIELD, displayName);
    }
    public void uploadProfilePicture(String fileName) {
        File file = new File("src/test/resources/testdata/" + fileName);
        String absolutePath = file.getAbsolutePath();
        driver.findElement(PROFILE_PIC_TEXT_FIELD).sendKeys(absolutePath);
    }
    public void adjustZoom(String value) {
        locatorWaiting(ZOOM_RESIZER);
        WebElement slider = driver.findElement(ZOOM_RESIZER);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'))", slider, value);
    }
    public String getZoomValue() {
        return driver.findElement(ZOOM_RESIZER).getAttribute("value");
    }
    public String getUploadedFileName() {
        WebElement fileInput = driver.findElement(PROFILE_PIC_TEXT_FIELD);
        String filePath = fileInput.getAttribute("value");
        if (filePath == null || filePath.isEmpty()) return "";
        int lastSlash = Math.max(filePath.lastIndexOf('/'), filePath.lastIndexOf('\\'));
        return lastSlash >= 0 ? filePath.substring(lastSlash + 1) : filePath;
    }
    public void fillCityFields(String city) {
        type(JoinPage.CITY_TEXT_FIELD, city);
    }
    public void fillCountryFields(String country) {
        type(JoinPage.COUNTRY_TEXT_FIELD, country);
    }
    public void fillBioFields(String bio){
        type(JoinPage.BIO_TEXT_FIELD, bio);
    }
    public void selectProfRadio() {
        click(REG_AS_RADIO_PROF);
    }
    public void selectBothRadio() {
        click(REG_AS_RADIO_BOTH);
    }
    public void selectSeekerRadio() {
        click(REG_AS_RADIO_SEEKER);
    }
    public void selectSeekerBtn() {
        click(IM_SEEKER_BTN);
    }
    public void selectProfBtn() {
        click(IM_PROF_BTN);
    }
    public void clickServiceLocationPin() {
        click(SERVICE_LOCATION_PIN_SELECTOR);
    }
    public void selectCategory(String categoryText) throws InterruptedException {
        locatorWaiting(CATEGORY_DROPDOWN);
        Select category = new Select(driver.findElement(CATEGORY_DROPDOWN));
        Thread.sleep(1000);
        category.selectByVisibleText(categoryText);
    }
    public void selectSubCategory(String subCategoryText) {
        locatorWaiting(SUB_CATEGORY_DROPDOWN);
        Select subCategory = new Select(driver.findElement(SUB_CATEGORY_DROPDOWN));
        subCategory.selectByVisibleText(subCategoryText);
    }
    public boolean isSubCatDisabled() {
        locatorWaiting(SUB_CATEGORY_DROPDOWN);
        WebElement subCatInput = driver.findElement(SUB_CATEGORY_DROPDOWN);

        String disabled = subCatInput.getAttribute("disabled");
        return (disabled != null && disabled.equals("true"));
    }
    public List<String> getSubCategoryOptions() {
        List<WebElement> options = driver.findElements(SUB_CATEGORY_DD_VALUES);
        List<String> textList = new ArrayList<>();

        for (WebElement option : options) {
            textList.add(option.getText());
        }
        return textList;
    }
    public String getFirstCatOption() {
        Select select = new Select(driver.findElement(CATEGORY_DROPDOWN));
        return select.getFirstSelectedOption().getText();
    }
    public String getFirstSubCatOption() {
        Select select = new Select(driver.findElement(SUB_CATEGORY_DROPDOWN));
        return select.getFirstSelectedOption().getText();
    }
    public void clickSubCategoryRemove() {
        click(SUB_CATEGORY_REMOVE_BTN);
    }
    public void clickSubCategoryClearAll() {
        click(SUB_CATEGORY_CLEAR_ALL_BTN);
    }
    public boolean isCountryFieldReadOnly() {
        locatorWaiting(COUNTRY_TEXT_FIELD);
        WebElement countryInput = driver.findElement(COUNTRY_TEXT_FIELD);

        String readOnly = countryInput.getAttribute("readonly");
        return (readOnly != null && readOnly.equals("true"));
    }
    public String getCountryValue() {
        return driver.findElement(COUNTRY_TEXT_FIELD).getAttribute("value");
    }
    public String getRateCurrencyValue() {
        locatorWaiting(RATE_CURRENCY_DROPDOWN);
        Select currencySelect = new Select(driver.findElement(RATE_CURRENCY_DROPDOWN));
        WebElement selectedOption = currencySelect.getFirstSelectedOption();
        return selectedOption.getText().trim();
    }
    public void fillServiceRadius(String serviceRadius) {
        clearField(SERVICE_RADIUS_SELECTOR);
        type(SERVICE_RADIUS_SELECTOR, serviceRadius);
    }
    public void selectServiceLocation() {
        click(USE_MY_LOCATION_BTN);
        locatorWaiting(MAP_PIN_ICON);
    }
    public void clearServiceLocationPin() {
        click(CLEAR_PIN_BTN);
    }
    public String getValidationMsg(By locator) {
        locatorWaiting(locator);
        return driver.findElement(locator).getText();
    }
    public String getFieldText(By locator) {
        locatorWaiting(locator);
        By labelLocator = By.xpath("./parent::div/child::label");
        try {
            return driver.findElement(locator).findElement(labelLocator).getText();
        } catch (Exception e) {
            String id = driver.findElement(locator).getAttribute("id");
            return driver.findElement(By.xpath("//label[@for='" + id + "']")).getText();
        }
    }
    public void clickCreateAccount() {
        click(CREATE_ACC_BTN);
    }
}
