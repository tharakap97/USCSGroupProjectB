package tests.join;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.JoinPage;
import tests.BaseTest;



public class JoinBaseTest extends BaseTest {
    private JoinPage joinPage;

    @BeforeMethod
    public void verifyJoinPage() {
        joinPage = BasePage.initApp();
        joinPage.clickJoin();
    }

    @Test
    public void fillTextFields() throws InterruptedException {
        joinPage.typeText(JoinPage.EMAIL_TEXT_FIELD, "dilsharwe.aabesiri@gmail.com");
        joinPage.typeText(JoinPage.PASSWORD_TEXT_FIELD,"#ABCabc123");
        joinPage.typeText(JoinPage.DISPLAY_NAME_TEXT_FIELD,"Dulki");
        joinPage.typeText(JoinPage.CITY_TEXT_FIELD, "Horana");
        joinPage.typeText(JoinPage.COUNTRY_TEXT_FIELD, "Sri Lanka");
        joinPage.typeText(JoinPage.BIO_TEXT_FIELD, "Test");
        joinPage.clickCreateAcc();

    }

//    @Test
//    public void createAcc() throws InterruptedException {
//        logInPage.clickCreateAcc();
//    }
}
