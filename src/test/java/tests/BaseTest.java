package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LogInPage;

public class BaseTest {

    protected WebDriver driver;

    @AfterMethod
    public void waitBetweenTests() throws InterruptedException {
        Thread.sleep(3000);
    }

    @BeforeClass
    public void setupBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://test.ineedtofindsomeonefor.com/");

        // Login once
        LogInPage loginPage = new LogInPage(driver);
        loginPage.login("www.sandunarjuna@gmail.com", "Sandun@071");

        // Go to search page
        loginPage.goToSearchPage();
    }

    @AfterClass
    public void teardownBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}