package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {

            ExtentSparkReporter spark =
                    new ExtentSparkReporter(
                            "ExtentReports/ExtentReport.html");

            spark.config()
                    .setReportName(
                            "Guru99 Bank Automation Report");

            spark.config()
                    .setDocumentTitle(
                            "Execution Report");

            extent = new ExtentReports();

            extent.attachReporter(spark);

            extent.setSystemInfo(
                    "Tester",
                    "Shiv Dayal Singh");
        }

        return extent;
    }
}