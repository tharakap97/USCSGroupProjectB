package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.BasePage;
import pages.LogInPage;

public class BaseTest {

    protected BasePage BasePage;
    protected WebDriver driver;

    @BeforeClass
    public void setupBrowser(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        BasePage = PageFactory.initElements(driver, BasePage.class);
        BasePage.initApp();

        // Login once before running the tests
        LogInPage logInPage = new LogInPage(driver);
        logInPage.login("www.sandunarjuna@gmail.com", "Sandun@071");

        // Navigate to Search/Find page after login
        logInPage.goToSearchPage();
    }

    @AfterClass
    public void teardownBrowser(){
        if(driver != null){
            driver.quit();
        }
    }
}
