package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.BasePage;

public class BaseTest {

    protected BasePage BasePage;
    protected WebDriver driver;

    @BeforeClass
    public void setupBrowser(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        BasePage = PageFactory.initElements(driver, BasePage.class);
    }

    @AfterClass
    public void quitBrowser(){
        driver.quit();
    }
}
