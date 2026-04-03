package tests.join;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePage;
import pages.JoinPage;
import tests.BaseTest;
import utils.JsonReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.testng.asserts.SoftAssert;

public class JoinValidationTest extends BaseTest {
    private JoinPage joinPage;

    @BeforeMethod
    public void setupTest() {
        HomePage homePage = new BasePage(driver).initApp();
        joinPage = homePage.clickJoin();
    }

    @Test(description = "TCL-003 Verify Empty Mandatory Fields in Seeker Account")
    public void testEmptyMandatoryFieldsSeeker() {
        SoftAssert softAssert = new SoftAssert();
        joinPage.selectSeekerRadio();
        joinPage.clickCreateAccount();
        String actualText = joinPage.getValidationMsg(JoinPage.ERROR_MSG);

        String fieldNameEmail = joinPage.getFieldText(JoinPage.EMAIL_TEXT_FIELD);
        softAssert.assertTrue(actualText.contains(fieldNameEmail),
                "Error message does not mention: " + fieldNameEmail);

        String fieldNamePw = joinPage.getFieldText(JoinPage.PASSWORD_TEXT_FIELD);
        softAssert.assertTrue(actualText.contains(fieldNamePw),
                "Error message does not mention: " + fieldNamePw);

        String fieldNameDpName = joinPage.getFieldText(JoinPage.DISPLAY_NAME_TEXT_FIELD);
        softAssert.assertTrue(actualText.contains(fieldNameDpName),
                "Error message does not mention: " + fieldNameDpName);

        softAssert.assertAll();
    }

    @Test(description = "TCL-003 Verify Empty Mandatory Fields in Prof or Both Account")
    public void testEmptyMandatoryFieldsProf() {
        SoftAssert softAssert = new SoftAssert();
        joinPage.selectProfRadio();
        joinPage.clickCreateAccount();
        String actualText = joinPage.getValidationMsg(JoinPage.ERROR_MSG);

        String fieldNameEmail = joinPage.getFieldText(JoinPage.EMAIL_TEXT_FIELD);
        softAssert.assertTrue(actualText.contains(fieldNameEmail),
                "Error message does not mention: " + fieldNameEmail);

        String fieldNamePw = joinPage.getFieldText(JoinPage.PASSWORD_TEXT_FIELD);
        softAssert.assertTrue(actualText.contains(fieldNamePw),
                "Error message does not mention: " + fieldNamePw);

        String fieldNameDpName = joinPage.getFieldText(JoinPage.DISPLAY_NAME_TEXT_FIELD);
        softAssert.assertTrue(actualText.contains(fieldNameDpName),
                "Error message does not mention: " + fieldNameDpName);

        softAssert.assertTrue(actualText.contains("Please add at least one specialty."),
                "Error message does not mention: " + "Speciality");

        String fieldNameServicePinName = joinPage.getFieldText(JoinPage.SERVICE_LOCATION_PIN_SELECTOR);
        softAssert.assertTrue(actualText.contains(fieldNameServicePinName),
                "Error message does not mention: " + fieldNameServicePinName);

        softAssert.assertAll();
    }

    @Test(description = "TCL-004: Invalid Email Format")
    public void testInvalidEmailFormat() {
        List<String> invalidEmails = JsonReader.getList("TCL-004", "invalidEmails");
        String validPassword = JsonReader.get("TCL-004", "password");
        String validDisplayName = JsonReader.get("TCL-004", "displayName");
        String validCity = JsonReader.get("TCL-004", "city");
        String validCountry = JsonReader.get("TCL-004", "country");
        String validBio = JsonReader.get("TCL-004", "bio");

        for (String invalidEmail : invalidEmails) {
            driver.navigate().refresh();

            joinPage.type(JoinPage.EMAIL_TEXT_FIELD, invalidEmail);
            joinPage.type(JoinPage.PASSWORD_TEXT_FIELD, validPassword);
            joinPage.type(JoinPage.DISPLAY_NAME_TEXT_FIELD, validDisplayName);
            joinPage.type(JoinPage.CITY_TEXT_FIELD, validCity);
            joinPage.type(JoinPage.COUNTRY_TEXT_FIELD, validCountry);
            joinPage.type(JoinPage.BIO_TEXT_FIELD, validBio);

            joinPage.clickCreateAccount();

            String errorMsg = joinPage.getValidationMsg(JoinPage.ERROR_MSG);
            Assert.assertTrue(errorMsg.contains("Invalid email"), "Expected 'Invalid email' but found: " + errorMsg);
        }
    }

