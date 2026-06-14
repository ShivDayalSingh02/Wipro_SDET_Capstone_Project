package testcases;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test(priority = 1)
    public void invalidLoginTest() {

        LoginPage login =
                new LoginPage(driver);

        login.login(
                "wrongUser",
                "wrongPass");

        WebDriverWait wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(10));

        Alert alert =
                wait.until(
                        ExpectedConditions.alertIsPresent());

        String alertText =
                alert.getText();

        System.out.println(
                "Alert Message : "
                        + alertText);

        Assert.assertTrue(
                alertText.contains(
                        "User or Password is not valid"));

        alert.accept();
    }

    @Test(priority = 2)
    public void emptyLoginTest() {

        LoginPage login =
                new LoginPage(driver);

        login.login("", "");

        try {

            WebDriverWait wait =
                    new WebDriverWait(
                            driver,
                            Duration.ofSeconds(5));

            Alert alert =
                    wait.until(
                            ExpectedConditions.alertIsPresent());

            String alertText =
                    alert.getText();

            System.out.println(
                    "Alert Message : "
                            + alertText);

            alert.accept();

        } catch(Exception e) {

            System.out.println(
                    "No Alert Displayed");

            Assert.assertTrue(
                    driver.getCurrentUrl()
                            .contains("V4"));
        }
    }
}