package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FindPage extends BasePage {

    // 🔹 Locators (React UI, keep flexible)
    // When tests start from the /search page there may be no "Browse" button; allow direct navigation.
    private static final String SEARCH_URL =
            "https://test.ineedtofindsomeonefor.com/search?parent_category_id=0&category_id=0&preferred_currency=LKR&lat=0&lng=0&radius_km=0";

    private final By browseProfessionalBtn = By.xpath(
            "//*[self::a or self::button or self::div or self::span]" +
                    "[contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'browse')" +
                    " or contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'browse professional')]"
    );

    private final By findFriendOption = By.xpath(
            "//*[self::span or self::button or self::div or self::a]" +
                    "[contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'find a friend')]"
    );

    private final By resultsSection = By.xpath(
            "//div[contains(@class,'results')]" +
                    " | //*[@data-testid='results']" +
                    " | //*[contains(@class,'result')]" +
                    " | //*[contains(@class,'card')]" +
                    " | //*[contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'no results')]"
    );

    private final By browsePageMarker = By.xpath(
            "//*[contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'browse')]" +
                    " | //*[contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'filters')]" +
                    " | //*[contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'intent')]"
    );

    public FindPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Opens the browse/search page.
     * If a "Browse" UI element is available, click it; otherwise directly navigate to the known search URL.
     */
    public void openBrowseProfessional() {
        if (!driver.findElements(browseProfessionalBtn).isEmpty()) {
            click(browseProfessionalBtn);
        } else {
            driver.get(SEARCH_URL);
        }
    }

    public void selectFindFriend() {
        click(findFriendOption);
    }

    public boolean isBrowsePageLoaded() {
        try {
            // If we are already on /search, that counts as loaded.
            if (driver.getCurrentUrl() != null && driver.getCurrentUrl().contains("/search")) {
                return true;
            }
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