    @Test(description = "TCL-005: Existing Email Format")
    public void testExistingEmailFormat() {
        String existingEmail = JsonReader.get("TCL-005", "existingEmail");
        String validPassword = JsonReader.get("TCL-005", "password");
        String validDisplayName = JsonReader.get("TCL-005", "displayName");
        String validCity = JsonReader.get("TCL-005", "city");
        String validCountry = JsonReader.get("TCL-005", "country");
        String validBio = JsonReader.get("TCL-005", "bio");

        joinPage.type(JoinPage.EMAIL_TEXT_FIELD, existingEmail);
        joinPage.type(JoinPage.PASSWORD_TEXT_FIELD, validPassword);
        joinPage.type(JoinPage.DISPLAY_NAME_TEXT_FIELD, validDisplayName);
        joinPage.type(JoinPage.CITY_TEXT_FIELD, validCity);
        joinPage.type(JoinPage.COUNTRY_TEXT_FIELD, validCountry);
        joinPage.type(JoinPage.BIO_TEXT_FIELD, validBio);

        joinPage.clickCreateAccount();

        String errorMsg = joinPage.getValidationMsg(JoinPage.ERROR_MSG);
        Assert.assertTrue(errorMsg.contains("email already exists"), "Expected 'email already exists' but found: " + errorMsg);
    }

    @Test(description = "TCL-006: Invalid Password Format")
    public void testInvalidPasswordFormat() {
        List<String> invalidPasswords = JsonReader.getList("TCL-006", "invalidPasswords");
        String validEmail = JsonReader.get("TCL-006", "email");
        String validDisplayName = JsonReader.get("TCL-006", "displayName");
        String validCity = JsonReader.get("TCL-006", "city");
        String validCountry = JsonReader.get("TCL-006", "country");
        String validBio = JsonReader.get("TCL-006", "bio");

        for (String invalidPassword : invalidPasswords) {
            driver.navigate().refresh();

            joinPage.type(JoinPage.PASSWORD_TEXT_FIELD, invalidPassword);
            joinPage.type(JoinPage.EMAIL_TEXT_FIELD, validEmail);
            joinPage.type(JoinPage.DISPLAY_NAME_TEXT_FIELD, validDisplayName);
            joinPage.type(JoinPage.CITY_TEXT_FIELD, validCity);
            joinPage.type(JoinPage.COUNTRY_TEXT_FIELD, validCountry);
            joinPage.type(JoinPage.BIO_TEXT_FIELD, validBio);

            joinPage.clickCreateAccount();

            String errMsg = joinPage.getValidationMsg(JoinPage.ERROR_MSG);
            Assert.assertTrue(errMsg.contains("Password must be at least 8 characters") ,
                    "Expected custom password error, but got: " + errMsg);
        }
    }

