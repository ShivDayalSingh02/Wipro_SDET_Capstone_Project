package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utilities.WaitUtils;

public class FundTransferPage {

    WebDriver driver;

    public FundTransferPage(WebDriver driver) {
        this.driver = driver;
    }

    By fundTransferLink =
            By.linkText("Fund Transfer");

    By payerAccount =
            By.name("payersaccount");

    By payeeAccount =
            By.name("payeeaccount");

    By amount =
            By.name("ammount");

    By description =
            By.name("desc");

    By submit =
            By.name("AccSubmit");

    public void transferFunds(
            String fromAcc,
            String toAcc,
            String amt) {

    	WebElement fundTransfer =
    	        WaitUtils.waitForElement(
    	                driver,
    	                fundTransferLink);

    	JavascriptExecutor js =
    	        (JavascriptExecutor) driver;

    	js.executeScript(
    	        "arguments[0].click();",
    	        fundTransfer);

        driver.findElement(payerAccount)
                .sendKeys(fromAcc);

        driver.findElement(payeeAccount)
                .sendKeys(toAcc);

        driver.findElement(amount)
                .sendKeys(amt);

        driver.findElement(description)
                .sendKeys("Fund Transfer");

        WaitUtils.waitForElement(
                driver,
                submit)
                .click();
    }
}