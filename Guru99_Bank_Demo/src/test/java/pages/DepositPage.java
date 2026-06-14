package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utilities.WaitUtils;

public class DepositPage {

    WebDriver driver;

    public DepositPage(WebDriver driver) {
        this.driver = driver;
    }

    By depositLink = By.linkText("Deposit");
    By accountNo = By.name("accountno");
    By amount = By.name("ammount");
    By description = By.name("desc");
    By submit = By.name("AccSubmit");

    public void depositMoney(String accNo, String amt) {

        WebElement depositMenu =
                WaitUtils.waitForClickable(
                        driver,
                        depositLink);

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].click();",
                depositMenu);

        WaitUtils.waitForElement(
                driver,
                accountNo)
                .sendKeys(accNo);

        driver.findElement(amount)
                .sendKeys(amt);

        driver.findElement(description)
                .sendKeys("Deposit");

        WaitUtils.waitForClickable(
                driver,
                submit)
                .click();
    }
}