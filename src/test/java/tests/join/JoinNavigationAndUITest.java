package tests.join;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.BasePage;
import pages.HomePage;
import pages.JoinPage;
import tests.BaseTest;

public class JoinNavigationAndUITest extends BaseTest {
    private JoinPage joinPage;
    private HomePage homePage;

    @BeforeMethod
    public void goToHome() {
        homePage = new BasePage(driver).initApp();
    }

    @Test(description = "TCL-001-1: Verify Create your account page navigation with Join Button")
    public void testNavigationToJoinPage1() {
        joinPage = homePage.clickJoin();
        boolean isCreateAccDisplayed = homePage.isElementDisplayed(JoinPage.CREATE_ACC_PAGE);
        Assert.assertTrue(isCreateAccDisplayed, "Navigate to Create your account page failed.");
    }

    @Test(description = "TCL-001-2: Verify Create your account page navigation with Create Acc Button")
    public void testNavigationToJoinPage2() {
        joinPage = homePage.clickCreateAcc();
        boolean isCreateAccDisplayed = homePage.isElementDisplayed(JoinPage.CREATE_ACC_PAGE);
        Assert.assertTrue(isCreateAccDisplayed, "Navigate to Create your account page failed.");
    }

    @Test(description = "TCL-007: Verify Prof. button switching")
    public void testProfRoleButtons() {
        joinPage = homePage.clickJoin();
        joinPage.selectSeekerRadio();
        joinPage.selectProfBtn();
        Assert.assertTrue(homePage.isElementDisplayed(JoinPage.SPECIALITIES) && homePage.isBtnSelected(JoinPage.REG_AS_RADIO_PROF), "Specialities should be visible and Professional radio button should be clicked.");
    }

    @Test(description = "TCL-008: Verify Seeker button switching")
    public void testSeekerRoleButtons() {
        joinPage = homePage.clickJoin();
        joinPage.selectProfRadio();
        joinPage.selectSeekerBtn();
        Assert.assertTrue(!homePage.isElementDisplayed(JoinPage.SPECIALITIES) && homePage.isBtnSelected(JoinPage.REG_AS_RADIO_SEEKER), "Specialities should not be visible and Seeker radio button should be clicked.");
    }

    @Test(description = "TCL-012: Verify Radio buttons switching (Seeker/ Professional/ Both)")
    public void testRoleRadioButtons() {
        SoftAssert softAssert = new SoftAssert();
        joinPage = homePage.clickJoin();

        joinPage.selectSeekerRadio();
        softAssert.assertTrue(!homePage.isElementDisplayed(JoinPage.SPECIALITIES) && homePage.isElementDisplayed(JoinPage.IM_PROF_BTN), "Specialities should not be visible and I'm Professional button should be visible for seeker.");

        joinPage.selectProfRadio();
        softAssert.assertTrue(homePage.isElementDisplayed(JoinPage.SPECIALITIES) && homePage.isElementDisplayed(JoinPage.IM_SEEKER_BTN), "Specialities should be visible and  I'm Seeker button should be visible for professional.");

        joinPage.selectBothRadio();
        softAssert.assertTrue(homePage.isElementDisplayed(JoinPage.SPECIALITIES) && homePage.isElementDisplayed(JoinPage.IM_PROF_BTN), "Specialities should  be visible and I'm Professional button should be visible for both.");

        softAssert.assertAll();
    }
}
