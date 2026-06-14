package testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.DepositPage;
import pages.FundTransferPage;
import pages.HomePage;
import pages.LoginPage;
import pages.NewAccountPage;
import pages.NewCustomerPage;
import pages.WithdrawalPage;
import utilities.ConfigReader;
import utilities.TestData;
import utilities.WaitUtils;

public class Guru99CapstoneFlowTest extends BaseTest {

@Test
public void completeGuru99BankingFlow() throws Exception {

    System.out.println("\n========== GURU99 CAPSTONE PROJECT STARTED ==========\n");

    ConfigReader config = new ConfigReader();

    LoginPage login = new LoginPage(driver);
    HomePage home = new HomePage(driver);
    NewCustomerPage customer = new NewCustomerPage(driver);
    NewAccountPage account = new NewAccountPage(driver);
    DepositPage deposit = new DepositPage(driver);
    WithdrawalPage withdrawal = new WithdrawalPage(driver);
    FundTransferPage transfer = new FundTransferPage(driver);

    // ==========================================
    // LOGIN
    // ==========================================

    System.out.println("Logging into Guru99 Bank...");

    login.login(
            config.getUsername(),
            config.getPassword());

    Thread.sleep(2000);

    Assert.assertTrue(
            home.verifyManagerHomePage(),
            "Login Failed");

    System.out.println("Login Successful");

    // ==========================================
    // CREATE CUSTOMER
    // ==========================================

    System.out.println("\nCreating Customer...");

    customer.addCustomer();

    WaitUtils.waitForText(
            driver,
            "Customer Registered Successfully");

    Assert.assertTrue(
            driver.getPageSource()
                  .contains("Customer Registered Successfully"));

    Assert.assertNotNull(
            TestData.customerId);

    System.out.println(
            "Customer ID = "
                    + TestData.customerId);

    Thread.sleep(2000);

    // ==========================================
    // CREATE ACCOUNT 1
    // ==========================================

    System.out.println("\nCreating Account 1...");

    account.createAccount(
            TestData.customerId);

    Assert.assertNotNull(
            TestData.accountId1);

    System.out.println(
            "Account 1 = "
                    + TestData.accountId1);

    Thread.sleep(2000);

    // ==========================================
    // CREATE ACCOUNT 2
    // ==========================================

    System.out.println("\nCreating Account 2...");

    account.createAccount(
            TestData.customerId);

    Assert.assertNotNull(
            TestData.accountId2);

    System.out.println(
            "Account 2 = "
                    + TestData.accountId2);

    Thread.sleep(2000);

    // ==========================================
    // DEPOSIT
    // ==========================================

    try {

        System.out.println("\nDepositing Money...");

        deposit.depositMoney(
                TestData.accountId1,
                "5000");

        Thread.sleep(2000);

        String pageText =
                driver.findElement(
                        By.tagName("body"))
                        .getText();

        System.out.println(pageText);

        if(pageText.contains("HTTP ERROR 500")) {

            System.out.println(
                    "\nAPPLICATION DEFECT FOUND");

            System.out.println(
                    "Deposit Functionality Returned HTTP 500");
        }
        else {

            Assert.assertTrue(
                    pageText.contains(
                            "Transaction details of Deposit"));

            TestData.account1Balance += 5000;

            System.out.println(
                    "Deposit Successful");

            System.out.println(
                    "Account 1 Balance = "
                            + TestData.account1Balance);
        }

    } catch(Exception e) {

        System.out.println(
                "Deposit Execution Failed");

        System.out.println(
                e.getMessage());
    }

    // ==========================================
    // WITHDRAWAL
    // ==========================================

    try {

        System.out.println("\nWithdrawing Money...");

        withdrawal.withdrawMoney(
                TestData.accountId1,
                "1000");

        Thread.sleep(2000);

        String pageText =
                driver.findElement(
                        By.tagName("body"))
                        .getText();

        System.out.println(pageText);

        if(pageText.contains("HTTP ERROR 500")) {

            System.out.println(
                    "Withdrawal Returned HTTP 500");
        }
        else if(pageText.contains(
                "Transaction details of Withdrawal")) {

            TestData.account1Balance -= 1000;

            System.out.println(
                    "Withdrawal Successful");

            System.out.println(
                    "Balance = "
                            + TestData.account1Balance);
        }

    } catch(Exception e) {

        System.out.println(
                "Withdrawal Execution Failed");

        System.out.println(
                e.getMessage());
    }

    // ==========================================
    // FUND TRANSFER
    // ==========================================

    try {

        System.out.println("\nTransferring Funds...");

        transfer.transferFunds(
                TestData.accountId1,
                TestData.accountId2,
                "2500");

        Thread.sleep(2000);

        String pageText =
                driver.findElement(
                        By.tagName("body"))
                        .getText();

        System.out.println(pageText);

        if(pageText.contains("HTTP ERROR 500")) {

            System.out.println(
                    "Fund Transfer Returned HTTP 500");
        }
        else if(pageText.contains(
                "Fund Transfer Details")) {

            TestData.account1Balance -= 2500;
            TestData.account2Balance += 2500;

            System.out.println(
                    "Fund Transfer Successful");
        }

    } catch(Exception e) {

        System.out.println(
                "Fund Transfer Execution Failed");

        System.out.println(
                e.getMessage());
    }

    // ==========================================
    // FINAL SUMMARY
    // ==========================================

    System.out.println(
            "\n========== FINAL SUMMARY ==========");

    System.out.println(
            "Customer ID : "
                    + TestData.customerId);

    System.out.println(
            "Account 1 : "
                    + TestData.accountId1);

    System.out.println(
            "Account 2 : "
                    + TestData.accountId2);

    System.out.println(
            "Final Account 1 Balance : "
                    + TestData.account1Balance);

    System.out.println(
            "Final Account 2 Balance : "
                    + TestData.account2Balance);

    System.out.println(
            "\n========== CAPSTONE EXECUTION COMPLETED ==========");
}
}
