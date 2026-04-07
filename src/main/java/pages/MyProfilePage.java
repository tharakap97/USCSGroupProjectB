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

    //navigate to my profile page by clicking button
    public void navigateToMyProfile() {
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


}
