package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FindPage extends BasePage {

    // 🔹 Locators (update after inspecting real site)
    private final By browseProfessionalBtn = By.xpath("//a[contains(text(),'Browse') or contains(text(),'Browse Professional')]");

    // Intent option (may be a button/div/span depending on UI)
    private final By findFriendOption = By.xpath(
            "//*[self::span or self::button or self::div][contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'find a friend')]"
    );

    // Generic results markers
    private final By resultsSection = By.xpath("//div[contains(@class,'results')] | //*[@data-testid='results'] | //*[contains(@class,'result')] ");

    // Generic browse page markers (update when you know exact elements)
    private final By browsePageMarker = By.xpath(
            "//*[contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'browse')]"
    );

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

    public boolean isBrowsePageLoaded() {
        try {
            locatorWaiting(browsePageMarker);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isResultsDisplayed() {
        locatorWaiting(resultsSection);
        return !driver.findElements(resultsSection).isEmpty() && driver.findElement(resultsSection).isDisplayed();
    }
}
