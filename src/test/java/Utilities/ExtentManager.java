package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extent;
    private static ExtentSparkReporter reporter;

    public static ExtentReports createExtentInstance(String fileName) {
        reporter = new ExtentSparkReporter(fileName);

        reporter.config().setEncoding("utf-8");
        reporter.config().setDocumentTitle(fileName);
        reporter.config().setReportName(fileName);
        reporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();

        extent.attachReporter(reporter);
        extent.setSystemInfo("Automation Tester", "Berat Sönmez");
        extent.setSystemInfo("Organization", "Orion");
        extent.setSystemInfo("Build No", "123123123");

        return extent;
    }
}
