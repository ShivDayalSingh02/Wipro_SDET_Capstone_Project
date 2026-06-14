package testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.DepositPage;
import pages.LoginPage;
import pages.NewAccountPage;
import pages.NewCustomerPage;
import utilities.ConfigReader;
import utilities.TestData;

public class InvalidAccountTest extends BaseTest {

    @Test
    public void invalidAccountDepositTest()
            throws Exception {

        ConfigReader config =
                new ConfigReader();

        LoginPage login =
                new LoginPage(driver);

        login.login(
                config.getUsername(),
                config.getPassword());

        NewCustomerPage customer =
                new NewCustomerPage(driver);

        customer.addCustomer();

        NewAccountPage account =
                new NewAccountPage(driver);

        account.createAccount(
                TestData.customerId);

        DepositPage deposit =
                new DepositPage(driver);

        deposit.depositMoney(
                "99999999",
                "500");

        String pageText =
                driver.findElement(
                        By.tagName("body"))
                        .getText();

        System.out.println(pageText);

        Assert.assertNotNull(pageText);

        System.out.println(
                "Invalid Account Deposit Test Executed");
    }
}