package tests.signIn;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignInPage;
import tests.BaseTest;

public class SignInTest extends BaseTest {
    private SignInPage signInPage;

    @BeforeMethod
    public void verifyJoinPage() {
        HomePage homePage = BasePage.initApp();
        signInPage = homePage.selectSignIn();
    }

    @Test
    public void successLogin() throws InterruptedException {
        //Thread.sleep(5000);
        signInPage.typeEmail("yashguruge@gmail.com");
       // Thread.sleep(5000);
        signInPage.typePassword("");
    }
}
