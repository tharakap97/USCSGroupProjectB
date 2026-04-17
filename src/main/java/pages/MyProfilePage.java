package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class MyProfilePage extends BasePage{

    public MyProfilePage(WebDriver driver) {
        super(driver);
    }


    //navigate to my profile page
    private static final By MY_PROFILE_BUTTON= By.xpath("//a[contains(text(),'My Profile')]");

    //my profile ui
    private static final By EMAIL_TEXT_FIELD= By.xpath("//label[text()='Email']/parent::div/child::input");

    //role section
    private static final By ROLE_SELECT= By.xpath("//label[text()='Role']/following-sibling::select");
    private static final By ROLE_SEEKER_OPTION= By.xpath("//select[@class='select']/option[@value='seeker']");
    private static final By ROLE_PROFESSIONAL_OPTION= By.xpath("//select[@class='select']/option[@value='professional']");
    private static final By ROLE_BOTH_OPTION= By.xpath("//select[@class='select']/option[@value='both']");

    private static final By DISPLAY_NAME_FIELD= By.xpath("//label[text()='Display name']/parent::div/child::input");

    //profile picture section
    private static final By PROFILE_PICTURE_IMG= By.xpath("//img[@alt='Profile']");
    private static final By PROFILE_PICTURE_FILE_INPUT= By.xpath("//input[@type='file' and @accept='image/png,image/jpeg,image/webp']");
    private static final By ZOOM_RANGE_INPUT= By.xpath("//input[@type='range' and @min='1' and @max='3']");
    private static final By USE_THIS_CROP_BUTTON= By.xpath("//button[@type='button' and @class='btn primary' and text()='Use this crop']");
    private static final By CANCEL_BUTTON= By.xpath("//button[@type='button' and @class='btn ghost' and text()='Cancel']");

    private static final By UPLOAD_PHOTO_BUTTON= By.xpath("//button[@type='button' and @class='btn ghost' and text()='Upload photo']");

    //gender section
    private static final By GENDER_SELECT= By.xpath("//label[text()='Gender']/following-sibling::select");
    private static final By GENDER_PREFER_NOT_SAY_OPTION= By.xpath("//select[@class='select']/option[@value='prefer_not_say']");
    private static final By GENDER_MALE_OPTION= By.xpath("//select[@class='select']/option[@value='male']");
    private static final By GENDER_FEMALE_OPTION= By.xpath("//select[@class='select']/option[@value='female']");
    private static final By GENDER_OTHER_OPTION= By.xpath("//select[@class='select']/option[@value='other']");


    private static final By COUNTRY_CODE_FIELD= By.xpath("//label[text()='Country code']/parent::div/child::input");

    private static final By CITY_FIELD= By.xpath("//label[text()='City']/parent::div/child::input");

    private static final By BIO_FIELD= By.xpath("//label[text()='Bio']/parent::div/child::textarea");

    private static final By SAVE_CHANGES_BUTTON= By.xpath("//button[@class='btn primary' and @type='submit' and text()='Save Changes']");


    //navigate to my profile page by clicking button
    public void clickMyProfile() {
        click(MY_PROFILE_BUTTON);
    }

    //get email value from field
    public String getEmailValue() {
        return getAttribute(EMAIL_TEXT_FIELD, "value");
    }

    //enter email in email field
    public void enterEmail(String email) {
        type(EMAIL_TEXT_FIELD, email);
    }


    //clear email field
    public void clearEmail() {
        clear(EMAIL_TEXT_FIELD);
    }

    //set email (clear and enter new email)
    public void setEmail(String email) {
        clearEmail();
        enterEmail(email);
    }
    
    //get currently selected role
    public String getSelectedRole() {
        return getSelectedOptionText(ROLE_SELECT);
    }
    
    //update role to seeker
    public void selectRoleSeeker() {
        selectDropdownOption(ROLE_SEEKER_OPTION, "Seeker");
    }
    
    //update role to professional
    public void selectRoleProfessional() {
        selectDropdownOption(ROLE_PROFESSIONAL_OPTION, "Professional");
    }
    
    //update role to both
    public void selectRoleBoth() {
        selectDropdownOption(ROLE_BOTH_OPTION, "Both");
    }
    
    //update role by value
    public void selectRoleByValue(String roleValue) {
        selectDropdownByValue(ROLE_SELECT, roleValue);
    }

    //get currently selected gender
    public String getSelectedGender() {
        return getSelectedOptionText(GENDER_SELECT);
    }

    //update gender to prefer not to say
    public void selectGenderPreferNotSay() {
        selectDropdownOption(GENDER_PREFER_NOT_SAY_OPTION, "Prefer not to say");
    }

    //update gender to male
    public void selectGenderMale() {
        selectDropdownOption(GENDER_MALE_OPTION, "Male");
    }

    //update gender to female
    public void selectGenderFemale() {
        selectDropdownOption(GENDER_FEMALE_OPTION, "Female");
    }

    //update gender to other
    public void selectGenderOther() {
        selectDropdownOption(GENDER_OTHER_OPTION, "Other");
    }

    //update gender by value
    public void selectGenderByValue(String genderValue) {
        selectDropdownByValue(GENDER_SELECT, genderValue);
    }

    //get display name value
    public String getDisplayName() {
        return getAttribute(DISPLAY_NAME_FIELD, "value");
    }

    //enter display name
    public void enterDisplayName(String name) {
        type(DISPLAY_NAME_FIELD, name);
    }

    //clear display name
    public void clearDisplayName() {
        clear(DISPLAY_NAME_FIELD);
    }

    //set display name (clear and enter new name)
    public void setDisplayName(String name) {
        clearDisplayName();
        enterDisplayName(name);
    }

    //get country code value
    public String getCountryCode() {
        return getAttribute(COUNTRY_CODE_FIELD, "value");
    }

    //enter country code
    public void enterCountryCode(String code) {
        type(COUNTRY_CODE_FIELD, code);
    }

    //clear country code
    public void clearCountryCode() {
        clear(COUNTRY_CODE_FIELD);
    }

    //set country code (clear and enter new code)
    public void setCountryCode(String code) {
        clearCountryCode();
        enterCountryCode(code);
    }

    //get city value
    public String getCity() {
        return getAttribute(CITY_FIELD, "value");
    }

    //enter city
    public void enterCity(String city) {
        type(CITY_FIELD, city);
    }

    //clear city
    public void clearCity() {
        clear(CITY_FIELD);
    }

    //set city (clear and enter new city)
    public void setCity(String city) {
        clearCity();
        enterCity(city);
    }

    //get bio value
    public String getBio() {
        return getAttribute(BIO_FIELD, "value");
    }

    //enter bio
    public void enterBio(String bio) {
        type(BIO_FIELD, bio);
    }

    //clear bio
    public void clearBio() {
        clear(BIO_FIELD);
    }

    //set bio (clear and enter new bio)
    public void setBio(String bio) {
        clearBio();
        enterBio(bio);
    }

    //click save changes button
    public void saveChanges() {
        click(SAVE_CHANGES_BUTTON);
    }

    //get profile picture src
    public String getProfilePictureSrc() {
        return getAttribute(PROFILE_PICTURE_IMG, "src");
    }

    //upload profile picture
    public void uploadProfilePicture(String filePath) {
        type(PROFILE_PICTURE_FILE_INPUT, filePath);
    }

    //set zoom level
    public void setZoomLevel(String value) {
        type(ZOOM_RANGE_INPUT, value);
    }

    //click use this crop
    public void useThisCrop() {
        click(USE_THIS_CROP_BUTTON);
    }

    //click cancel
    public void cancelCrop() {
        click(CANCEL_BUTTON);
    }

    //click upload photo
    public void clickUploadPhoto() {
        click(UPLOAD_PHOTO_BUTTON);
    }


}