    @Test(description = "TCL-009: Invalid Display Name Field")
    public void testInvalidDisplayName() {
        List<String> invalidDisplayNames = JsonReader.getList("TCL-009", "invalidDisplayNames");
        String validEmail = JsonReader.get("TCL-009", "email");
        String validPw = JsonReader.get("TCL-009", "password");
        String validCity = JsonReader.get("TCL-009", "city");
        String validCountry = JsonReader.get("TCL-009", "country");
        String validBio = JsonReader.get("TCL-009", "bio");

        for (String invalidDisplayName : invalidDisplayNames) {
            driver.navigate().refresh();

            joinPage.type(JoinPage.DISPLAY_NAME_TEXT_FIELD, invalidDisplayName);
            joinPage.type(JoinPage.EMAIL_TEXT_FIELD, validEmail);
            joinPage.type(JoinPage.PASSWORD_TEXT_FIELD, validPw);
            joinPage.type(JoinPage.CITY_TEXT_FIELD, validCity);
            joinPage.type(JoinPage.COUNTRY_TEXT_FIELD, validCountry);
            joinPage.type(JoinPage.BIO_TEXT_FIELD, validBio);

            joinPage.clickCreateAccount();

            String errMsg = joinPage.getValidationMsg(JoinPage.ERROR_MSG);
            Assert.assertTrue(errMsg.contains("Display Name must contain at least 2 character(s)"),
                    "Expected Display name error, but got: " + errMsg);
        }
    }

    @Test(description = "TCL-013: Invalid City Field")
    public void verifyCityTF() {
        String validEmail = JsonReader.get("TCL-013", "email");
        String validPw = JsonReader.get("TCL-013", "password");
        String validDisplayName = JsonReader.get("TCL-013", "displayName");
        String invalidCity = JsonReader.get("TCL-013", "invalidCity");
        String validCountry = JsonReader.get("TCL-013", "country");
        String validBio = JsonReader.get("TCL-013", "bio");

        joinPage.type(JoinPage.EMAIL_TEXT_FIELD, validEmail);
        joinPage.type(JoinPage.PASSWORD_TEXT_FIELD, validPw);
        joinPage.type(JoinPage.DISPLAY_NAME_TEXT_FIELD, validDisplayName);
        joinPage.type(JoinPage.CITY_TEXT_FIELD, invalidCity);
        joinPage.type(JoinPage.COUNTRY_TEXT_FIELD, validCountry);
        joinPage.type(JoinPage.BIO_TEXT_FIELD, validBio);

        joinPage.clickCreateAccount();

        Assert.assertFalse(joinPage.isElementDisplayed(JoinPage.VERIFY_EMAIL_POPUP), "Should not be on verify page");
        String errMsg = joinPage.getValidationMsg(JoinPage.ERROR_MSG);
        Assert.assertTrue(errMsg.contains("City should be valid text") ,
                "Expected City error, but got: " + errMsg);
    }

    @Test(description = "TCL-014: Verify Bio Field")
    public void verifyBioTF() {
        String validEmail = JsonReader.get("TCL-014", "email");
        String validPw = JsonReader.get("TCL-014", "password");
        String validDisplayName = JsonReader.get("TCL-014", "displayName");
        String validCity = JsonReader.get("TCL-014", "city");
        String validCountry = JsonReader.get("TCL-014", "country");
        String invalidBio = JsonReader.get("TCL-014", "invalidBio");

        joinPage.type(JoinPage.EMAIL_TEXT_FIELD, validEmail);
        joinPage.type(JoinPage.PASSWORD_TEXT_FIELD, validPw);
        joinPage.type(JoinPage.DISPLAY_NAME_TEXT_FIELD, validDisplayName);
        joinPage.type(JoinPage.CITY_TEXT_FIELD, validCity);
        joinPage.type(JoinPage.COUNTRY_TEXT_FIELD, validCountry);
        joinPage.type(JoinPage.BIO_TEXT_FIELD, invalidBio);

        joinPage.clickCreateAccount();

        Assert.assertTrue(joinPage.isElementDisplayed(JoinPage.VERIFY_EMAIL_POPUP), "Should be on verify page");
    }

