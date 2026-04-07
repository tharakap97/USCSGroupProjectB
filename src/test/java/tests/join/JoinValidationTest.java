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

            joinPage.fillEmailFields(invalidEmail);
            joinPage.fillPwFields(validPassword);
            joinPage.fillDisplayNameFields(validDisplayName);
            joinPage.fillCityFields(validCity);
            joinPage.fillCountryFields(validCountry);
            joinPage.fillBioFields(validBio);
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

        joinPage.fillEmailFields(existingEmail);
        joinPage.fillPwFields(validPassword);
        joinPage.fillDisplayNameFields(validDisplayName);
        joinPage.fillCityFields(validCity);
        joinPage.fillCountryFields(validCountry);
        joinPage.fillBioFields(validBio);
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

            joinPage.fillEmailFields(validEmail);
            joinPage.fillPwFields(invalidPassword);
            joinPage.fillDisplayNameFields(validDisplayName);
            joinPage.fillCityFields(validCity);
            joinPage.fillCountryFields(validCountry);
            joinPage.fillBioFields(validBio);
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

            joinPage.fillEmailFields(validEmail);
            joinPage.fillPwFields(validPw);
            joinPage.fillDisplayNameFields(invalidDisplayName);
            joinPage.fillCityFields(validCity);
            joinPage.fillCountryFields(validCountry);
            joinPage.fillBioFields(validBio);
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

        joinPage.fillEmailFields(validEmail);
        joinPage.fillPwFields(validPw);
        joinPage.fillDisplayNameFields(validDisplayName);
        joinPage.fillCityFields(invalidCity);
        joinPage.fillCountryFields(validCountry);
        joinPage.fillBioFields(validBio);
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

        joinPage.fillEmailFields(validEmail);
        joinPage.fillPwFields(validPw);
        joinPage.fillDisplayNameFields(validDisplayName);
        joinPage.fillCityFields(validCity);
        joinPage.fillCountryFields(validCountry);
        joinPage.fillBioFields(invalidBio);
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

        joinPage.fillCountryFields("USA");
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

        joinPage.fillEmailFields(validEmail);
        joinPage.fillPwFields(validPw);
        joinPage.fillDisplayNameFields(validDisplayName);
        joinPage.selectSeekerRadio();
        joinPage.fillCityFields(validCity);
        joinPage.fillCountryFields(validCountry);
        joinPage.fillBioFields(validBio);
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
        String validServiceRadiusValue = JsonReader.get("TCL-018", "serviceRadiusValue");

        for (String category : categories) {
            driver.navigate().refresh();

            joinPage.fillEmailFields(validEmail);
            joinPage.fillPwFields(validPw);
            joinPage.fillDisplayNameFields(validDisplayName);
            joinPage.selectProfRadio();
            joinPage.selectCategory(category);
            joinPage.fillProfessionalSpecificFields(validExperience, validHourlyRate, validPrimaryWorkArea, validPreferredSurroundingAreas, validRateCurrency);
            joinPage.fillServiceRadius(validServiceRadiusValue);
            joinPage.clickServiceLocationPin();
            joinPage.fillCityFields(validCity);
            joinPage.fillCountryFields(validCountry);
            joinPage.fillBioFields(validBio);
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
        String validServiceRadiusValue = JsonReader.get("TCL-020", "serviceRadiusValue");

        joinPage.fillEmailFields(validEmail);
        joinPage.fillPwFields(validPw);
        joinPage.fillDisplayNameFields(validDisplayName);
        joinPage.selectProfRadio();
        for (String categoryName : specialtiesMap.keySet()) {
            joinPage.selectCategory(categoryName);
            List<String> subCatList = specialtiesMap.get(categoryName);
            for (String subCat : subCatList) {
                joinPage.selectSubCategory(subCat);
            }
        }
        joinPage.fillProfessionalSpecificFields(validExperience, validHourlyRate, validPrimaryWorkArea, validPreferredSurroundingAreas, validRateCurrency);
        joinPage.fillServiceRadius(validServiceRadiusValue);
        joinPage.clickServiceLocationPin();
        joinPage.fillCityFields(validCity);
        joinPage.fillCountryFields(validCountry);
        joinPage.fillBioFields(validBio);
        joinPage.clickCreateAccount();

        String speMsg = joinPage.getValidationMsg(JoinPage.SPE_MSG);
        softAssert.assertTrue(speMsg.contains("Selected sub-category has been added."),
                "Expected No Specialty error, but got: " + speMsg);
        softAssert.assertTrue(joinPage.isElementDisplayed(JoinPage.VERIFY_EMAIL_POPUP), "Should be on verify page");

        softAssert.assertAll();
    }

    @Test(description = "TCL-021-1: Verify Specialties - Remove button working properly.")
    public void verifySpecRemoveBtn() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        String category = JsonReader.get("TCL-021", "category");
        String subCategory = JsonReader.get("TCL-021", "subCategory");

        joinPage.selectProfRadio();
        joinPage.selectCategory(category);
        joinPage.selectSubCategory(subCategory);
        joinPage.clickSubCategoryRemove();

        String currentCategory = joinPage.getFirstCatOption();
        String currentSubCategory = joinPage.getFirstSubCatOption();

        softAssert.assertEquals(currentCategory, "Select category...",
                "Category dropdown did not reset to default!");

        softAssert.assertEquals(currentSubCategory, "Select sub-category...",
                "Sub-Category dropdown did not reset to default!");

        String speMsg = joinPage.getValidationMsg(JoinPage.SPE_MSG);
        softAssert.assertTrue(speMsg.contains("No specialties added yet."),
                "Expected No Specialty error, but got: " + speMsg);

        softAssert.assertAll();
    }

    @Test(description = "TCL-021-2: Verify Specialties - Clear All button working properly.")
    public void verifySpecClearAllBtn() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        Map<String, List<String>> specialtiesMap = JsonReader.getMap("TCL-021", "specialtiesData");

        joinPage.selectProfRadio();
        for (String categoryName : specialtiesMap.keySet()) {
            joinPage.selectCategory(categoryName);
            List<String> subCatList = specialtiesMap.get(categoryName);
            for (String subCat : subCatList) {
                joinPage.selectSubCategory(subCat);
            }
        }
        joinPage.clickSubCategoryClearAll();

        String currentCategory = joinPage.getFirstCatOption();
        String currentSubCategory = joinPage.getFirstSubCatOption();

        softAssert.assertEquals(currentCategory, "Select category...",
                "Category dropdown did not reset to default!");

        softAssert.assertEquals(currentSubCategory, "Select sub-category...",
                "Sub-Category dropdown did not reset to default!");

        String speMsg = joinPage.getValidationMsg(JoinPage.SPE_MSG);
        softAssert.assertTrue(speMsg.contains("No specialties added yet."),
                "Expected No Specialty error, but got: " + speMsg);

        softAssert.assertAll();
    }

    @Test(description = "TCL-022: Verify Experience (years) field.")
    public void verifyExperienceYr() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        String validEmail = JsonReader.get("TCL-022", "email");
        String validPw = JsonReader.get("TCL-022", "password");
        String validDisplayName = JsonReader.get("TCL-022", "displayName");
        String validCity = JsonReader.get("TCL-022", "city");
        String validCountry = JsonReader.get("TCL-022", "country");
        String validBio = JsonReader.get("TCL-022", "bio");
        String validCategory = JsonReader.get("TCL-022", "category");
        String validSubCategory = JsonReader.get("TCL-022", "subCategory");
        String validHourlyRate = JsonReader.get("TCL-022", "hourlyRate");
        String invalidExperience = JsonReader.get("TCL-022", "invalidExperience");
        String validPrimaryWorkArea = JsonReader.get("TCL-022", "primaryWorkArea");
        String validPreferredSurroundingAreas = JsonReader.get("TCL-022", "preferredSurroundingAreas");
        String validRateCurrency = JsonReader.get("TCL-022", "rateCurrency");
        String validServiceRadiusValue = JsonReader.get("TCL-022", "serviceRadiusValue");

        joinPage.fillEmailFields(validEmail);
        joinPage.fillPwFields(validPw);
        joinPage.fillDisplayNameFields(validDisplayName);
        joinPage.selectProfRadio();
        joinPage.selectCategory(validCategory);
        joinPage.selectSubCategory(validSubCategory);
        joinPage.fillProfessionalSpecificFields(invalidExperience, validHourlyRate, validPrimaryWorkArea, validPreferredSurroundingAreas, validRateCurrency);
        joinPage.fillServiceRadius(validServiceRadiusValue);
        joinPage.clickServiceLocationPin();
        joinPage.fillCityFields(validCity);
        joinPage.fillCountryFields(validCountry);
        joinPage.fillBioFields(validBio);
        joinPage.clickCreateAccount();

        String errMsg = joinPage.getValidationMsg(JoinPage.ERROR_MSG);
        softAssert.assertTrue(errMsg.contains("Experience must be a valid number"),
                "Expected No Experience error, but got: " + errMsg);

        softAssert.assertAll();
    }

    @Test(description = "TCL-023: Verify  HourlyRate field.")
    public void verifyHourlyRate() throws InterruptedException {

        String validEmail = JsonReader.get("TCL-023", "email");
        String validPw = JsonReader.get("TCL-023", "password");
        String validDisplayName = JsonReader.get("TCL-023", "displayName");
        String validCity = JsonReader.get("TCL-023", "city");
        String validCountry = JsonReader.get("TCL-023", "country");
        String validBio = JsonReader.get("TCL-023", "bio");
        String validCategory = JsonReader.get("TCL-023", "category");
        String validSubCategory = JsonReader.get("TCL-023", "subCategory");
        String invalidHourlyRate = JsonReader.get("TCL-023", "invalidHourlyRate");
        String validExperience = JsonReader.get("TCL-023", "experience");
        String validPrimaryWorkArea = JsonReader.get("TCL-023", "primaryWorkArea");
        String validPreferredSurroundingAreas = JsonReader.get("TCL-023", "preferredSurroundingAreas");
        String validRateCurrency = JsonReader.get("TCL-023", "rateCurrency");
        String validServiceRadiusValue = JsonReader.get("TCL-023", "serviceRadiusValue");

        joinPage.fillEmailFields(validEmail);
        joinPage.fillPwFields(validPw);
        joinPage.fillDisplayNameFields(validDisplayName);
        joinPage.selectProfRadio();
        joinPage.selectCategory(validCategory);
        joinPage.selectSubCategory(validSubCategory);
        joinPage.fillProfessionalSpecificFields(validExperience, invalidHourlyRate, validPrimaryWorkArea, validPreferredSurroundingAreas, validRateCurrency);
        joinPage.fillServiceRadius(validServiceRadiusValue);
        joinPage.clickServiceLocationPin();
        joinPage.fillCityFields(validCity);
        joinPage.fillCountryFields(validCountry);
        joinPage.fillBioFields(validBio);
        joinPage.clickCreateAccount();

        Assert.assertFalse(joinPage.isElementDisplayed(JoinPage.VERIFY_EMAIL_POPUP), "Should not be on verify page");

        String errMsg = joinPage.getValidationMsg(JoinPage.ERROR_MSG);
        Assert.assertTrue(errMsg.contains("Hourly Rate must be a valid number"),
                "Expected No Hourly Rate error, but got: " + errMsg);

    }

    @Test(description = "TCL-024: Verify Primary work area field.")
    public void verifyWorkArea() throws InterruptedException {

        String validEmail = JsonReader.get("TCL-024", "email");
        String validPw = JsonReader.get("TCL-024", "password");
        String validDisplayName = JsonReader.get("TCL-024", "displayName");
        String validCity = JsonReader.get("TCL-024", "city");
        String validCountry = JsonReader.get("TCL-024", "country");
        String validBio = JsonReader.get("TCL-024", "bio");
        String validCategory = JsonReader.get("TCL-024", "category");
        String validSubCategory = JsonReader.get("TCL-024", "subCategory");
        String validHourlyRate = JsonReader.get("TCL-024", "hourlyRate");
        String validExperience = JsonReader.get("TCL-024", "experience");
        String invalidPrimaryWorkArea = JsonReader.get("TCL-024", "invalidPrimaryWorkArea");
        String validPreferredSurroundingAreas = JsonReader.get("TCL-024", "preferredSurroundingAreas");
        String validRateCurrency = JsonReader.get("TCL-024", "rateCurrency");
        String validServiceRadiusValue = JsonReader.get("TCL-024", "serviceRadiusValue");

            joinPage.fillEmailFields(validEmail);
            joinPage.fillPwFields(validPw);
            joinPage.fillDisplayNameFields(validDisplayName);
            joinPage.selectProfRadio();
            joinPage.selectCategory(validCategory);
            joinPage.selectSubCategory(validSubCategory);
            joinPage.fillProfessionalSpecificFields(validExperience, validHourlyRate, invalidPrimaryWorkArea, validPreferredSurroundingAreas, validRateCurrency);
            joinPage.fillServiceRadius(validServiceRadiusValue);
            joinPage.clickServiceLocationPin();
            joinPage.fillCityFields(validCity);
            joinPage.fillCountryFields(validCountry);
            joinPage.fillBioFields(validBio);
            joinPage.clickCreateAccount();

        Assert.assertFalse(joinPage.isElementDisplayed(JoinPage.VERIFY_EMAIL_POPUP), "Should not be on verify page");

        String errMsg = joinPage.getValidationMsg(JoinPage.ERROR_MSG);
        Assert.assertTrue(errMsg.contains("Primary Work Area must be a valid area"),
                "Expected No Primary Work Area error, but got: " + errMsg);

    }

    @Test(description = "TCL-025: Verify Primary work area field.")
    public void verifySurroundingArea() throws InterruptedException {

        String validEmail = JsonReader.get("TCL-025", "email");
        String validPw = JsonReader.get("TCL-025", "password");
        String validDisplayName = JsonReader.get("TCL-025", "displayName");
        String validCity = JsonReader.get("TCL-025", "city");
        String validCountry = JsonReader.get("TCL-025", "country");
        String validBio = JsonReader.get("TCL-025", "bio");
        String validCategory = JsonReader.get("TCL-025", "category");
        String validSubCategory = JsonReader.get("TCL-025", "subCategory");
        String validHourlyRate = JsonReader.get("TCL-025", "hourlyRate");
        String validExperience = JsonReader.get("TCL-025", "experience");
        String validPrimaryWorkArea = JsonReader.get("TCL-025", "primaryWorkArea");
        String invalidPreferredSurroundingArea = JsonReader.get("TCL-025", "invalidPreferredSurroundingArea");
        String validRateCurrency = JsonReader.get("TCL-025", "rateCurrency");
        String validServiceRadiusValue = JsonReader.get("TCL-025", "serviceRadiusValue");

        joinPage.fillEmailFields(validEmail);
        joinPage.fillPwFields(validPw);
        joinPage.fillDisplayNameFields(validDisplayName);
        joinPage.selectProfRadio();
        joinPage.selectCategory(validCategory);
        joinPage.selectSubCategory(validSubCategory);
        joinPage.fillProfessionalSpecificFields(validExperience, validHourlyRate, validPrimaryWorkArea, invalidPreferredSurroundingArea, validRateCurrency);
        joinPage.fillServiceRadius(validServiceRadiusValue);
        joinPage.clickServiceLocationPin();
        joinPage.fillCityFields(validCity);
        joinPage.fillCountryFields(validCountry);
        joinPage.fillBioFields(validBio);
        joinPage.clickCreateAccount();

        Assert.assertFalse(joinPage.isElementDisplayed(JoinPage.VERIFY_EMAIL_POPUP), "Should not be on verify page");

        String errMsg = joinPage.getValidationMsg(JoinPage.ERROR_MSG);
        Assert.assertTrue(errMsg.contains("Preferred Surrounding Area must be a valid area"),
                "Expected No Preferred Surrounding Area error, but got: " + errMsg);

    }

    @Test(description = "TCL-026: Verify Rate Currency field is defaults to LKR")
    public void testRateCurrencyDefault() {
        joinPage.selectProfRadio();

        String defaultRateCurrency = joinPage.getRateCurrencyValue();
        Assert.assertEquals(defaultRateCurrency, "LKR", "Default Rate Currency is not LKR!");
    }

    @Test(description = "TCL-027: Verify Service Radius field.")
    public void verifyServiceRadius() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        List<String> invalidServiceRadiusValues = JsonReader.getList("TCL-027", "invalidServiceRadiusValues");

        String validEmail = JsonReader.get("TCL-027", "email");
        String validPw = JsonReader.get("TCL-027", "password");
        String validDisplayName = JsonReader.get("TCL-027", "displayName");
        String validCity = JsonReader.get("TCL-027", "city");
        String validCountry = JsonReader.get("TCL-027", "country");
        String validBio = JsonReader.get("TCL-027", "bio");
        String validCategory = JsonReader.get("TCL-027", "category");
        String validSubCategory = JsonReader.get("TCL-027", "subCategory");
        String validHourlyRate = JsonReader.get("TCL-027", "hourlyRate");
        String validExperience = JsonReader.get("TCL-027", "experience");
        String validPrimaryWorkArea = JsonReader.get("TCL-027", "primaryWorkArea");
        String validPreferredSurroundingArea = JsonReader.get("TCL-027", "preferredSurroundingArea");
        String validRateCurrency = JsonReader.get("TCL-027", "rateCurrency");

        for (String invalidServiceRadiusValue : invalidServiceRadiusValues) {
            driver.navigate().refresh();

            joinPage.fillEmailFields(validEmail);
            joinPage.fillPwFields(validPw);
            joinPage.fillDisplayNameFields(validDisplayName);
            joinPage.selectProfRadio();
            joinPage.selectCategory(validCategory);
            joinPage.selectSubCategory(validSubCategory);
            joinPage.fillProfessionalSpecificFields(validExperience, validHourlyRate, validPrimaryWorkArea, validPreferredSurroundingArea, validRateCurrency);
            joinPage.fillServiceRadius(invalidServiceRadiusValue);
            joinPage.clickServiceLocationPin();
            joinPage.fillCityFields(validCity);
            joinPage.fillCountryFields(validCountry);
            joinPage.fillBioFields(validBio);
            joinPage.clickCreateAccount();

            String errMsg = joinPage.getValidationMsg(JoinPage.ERROR_MSG);

            if (invalidServiceRadiusValue.equals("-1")) {
                softAssert.assertTrue(errMsg.contains("Service Radius Value must be greater than or equal to 0"),
                        "Expected No Service Radius value error, but got: " + errMsg);
            } else if (invalidServiceRadiusValue.equals("1000")) {
                softAssert.assertTrue(errMsg.contains("Service Radius Value must be less than or equal to 500"),
                        "Expected No Service Radius value error, but got: " + errMsg);
            }

        }
        softAssert.assertAll();

    }

    @Test(description = "TCL-028: Verify Service Location field.")
    public void verifyServiceLocation() throws InterruptedException {

        String validEmail = JsonReader.get("TCL-028", "email");
        String validPw = JsonReader.get("TCL-028", "password");
        String validDisplayName = JsonReader.get("TCL-028", "displayName");
        String validCity = JsonReader.get("TCL-028", "city");
        String validCountry = JsonReader.get("TCL-028", "country");
        String validBio = JsonReader.get("TCL-028", "bio");
        String validCategory = JsonReader.get("TCL-028", "category");
        String validSubCategory = JsonReader.get("TCL-028", "subCategory");
        String validHourlyRate = JsonReader.get("TCL-028", "hourlyRate");
        String validExperience = JsonReader.get("TCL-028", "experience");
        String validPrimaryWorkArea = JsonReader.get("TCL-028", "primaryWorkArea");
        String validPreferredSurroundingArea = JsonReader.get("TCL-028", "preferredSurroundingArea");
        String validRateCurrency = JsonReader.get("TCL-028", "rateCurrency");
        String validServiceRadiusValue = JsonReader.get("TCL-028", "serviceRadiusValue");

            joinPage.fillEmailFields(validEmail);
            joinPage.fillPwFields(validPw);
            joinPage.fillDisplayNameFields(validDisplayName);
            joinPage.selectProfRadio();
            joinPage.selectCategory(validCategory);
            joinPage.selectSubCategory(validSubCategory);
            joinPage.fillProfessionalSpecificFields(validExperience, validHourlyRate, validPrimaryWorkArea, validPreferredSurroundingArea, validRateCurrency);
            joinPage.fillServiceRadius(validServiceRadiusValue);
            joinPage.fillCityFields(validCity);
            joinPage.fillCountryFields(validCountry);
            joinPage.fillBioFields(validBio);
            joinPage.clickCreateAccount();

            String errMsg = joinPage.getValidationMsg(JoinPage.ERROR_MSG);

           Assert.assertTrue(errMsg.contains("Please set your service location pin on the map."),
                        "Expected No Service Location error, but got: " + errMsg);

    }

    @Test(description = "TCL-029: Verify Service location selection using Click my location button")
    public void verifyServiceLocationPin() {
        joinPage.selectProfRadio();
        joinPage.selectServiceLocation();

        String serLocMsg = joinPage.getValidationMsg(JoinPage.SERVICE_LOC_MSG);
        Assert.assertTrue(serLocMsg.contains("Selected:"),
                "Expected 'Selected:' in message, but actual message was: " + serLocMsg);
    }

    @Test(description = "TCL-030: Verify Service location pin- Clear pin button working properly.")
    public void verifyServiceLocationClearPin() {
        SoftAssert softAssert = new SoftAssert();
        joinPage.selectProfRadio();
        joinPage.selectServiceLocation();
        joinPage.clearServiceLocationPin();

        softAssert.assertFalse(joinPage.isElementDisplayed(JoinPage.MAP_PIN_ICON), "Map pin icon should be not visible after clearing the pin.");

        String serLocMsg = joinPage.getValidationMsg(JoinPage.SERVICE_LOC_MSG);
        softAssert.assertFalse(serLocMsg.contains("Selected:"),
                "Not expected 'Selected:' in message, but actual message was: " + serLocMsg);

        softAssert.assertAll();
    }

    @Test(description = "TCL-031: Verify Professional Account create correctly")
    public void verifyProfAcc() throws InterruptedException {

        String validEmail = JsonReader.get("TCL-031", "email");
        String validPw = JsonReader.get("TCL-031", "password");
        String validDisplayName = JsonReader.get("TCL-031", "displayName");
        String validCity = JsonReader.get("TCL-031", "city");
        String validCountry = JsonReader.get("TCL-031", "country");
        String validBio = JsonReader.get("TCL-031", "bio");
        String validCategory = JsonReader.get("TCL-031", "category");
        String validSubCategory = JsonReader.get("TCL-031", "subCategory");
        String validHourlyRate = JsonReader.get("TCL-031", "hourlyRate");
        String validExperience = JsonReader.get("TCL-031", "experience");
        String validPrimaryWorkArea = JsonReader.get("TCL-031", "primaryWorkArea");
        String validPreferredSurroundingArea = JsonReader.get("TCL-031", "preferredSurroundingArea");
        String validRateCurrency = JsonReader.get("TCL-031", "rateCurrency");
        String validServiceRadiusValue = JsonReader.get("TCL-031", "serviceRadiusValue");

        joinPage.fillEmailFields(validEmail);
        joinPage.fillPwFields(validPw);
        joinPage.fillDisplayNameFields(validDisplayName);
        joinPage.selectProfRadio();
        joinPage.selectCategory(validCategory);
        joinPage.selectSubCategory(validSubCategory);
        joinPage.fillProfessionalSpecificFields(validExperience, validHourlyRate, validPrimaryWorkArea, validPreferredSurroundingArea, validRateCurrency);
        joinPage.fillServiceRadius(validServiceRadiusValue);
        joinPage.clickServiceLocationPin();
        joinPage.fillCityFields(validCity);
        joinPage.fillCountryFields(validCountry);
        joinPage.fillBioFields(validBio);
        joinPage.clickCreateAccount();

        Assert.assertTrue(joinPage.isElementDisplayed(JoinPage.VERIFY_EMAIL_POPUP), "Should be on verify page");

    }

    @Test(description = "TCL-032: Verify Both Account create correctly")
    public void verifyBothAcc() throws InterruptedException {

        String validEmail = JsonReader.get("TCL-032", "email");
        String validPw = JsonReader.get("TCL-032", "password");
        String validDisplayName = JsonReader.get("TCL-032", "displayName");
        String validCity = JsonReader.get("TCL-032", "city");
        String validCountry = JsonReader.get("TCL-032", "country");
        String validBio = JsonReader.get("TCL-032", "bio");
        String validCategory = JsonReader.get("TCL-032", "category");
        String validSubCategory = JsonReader.get("TCL-032", "subCategory");
        String validHourlyRate = JsonReader.get("TCL-032", "hourlyRate");
        String validExperience = JsonReader.get("TCL-032", "experience");
        String validPrimaryWorkArea = JsonReader.get("TCL-032", "primaryWorkArea");
        String validPreferredSurroundingArea = JsonReader.get("TCL-032", "preferredSurroundingArea");
        String validRateCurrency = JsonReader.get("TCL-032", "rateCurrency");
        String validServiceRadiusValue = JsonReader.get("TCL-032", "serviceRadiusValue");

        joinPage.fillEmailFields(validEmail);
        joinPage.fillPwFields(validPw);
        joinPage.fillDisplayNameFields(validDisplayName);
        joinPage.selectBothRadio();
        joinPage.selectCategory(validCategory);
        joinPage.selectSubCategory(validSubCategory);
        joinPage.fillProfessionalSpecificFields(validExperience, validHourlyRate, validPrimaryWorkArea, validPreferredSurroundingArea, validRateCurrency);
        joinPage.fillServiceRadius(validServiceRadiusValue);
        joinPage.clickServiceLocationPin();
        joinPage.fillCityFields(validCity);
        joinPage.fillCountryFields(validCountry);
        joinPage.fillBioFields(validBio);
        joinPage.clickCreateAccount();

        Assert.assertTrue(joinPage.isElementDisplayed(JoinPage.VERIFY_EMAIL_POPUP), "Should be on verify page");

    }
}
