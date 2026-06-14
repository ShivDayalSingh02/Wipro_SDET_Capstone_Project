package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.WaitUtils;
public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private By userId = By.name("uid");
    private By password = By.name("password");
    private By loginBtn = By.name("btnLogin");

    public void enterUserId(String uid) {

        WaitUtils.waitForElement(
                driver,
                userId)
                .sendKeys(uid);
    }

    public void enterPassword(String pass) {

        WaitUtils.waitForElement(
                driver,
                password)
                .sendKeys(pass);
    }

    public void clickLogin() {

        WaitUtils.waitForElement(
                driver,
                loginBtn)
                .click();
    }
    public void login(String uid, String pass) {

        enterUserId(uid);
        enterPassword(pass);
        clickLogin();
    }
}