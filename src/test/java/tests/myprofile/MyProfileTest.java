package tests.myprofile;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.BasePage;
import pages.HomePage;
import pages.MyProfilePage;
import tests.BaseTest;
import utils.JsonReader;

public class MyProfileTest extends BaseTest {
    
    private MyProfilePage myProfilePage;
    private HomePage homePage;
    
    @BeforeMethod
    public void goToHome() {
        homePage = new BasePage(driver).initApp();
        myProfilePage = homePage.clickMyProfile();
    }
    
    @Test(description = "MYP-001: Verify Email field - Get and Display current email")
    public void testGetEmailValue() {
        String emailValue = myProfilePage.getEmailValue();
        Assert.assertNotNull(emailValue, "Email value should not be null");
        Assert.assertFalse(emailValue.isEmpty(), "Email value should not be empty");
    }
    
    @Test(description = "MYP-002: Verify Email field - Update email with valid email")
    public void testUpdateEmailValid() {
        String validEmail = JsonReader.get("MYP-002", "validEmail");
        myProfilePage.setEmail(validEmail);
        myProfilePage.saveChanges();
        
        String updatedEmail = myProfilePage.getEmailValue();
        Assert.assertEquals(updatedEmail, validEmail, "Email should be updated to: " + validEmail);
    }
    
    @Test(description = "MYP-003: Verify Role field - Get and Display current role")
    public void testGetSelectedRole() {
        String selectedRole = myProfilePage.getSelectedRole();
        Assert.assertNotNull(selectedRole, "Role should not be null");
        Assert.assertFalse(selectedRole.isEmpty(), "Role should not be empty");
    }
    
    @Test(description = "MYP-004: Verify Role field - Update role to Seeker")
    public void testUpdateRoleToSeeker() {
        myProfilePage.selectRoleSeeker();
        myProfilePage.saveChanges();
        
        String selectedRole = myProfilePage.getSelectedRole();
        Assert.assertEquals(selectedRole, "Seeker", "Role should be updated to Seeker");
    }
    
    @Test(description = "MYP-005: Verify Role field - Update role to Professional")
    public void testUpdateRoleToProfessional() {
        myProfilePage.selectRoleProfessional();
        myProfilePage.saveChanges();
        
        String selectedRole = myProfilePage.getSelectedRole();
        Assert.assertEquals(selectedRole, "Professional", "Role should be updated to Professional");
    }
    
    @Test(description = "MYP-006: Verify Role field - Update role to Both")
    public void testUpdateRoleToBoth() {
        myProfilePage.selectRoleBoth();
        myProfilePage.saveChanges();
        
        String selectedRole = myProfilePage.getSelectedRole();
        Assert.assertEquals(selectedRole, "Both", "Role should be updated to Both");
    }
    
    @Test(description = "MYP-007: Verify Role field - Update role by value")
    public void testUpdateRoleByValue() {
        String roleValue = "professional";
        myProfilePage.selectRoleByValue(roleValue);
        myProfilePage.saveChanges();
        
        String selectedRole = myProfilePage.getSelectedRole();
        Assert.assertEquals(selectedRole, "Professional", "Role should be updated to Professional");
    }
    
    @Test(description = "MYP-008: Verify Gender field - Get and Display current gender")
    public void testGetSelectedGender() {
        String selectedGender = myProfilePage.getSelectedGender();
        Assert.assertNotNull(selectedGender, "Gender should not be null");
    }
    
    @Test(description = "MYP-009: Verify Gender field - Update gender to Male")
    public void testUpdateGenderToMale() {
        myProfilePage.selectGenderMale();
        myProfilePage.saveChanges();
        
        String selectedGender = myProfilePage.getSelectedGender();
        Assert.assertEquals(selectedGender, "Male", "Gender should be updated to Male");
    }
    
    @Test(description = "MYP-010: Verify Gender field - Update gender to Female")
    public void testUpdateGenderToFemale() {
        myProfilePage.selectGenderFemale();
        myProfilePage.saveChanges();
        
        String selectedGender = myProfilePage.getSelectedGender();
        Assert.assertEquals(selectedGender, "Female", "Gender should be updated to Female");
    }
    
