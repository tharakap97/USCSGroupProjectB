package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogInPage extends BasePage {

    // 🔹 Locators (update after inspecting real site)
    // NOTE: This site is a React app; locators are intentionally flexible.
    private final By openLoginModalBtn = By.xpath(
            "//a[contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'log in') or " +
                    "contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'login') or " +
                    "contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'sign in')]" +
                    "|//button[contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'log in') or " +
                    "contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'login') or " +
                    "contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'sign in')]"
    );

    private final By emailField = By.xpath("//input[@type='email' or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'email')]");
    private final By passwordField = By.xpath("//input[@type='password' or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'password')]");

    private final By submitBtn = By.xpath("//button[@type='submit' or contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'log in') or contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'sign in')]");

    // Marker to indicate login success (adjust if needed)
    private final By loggedInMarker = By.xpath(
            "//*[contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'logout')" +
                    " or contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'my profile')]"
    );

    public LogInPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Returns true if the UI shows evidence that the user is logged in.
     */
    public boolean isLoggedIn() {
        return !driver.findElements(loggedInMarker).isEmpty();
    }

    /**
     * Logs in and waits until a post-login marker is visible.
     * If login fails, this method will throw via locatorWaiting().
     */
    public void login(String email, String password) {
        // Some pages require opening a login modal first.
        clickIfPresent(openLoginModalBtn);

        type(emailField, email);
        type(passwordField, password);
        click(submitBtn);

        // Deterministic verification: wait for a logged-in marker.
        locatorWaiting(loggedInMarker);
    }

    public void goToSearchPage() {
        driver.get("https://test.ineedtofindsomeonefor.com/search?parent_category_id=0&category_id=0&preferred_currency=LKR&lat=0&lng=0&radius_km=0");
    }
}
