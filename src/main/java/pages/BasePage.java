package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected WebDriver driver;
    public static final long WAIT_TIME = 10;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void click(By locator) {
        waitForElement(locator);
        driver.findElement(locator).click();
    }

    public void type(By locator, String text) {
        waitForElement(locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    public boolean isDisplayed(By locator) {
        return !driver.findElements(locator).isEmpty() &&
                driver.findElement(locator).isDisplayed();
    }

    public void navigate(String url) {
        driver.get(url);
    }
}