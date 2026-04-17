package tests.signIn;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignInPage;
import tests.BaseTest;

import javax.lang.model.element.Name;

public class SignInTest extends BaseTest {
    private SignInPage signInPage;

    @BeforeMethod
    public void verifyJoinPage(){
        HomePage homePage = BasePage.initApp();
        signInPage = homePage.selectSignIn();
    }

//    If Logout xpath moved to Dashboard page, move this test to DashboardTet
//    @Test (dependsOnMethods = "successfulLogin")
//    public void logOut(){
//        signInPage.clickLogout();

    @Test (priority = 1)
    public void emptyEmailAndPasswordLogin() throws InterruptedException {
        signInPage.typeText(SignInPage.LOGIN_EMAIL, "");
        signInPage.typeText(SignInPage.LOGIN_PASSWORD, "");
        Thread.sleep(2000);
        signInPage.clickSignIn();
        Thread.sleep(2000);
    }

    @Test (priority = 2)
    public void emptyPasswordLogin() throws InterruptedException {
        signInPage.typeText(SignInPage.LOGIN_EMAIL, "Mary@yopmail.com");
        signInPage.typeText(SignInPage.LOGIN_PASSWORD, "");
        Thread.sleep(2000);
        signInPage.clickSignIn();
        Thread.sleep(2000);
    }

    @Test (priority = 3)
    public void invalidPasswordLogin() throws InterruptedException {
        signInPage.typeText(SignInPage.LOGIN_EMAIL, "Mary@yopmail.com");
        signInPage.typeText(SignInPage.LOGIN_PASSWORD, "Incorrect@123");
        Thread.sleep(2000);
        signInPage.clickSignIn();
        Thread.sleep(2000);
    }

    @Test (priority = 4)
    public void checkShowAndHideButton() throws InterruptedException {
        signInPage.typeText(SignInPage.LOGIN_PASSWORD, "TryOne@111");
        Thread.sleep(2000);
        signInPage.clickShowButton();
        Thread.sleep(2000);
        signInPage.clickHideButton();
        Thread.sleep(2000);
        signInPage.clickShowButton();
        Thread.sleep(2000);
        signInPage.clickHideButton();
        Thread.sleep(2000);
    }

    @Test(priority = 5)
    public void tickUntickRememberMyCredentials() throws InterruptedException {
        signInPage.clickRememberMyCredentials();
        Thread.sleep(2000);
        signInPage.clickRememberMyCredentials();
        Thread.sleep(2000);
        signInPage.clickRememberMyCredentials();
        Thread.sleep(2000);
    }

    @Test (priority = 6)
    public void goToCreateAccPage() throws InterruptedException {
        signInPage.clickCreateAcc();
        Thread.sleep(2000);
    }

    @Test(priority = 6)
    public void successfulLogin() throws InterruptedException {
        signInPage.typeText(SignInPage.LOGIN_EMAIL, "Mary@yopmail.com");
        signInPage.typeText(SignInPage.LOGIN_PASSWORD, "Mary1234*");
        signInPage.clickSignIn();
        Thread.sleep(2000);
    }


}