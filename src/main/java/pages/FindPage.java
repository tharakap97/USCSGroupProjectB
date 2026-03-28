package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FindPage extends BasePage {

    // 🔹 Locators (update after inspecting real site)
    private By browseProfessionalBtn = By.xpath("//a[contains(text(),'Browse')]");
    private By findFriendOption = By.xpath("//span[contains(text(),'Find a Friend')]");
    private By resultsSection = By.xpath("//div[contains(@class,'results')]");

    public FindPage(WebDriver driver) {
        super(driver);
    }

    // 🔹 Actions

    public void openBrowseProfessional() {
        click(browseProfessionalBtn);
    }

    public void selectFindFriend() {
        click(findFriendOption);
    }

    public boolean isResultsDisplayed() {
        waitForElement(resultsSection);
        return driver.findElement(resultsSection).isDisplayed();
    }

    private void waitForElement(By resultsSection) {
    }
}