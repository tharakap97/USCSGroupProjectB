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

    // An optional locator that indicates user is logged in (update to something stable once known)
    private final By loggedInMarker = By.xpath("//*[contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'logout') or contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'my profile')]");

    public LogInPage(WebDriver driver) {
        super(driver);
    }

    public void login(String email, String password) {
        // Some pages require opening a login modal first.
        clickIfPresent(openLoginModalBtn);

        type(emailField, email);
        type(passwordField, password);
        click(submitBtn);

        // Best-effort verification: either URL changes or a logged-in marker appears.
        // If this fails, it means the locators need to be aligned with the real DOM.
        try {
            locatorWaiting(loggedInMarker);
        } catch (Exception ignored) {
            // fall back: do not hard fail here; tests can still fail later if not logged in
        }
    }
}
