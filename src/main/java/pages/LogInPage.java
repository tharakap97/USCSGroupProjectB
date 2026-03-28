package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogInPage extends BasePage {

    private By headerSignInLink = By.xpath("//a[@href='/signin']");
    private By emailField = By.xpath("//input[@type='email']");
    private By passwordField = By.xpath("//input[@type='password']");
    private By loginBtn = By.xpath("//button[contains(text(),'Login') or contains(text(),'Sign')]");

    public LogInPage(WebDriver driver) {
        super(driver);
    }

    public void login(String email, String password) {
        click(headerSignInLink);
        type(emailField, email);
        type(passwordField, password);
        click(loginBtn);
    }

    public void goToSearchPage() {
        navigate("https://test.ineedtofindsomeonefor.com/search");
    }
}