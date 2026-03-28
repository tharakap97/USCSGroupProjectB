package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FindPage extends BasePage {

    private By browseBtn = By.xpath("//*[contains(text(),'Browse')]"); // Obsolete on search page
    private By findFriend = By.xpath("//button[contains(text(),'Find a Friend')]");
    private By studyBuddy = By.xpath("//button[contains(text(),'Study Buddy')]");
    private By locationInput = By.xpath("//input[@placeholder='Colombo, Kandy...']");
    private By categoryDropdown = By.xpath("//label[text()='Main category']/following-sibling::select");
    private By ratingInput = By.xpath("//label[text()='Min rating']/following-sibling::input");
    private By radiusInput = By.xpath("//label[text()='Search radius (km)']/following-sibling::input");
    private By results = By.xpath("//div[contains(@class,'result') or contains(@class,'card')]");
    private By noResults = By.xpath("//*[contains(text(),'No results')]");

    public FindPage(WebDriver driver) {
        super(driver);
    }

    public void openBrowseProfessional() {
        // Not present on the search page; it's the default view natively.
    }

    public void selectFindFriend() {
        click(findFriend);
    }

    public void selectStudyBuddy() {
        click(studyBuddy);
    }

    public void enterLocation(String location) {
        type(locationInput, location);
    }

    public void selectCategory(String category) {
        click(categoryDropdown);
        // You may refine this based on real dropdown
    }

    public void setRating(int rating) {
        type(ratingInput, String.valueOf(rating));
    }

    public void setRadius(String radius) {
        type(radiusInput, radius);
    }

    public boolean isResultsDisplayed() {
        try {
            waitForElement(results);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isNoResultsDisplayed() {
        try {
            waitForElement(noResults);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}