    @Test(description = "TCL-016: Verify Country field is read-only and defaults to Sri Lanka")
    public void testCountryFieldReadOnly() {
        SoftAssert softAssert = new SoftAssert();

        String defaultCountry = joinPage.getCountryValue();
        softAssert.assertEquals(defaultCountry, "Sri Lanka", "Default country is not Sri Lanka!");

        boolean isLocked = joinPage.isCountryFieldReadOnly();
        softAssert.assertTrue(isLocked, "Country field is not Read Only");

        joinPage.type(JoinPage.COUNTRY_TEXT_FIELD, "USA");
        String afterType = joinPage.getCountryValue();
        softAssert.assertEquals(afterType, "Sri Lanka", "Country field value changed even though it should be locked.");

        softAssert.assertAll();
    }

    @Test(description = "TCL-017: Verify Seeker Account create correctly")
    public void verifySeekerAcc() {
        String validEmail = JsonReader.get("TCL-017", "email");
        String validPw = JsonReader.get("TCL-017", "password");
        String validDisplayName = JsonReader.get("TCL-017", "displayName");
        String validCity = JsonReader.get("TCL-017", "city");
        String validCountry = JsonReader.get("TCL-017", "country");
        String validBio = JsonReader.get("TCL-017", "bio");

        joinPage.type(JoinPage.EMAIL_TEXT_FIELD, validEmail);
        joinPage.type(JoinPage.PASSWORD_TEXT_FIELD, validPw);
        joinPage.type(JoinPage.DISPLAY_NAME_TEXT_FIELD, validDisplayName);
        joinPage.selectSeekerRadio();
        joinPage.type(JoinPage.CITY_TEXT_FIELD, validCity);
        joinPage.type(JoinPage.COUNTRY_TEXT_FIELD, validCountry);
        joinPage.type(JoinPage.BIO_TEXT_FIELD, validBio);
        joinPage.clickCreateAccount();
        Assert.assertTrue(joinPage.isElementDisplayed(JoinPage.VERIFY_EMAIL_POPUP), "Should be on verify page");
    }

    @Test(description = "TCL-018: Verify Specialties - Category dropdown validations")
    public void verifyCategory() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        List<String> categories = JsonReader.getList("TCL-018", "categories");
        String validEmail = JsonReader.get("TCL-018", "email");
        String validPw = JsonReader.get("TCL-018", "password");
        String validDisplayName = JsonReader.get("TCL-018", "displayName");
        String validCity = JsonReader.get("TCL-018", "city");
        String validCountry = JsonReader.get("TCL-018", "country");
        String validBio = JsonReader.get("TCL-018", "bio");
        String validHourlyRate = JsonReader.get("TCL-018", "hourlyRate");
        String validExperience = JsonReader.get("TCL-018", "experience");
        String validPrimaryWorkArea = JsonReader.get("TCL-018", "primaryWorkArea");
        String validPreferredSurroundingAreas = JsonReader.get("TCL-018", "preferredSurroundingAreas");
        String validRateCurrency = JsonReader.get("TCL-018", "rateCurrency");

