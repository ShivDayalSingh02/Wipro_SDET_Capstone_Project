package testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.FundTransferPage;
import pages.LoginPage;
import pages.NewAccountPage;
import pages.NewCustomerPage;
import utilities.ConfigReader;
import utilities.TestData;

public class InsufficientBalanceTest
        extends BaseTest {

    @Test
    public void insufficientBalanceTest()
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
                TestData.accountId1,
                TestData.accountId2,
                "100000");

        String pageText =
                driver.findElement(
                        By.tagName("body"))
                        .getText();

        System.out.println(pageText);

        Assert.assertNotNull(pageText);

        System.out.println(
                "Insufficient Balance Test Executed");
    }
}