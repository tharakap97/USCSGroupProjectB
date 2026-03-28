package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    public static final long EXPLICIT_WAIT_TIME_IN_SECONDS = 10;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void locatorWaiting(By locator){
        try{
            Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT_TIME_IN_SECONDS));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }catch (Exception ex){
            throw new RuntimeException("Locator not found : " + ex.getMessage());
        }
    }

    public void clear(By locator){
        locatorWaiting(locator);
    }

    public void type(By locator, String text){
        locatorWaiting(locator);
        clear(locator);
        driver.findElement(locator).sendKeys(text);
    }
    public void click(By locator){
        locatorWaiting(locator);
        driver.findElement(locator).click();
    }

    public LogInPage initApp() {
        driver.get("https://test.ineedtofindsomeonefor.com/");
        return PageFactory.initElements(driver, LogInPage.class);
    }

}
