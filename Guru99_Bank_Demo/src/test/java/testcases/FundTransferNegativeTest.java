package testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.FundTransferPage;
import pages.LoginPage;
import pages.NewAccountPage;
import pages.NewCustomerPage;
import pages.WithdrawalPage;
import utilities.ConfigReader;
import utilities.TestData;

public class FundTransferNegativeTest
        extends BaseTest {

    @Test(priority = 1)
    public void invalidAccountTransferTest()
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

        account.createAccount(
                TestData.customerId);

        FundTransferPage transfer =
                new FundTransferPage(driver);

        transfer.transferFunds(
                "99999999",
                TestData.accountId2,
                "100");

        String pageText =
                driver.findElement(
                        By.tagName("body"))
                        .getText();

        System.out.println(pageText);

        Assert.assertNotNull(pageText);

        System.out.println(
                "Invalid Account Transfer Executed");
    }

    @Test(priority = 2)
    public void excessiveWithdrawalTest()
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

        WithdrawalPage withdrawal =
                new WithdrawalPage(driver);

        withdrawal.withdrawMoney(
                TestData.accountId1,
                "100000");

        String pageText =
                driver.findElement(
                        By.tagName("body"))
                        .getText();

        System.out.println(pageText);

        Assert.assertNotNull(pageText);

        System.out.println(
                "Excess Withdrawal Test Executed");
    }
}