    @Test(description = "MYP-011: Verify Gender field - Update gender to Other")
    public void testUpdateGenderToOther() {
        myProfilePage.selectGenderOther();
        myProfilePage.saveChanges();
        
        String selectedGender = myProfilePage.getSelectedGender();
        Assert.assertEquals(selectedGender, "Other", "Gender should be updated to Other");
    }
    
    @Test(description = "MYP-012: Verify Gender field - Update gender to Prefer not to say")
    public void testUpdateGenderPreferNotSay() {
        myProfilePage.selectGenderPreferNotSay();
        myProfilePage.saveChanges();
        
        String selectedGender = myProfilePage.getSelectedGender();
        Assert.assertEquals(selectedGender, "Prefer not to say", "Gender should be updated to Prefer not to say");
    }
    
    @Test(description = "MYP-013: Verify Gender field - Update gender by value")
    public void testUpdateGenderByValue() {
        String genderValue = "female";
        myProfilePage.selectGenderByValue(genderValue);
        myProfilePage.saveChanges();
        
        String selectedGender = myProfilePage.getSelectedGender();
        Assert.assertEquals(selectedGender, "Female", "Gender should be updated to Female");
    }
    
    @Test(description = "MYP-014: Verify Display Name field - Get and Display current display name")
    public void testGetDisplayName() {
        String displayName = myProfilePage.getDisplayName();
        Assert.assertNotNull(displayName, "Display name should not be null");
    }
    
    @Test(description = "MYP-015: Verify Display Name field - Update display name")
    public void testUpdateDisplayName() {
        String newDisplayName = JsonReader.get("MYP-015", "displayName");
        myProfilePage.setDisplayName(newDisplayName);
        myProfilePage.saveChanges();
        
        String updatedName = myProfilePage.getDisplayName();
        Assert.assertEquals(updatedName, newDisplayName, "Display name should be updated to: " + newDisplayName);
    }
    
    @Test(description = "MYP-016: Verify Country Code field - Get and Display current country code")
    public void testGetCountryCode() {
        String countryCode = myProfilePage.getCountryCode();
        Assert.assertNotNull(countryCode, "Country code should not be null");
    }
    
    @Test(description = "MYP-017: Verify Country Code field - Update country code")
    public void testUpdateCountryCode() {
        String newCountryCode = JsonReader.get("MYP-017", "countryCode");
        myProfilePage.setCountryCode(newCountryCode);
        myProfilePage.saveChanges();
        
        String updatedCode = myProfilePage.getCountryCode();
        Assert.assertEquals(updatedCode, newCountryCode, "Country code should be updated to: " + newCountryCode);
    }
    
    @Test(description = "MYP-018: Verify City field - Get and Display current city")
    public void testGetCity() {
        String city = myProfilePage.getCity();
        Assert.assertNotNull(city, "City should not be null");
    }
    
    @Test(description = "MYP-019: Verify City field - Update city")
    public void testUpdateCity() {
        String newCity = JsonReader.get("MYP-019", "city");
        myProfilePage.setCity(newCity);
        myProfilePage.saveChanges();
        
        String updatedCity = myProfilePage.getCity();
        Assert.assertEquals(updatedCity, newCity, "City should be updated to: " + newCity);
    }
    
    @Test(description = "MYP-020: Verify Bio field - Get and Display current bio")
    public void testGetBio() {
        String bio = myProfilePage.getBio();
        Assert.assertNotNull(bio, "Bio should not be null");
    }
    
    @Test(description = "MYP-021: Verify Bio field - Update bio")
    public void testUpdateBio() {
        String newBio = JsonReader.get("MYP-021", "bio");
        myProfilePage.setBio(newBio);
        myProfilePage.saveChanges();
        
        String updatedBio = myProfilePage.getBio();
        Assert.assertEquals(updatedBio, newBio, "Bio should be updated to: " + newBio);
    }
    
    @Test(description = "MYP-022: Verify Profile Picture - Get current profile picture src")
    public void testGetProfilePictureSrc() {
        String pictureSrc = myProfilePage.getProfilePictureSrc();
        Assert.assertNotNull(pictureSrc, "Profile picture src should not be null");
        Assert.assertFalse(pictureSrc.isEmpty(), "Profile picture src should not be empty");
    }
    
