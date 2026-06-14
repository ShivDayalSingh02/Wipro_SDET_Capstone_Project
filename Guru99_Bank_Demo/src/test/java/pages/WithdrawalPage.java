package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utilities.WaitUtils;

public class WithdrawalPage {

    WebDriver driver;

    public WithdrawalPage(WebDriver driver) {
        this.driver = driver;
    }

    By withdrawalLink = By.linkText("Withdrawal");
    By accountNo = By.name("accountno");
    By amount = By.name("ammount");
    By description = By.name("desc");
    By submit = By.name("AccSubmit");

    public void withdrawMoney(
            String accNo,
            String amt) {

        WebElement withdrawalMenu =
                WaitUtils.waitForClickable(
                        driver,
                        withdrawalLink);

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].click();",
                withdrawalMenu);

        WaitUtils.waitForElement(
                driver,
                accountNo)
                .sendKeys(accNo);

        driver.findElement(amount)
                .sendKeys(amt);

        driver.findElement(description)
                .sendKeys("Withdraw");

        WaitUtils.waitForClickable(
                driver,
                submit)
                .click();
    }
}