        for (String category : categories) {
            driver.navigate().refresh();

            joinPage.type(JoinPage.EMAIL_TEXT_FIELD, validEmail);
            joinPage.type(JoinPage.PASSWORD_TEXT_FIELD, validPw);
            joinPage.type(JoinPage.DISPLAY_NAME_TEXT_FIELD, validDisplayName);
            joinPage.selectProfRadio();
            joinPage.selectCategory(category);
            joinPage.fillProfessionalSpecificFields(validExperience, validHourlyRate, validPrimaryWorkArea, validPreferredSurroundingAreas, validRateCurrency);
            joinPage.clickServiceLocationPin();
            joinPage.type(JoinPage.CITY_TEXT_FIELD, validCity);
            joinPage.type(JoinPage.COUNTRY_TEXT_FIELD, validCountry);
            joinPage.type(JoinPage.BIO_TEXT_FIELD, validBio);
            joinPage.clickCreateAccount();

            String errMsg = joinPage.getValidationMsg(JoinPage.ERROR_MSG);
            softAssert.assertTrue(errMsg.contains("Please add at least one specialty."),
                    "Expected Specialty error, but got: " + errMsg);
            String speMsg = joinPage.getValidationMsg(JoinPage.SPE_MSG);
            softAssert.assertTrue(speMsg.contains("No specialties added yet."),
                    "Expected No Specialty error, but got: " + speMsg);
        }
        softAssert.assertAll();
    }

    @Test(description = "TCL-019: Verify Specialties - Sub Category dropdown enabling")
    public void verifySubCategoryEnabling() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        List<String> Categories = JsonReader.getList("TCL-019", "categories");
        List<String> previousOptions = new ArrayList<>();
        joinPage.selectProfRadio();
        for (String category : Categories) {
            joinPage.selectCategory(category);

            boolean isDisabled = joinPage.isSubCatDisabled();
            softAssert.assertFalse(isDisabled, "Sub Category dropdown should be enabled when a category is selected.");

            List<String> currentOptions = joinPage.getSubCategoryOptions();
            if (!previousOptions.isEmpty()) {
                softAssert.assertNotEquals(currentOptions, previousOptions,
                        "Sub-Category options did not change when switching to: " + category);
            }
            previousOptions = new ArrayList<>(currentOptions);
        }
        softAssert.assertAll();
    }

    @Test(description = "TCL-020: Verify Specialties - Sub Category dropdown validations")
    public void verifySubCategory() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Map<String, List<String>> specialtiesMap = JsonReader.getMap("TCL-020", "specialtiesData");
        String validEmail = JsonReader.get("TCL-020", "email");
        String validPw = JsonReader.get("TCL-020", "password");
        String validDisplayName = JsonReader.get("TCL-020", "displayName");
        String validCity = JsonReader.get("TCL-020", "city");
        String validCountry = JsonReader.get("TCL-020", "country");
        String validBio = JsonReader.get("TCL-020", "bio");
        String validHourlyRate = JsonReader.get("TCL-020", "hourlyRate");
        String validExperience = JsonReader.get("TCL-020", "experience");
        String validPrimaryWorkArea = JsonReader.get("TCL-020", "primaryWorkArea");
        String validPreferredSurroundingAreas = JsonReader.get("TCL-020", "preferredSurroundingAreas");
        String validRateCurrency = JsonReader.get("TCL-020", "rateCurrency");

        joinPage.type(JoinPage.EMAIL_TEXT_FIELD, validEmail);
        joinPage.type(JoinPage.PASSWORD_TEXT_FIELD, validPw);
        joinPage.type(JoinPage.DISPLAY_NAME_TEXT_FIELD, validDisplayName);
        joinPage.selectProfRadio();
        for (String categoryName : specialtiesMap.keySet()) {
            joinPage.selectCategory(categoryName);
            List<String> subCatList = specialtiesMap.get(categoryName);
            for (String subCat : subCatList) {
                joinPage.selectSubCategory(subCat);
            }
        }
        joinPage.fillProfessionalSpecificFields(validExperience, validHourlyRate, validPrimaryWorkArea, validPreferredSurroundingAreas, validRateCurrency);
        joinPage.clickServiceLocationPin();
        joinPage.type(JoinPage.CITY_TEXT_FIELD, validCity);
        joinPage.type(JoinPage.COUNTRY_TEXT_FIELD, validCountry);
        joinPage.type(JoinPage.BIO_TEXT_FIELD, validBio);
        joinPage.clickCreateAccount();

        String speMsg = joinPage.getValidationMsg(JoinPage.SPE_MSG);
        softAssert.assertTrue(speMsg.contains("Selected sub-category has been added."),
                "Expected No Specialty error, but got: " + speMsg);
        softAssert.assertTrue(joinPage.isElementDisplayed(JoinPage.VERIFY_EMAIL_POPUP), "Should be on verify page");

        softAssert.assertAll();
    }

}
