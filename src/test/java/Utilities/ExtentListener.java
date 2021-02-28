package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Date;

public class ExtentListener implements ITestListener {
    static Date d = new Date();
    static String fileName = "Extent" + d.toString().replace("TRT ", "").replace(" ", "-") + ".html";

    private static ExtentReports extent = ExtentManager.createExtentInstance("./reports/" + fileName);
    public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();

    public void onTestStart(ITestResult result) {

        String testName = result.getTestClass().getName() + " @TestCase : " + result.getMethod().getMethodName();
        ExtentTest test = extent.createTest(testName);
        testReport.set(test);

    }

    public void onTestSuccess(ITestResult result) {

        String methodName = result.getMethod().getMethodName();
        String logText = "<b>" + "Test Case: " + methodName + " passed. </b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
        testReport.get().pass(m);
    }

    public void onTestFailure(ITestResult result) {

        String methodName = result.getMethod().getMethodName();
        String logText = "<b>" + "Test Case: " + methodName + " failed . </b>";
        testReport.get().fail(result.getThrowable());
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);

        testReport.get().fail(m);

    }

    public void onTestSkipped(ITestResult result) {

        String methodName = result.getMethod().getMethodName();
        String logText = "<b>" + "Test Case: " + methodName + " skipped . </b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
        testReport.get().skip(m);
    }

    public void onFinish(ITestContext context) {
        if (extent != null)
            extent.flush();
    }
}
