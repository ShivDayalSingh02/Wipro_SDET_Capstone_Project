package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.TestData;

public class NewAccountPage {

    WebDriver driver;

    public NewAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    By newAccountLink = By.linkText("New Account");

    By customerId = By.name("cusid");

    By accountType = By.name("selaccount");

    By initialDeposit = By.name("inideposit");

    By submitBtn = By.name("button2");

    public void createAccount(String custId) {

        WebDriverWait wait =
                new WebDriverWait(driver,
                        Duration.ofSeconds(20));

        WebElement accountMenu =
                wait.until(
                        ExpectedConditions
                                .elementToBeClickable(
                                        newAccountLink));

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].click();",
                accountMenu);

        wait.until(
                ExpectedConditions
                        .visibilityOfElementLocated(
                                customerId));

        driver.findElement(customerId)
                .sendKeys(custId);

        driver.findElement(accountType)
                .sendKeys("Savings");

        driver.findElement(initialDeposit)
                .sendKeys("5000");

        driver.findElement(submitBtn)
                .click();

        wait.until(
                ExpectedConditions
                        .visibilityOfElementLocated(
                                By.xpath(
                                        "//td[text()='Account ID']/following-sibling::td")));

        String accountId =
                driver.findElement(
                        By.xpath(
                                "//td[text()='Account ID']/following-sibling::td"))
                        .getText();

        if (TestData.accountId1 == null) {

            TestData.accountId1 = accountId;

        } else {

            TestData.accountId2 = accountId;
        }

        System.out.println(
                "Generated Account ID = "
                        + accountId);
    }
}