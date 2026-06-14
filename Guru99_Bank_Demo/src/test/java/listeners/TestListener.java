package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import base.BaseTest;
import utilities.ExtentManager;
import utilities.ScreenshotUtil;

public class TestListener implements ITestListener {

    ExtentReports extent =
            ExtentManager.getInstance();

    ExtentTest test;

    @Override
    public void onTestStart(
            ITestResult result) {

        test = extent.createTest(
                result.getName());

        System.out.println(
                "STARTED : "
                        + result.getName());
    }

    @Override
    public void onTestSuccess(
            ITestResult result) {

        test.pass("Test Passed");

        System.out.println(
                "PASSED : "
                        + result.getName());
    }

    @Override
    public void onTestFailure(
            ITestResult result) {

        test.fail(
                result.getThrowable());

        try {

            if (BaseTest.driver != null) {

                ScreenshotUtil.captureScreenshot(
                        BaseTest.driver,
                        result.getName());
            }

        } catch (Exception e) {

            System.out.println(
                    "Screenshot capture failed : "
                            + e.getMessage());
        }

        System.out.println(
                "FAILED : "
                        + result.getName());

        if (result.getThrowable() != null) {

            System.out.println(
                    "Exception Details:");

            result.getThrowable()
                  .printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(
            ITestResult result) {

        test.skip("Test Skipped");

        System.out.println(
                "SKIPPED : "
                        + result.getName());
    }

    @Override
    public void onStart(
            ITestContext context) {

        System.out.println(
                "Execution Started");
    }

    @Override
    public void onFinish(
            ITestContext context) {

        extent.flush();

        System.out.println(
                "Execution Completed");
    }
}