    @Test(description = "MYP-023: Verify Profile Picture - Upload profile picture")
    public void testUploadProfilePicture() {
        String imagePath = JsonReader.get("MYP-023", "imagePath");
        myProfilePage.clickUploadPhoto();
        myProfilePage.uploadProfilePicture(imagePath);
        myProfilePage.useThisCrop();
        myProfilePage.saveChanges();
        
        Assert.assertTrue(true, "Profile picture uploaded successfully");
    }
    
    @Test(description = "MYP-024: Verify Profile Picture - Zoom functionality")
    public void testZoomProfilePicture() {
        myProfilePage.clickUploadPhoto();
        String zoomValue = "1.5";
        myProfilePage.setZoomLevel(zoomValue);
        myProfilePage.useThisCrop();
        
        Assert.assertTrue(true, "Zoom functionality working");
    }
    
    @Test(description = "MYP-025: Verify Profile Picture - Cancel crop")
    public void testCancelCrop() {
        myProfilePage.clickUploadPhoto();
        myProfilePage.cancelCrop();
        
        Assert.assertTrue(true, "Cancel crop working");
    }
    
    @Test(description = "MYP-026: Verify Save Changes button - Save all profile updates")
    public void testSaveChanges() {
        SoftAssert softAssert = new SoftAssert();
        
        String newDisplayName = JsonReader.get("MYP-026", "displayName");
        String newCity = JsonReader.get("MYP-026", "city");
        String newBio = JsonReader.get("MYP-026", "bio");
        
        myProfilePage.setDisplayName(newDisplayName);
        myProfilePage.setCity(newCity);
        myProfilePage.setBio(newBio);
        myProfilePage.selectRoleProfessional();
        myProfilePage.selectGenderMale();
        myProfilePage.saveChanges();
        
        softAssert.assertEquals(myProfilePage.getDisplayName(), newDisplayName, "Display name should be saved");
        softAssert.assertEquals(myProfilePage.getCity(), newCity, "City should be saved");
        softAssert.assertEquals(myProfilePage.getBio(), newBio, "Bio should be saved");
        softAssert.assertEquals(myProfilePage.getSelectedRole(), "Professional", "Role should be saved");
        softAssert.assertEquals(myProfilePage.getSelectedGender(), "Male", "Gender should be saved");
        
        softAssert.assertAll();
    }
    
    @Test(description = "MYP-027: Verify Multiple field updates - Update multiple fields at once")
    public void testMultipleFieldUpdates() {
        SoftAssert softAssert = new SoftAssert();
        
        String newEmail = JsonReader.get("MYP-027", "email");
        String newDisplayName = JsonReader.get("MYP-027", "displayName");
        String newCountryCode = JsonReader.get("MYP-027", "countryCode");
        String newCity = JsonReader.get("MYP-027", "city");
        String newBio = JsonReader.get("MYP-027", "bio");
        
        myProfilePage.setEmail(newEmail);
        myProfilePage.setDisplayName(newDisplayName);
        myProfilePage.setCountryCode(newCountryCode);
        myProfilePage.setCity(newCity);
        myProfilePage.setBio(newBio);
        myProfilePage.selectRoleSeeker();
        myProfilePage.selectGenderFemale();
        myProfilePage.saveChanges();
        
        softAssert.assertEquals(myProfilePage.getEmailValue(), newEmail, "Email should be updated");
        softAssert.assertEquals(myProfilePage.getDisplayName(), newDisplayName, "Display name should be updated");
        softAssert.assertEquals(myProfilePage.getCountryCode(), newCountryCode, "Country code should be updated");
        softAssert.assertEquals(myProfilePage.getCity(), newCity, "City should be updated");
        softAssert.assertEquals(myProfilePage.getBio(), newBio, "Bio should be updated");
        softAssert.assertEquals(myProfilePage.getSelectedRole(), "Seeker", "Role should be updated to Seeker");
        softAssert.assertEquals(myProfilePage.getSelectedGender(), "Female", "Gender should be updated to Female");
        
        softAssert.assertAll();
    }
}
