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
    public static final long EXPLICIT_WAIT_TIME_IN_SECONDS = 50;

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

    public void type(By locator, String text){
        locatorWaiting(locator);
        driver.findElement(locator).sendKeys(text);
    }
    public void click(By locator){
        locatorWaiting(locator);
        driver.findElement(locator).click();
    }

    public String getAttribute(By locator, String attributeName){
        locatorWaiting(locator);
        return driver.findElement(locator).getAttribute(attributeName);
    }

    public void clear(By locator){
        locatorWaiting(locator);
        driver.findElement(locator).clear();
    }

    public String getSelectedOptionText(By locator){
        locatorWaiting(locator);
        return driver.findElement(locator).getText();
    }

    public void selectDropdownOption(By locator, String optionText){
        locatorWaiting(locator);
        driver.findElement(locator).click();
    }

    public void selectDropdownByValue(By locator, String value){
        locatorWaiting(locator);
        driver.findElement(locator).sendKeys(value);
    }

    public HomePage initApp() {
        driver.get("https://test.ineedtofindsomeonefor.com/");
        return PageFactory.initElements(driver, HomePage.class);
    }

}
