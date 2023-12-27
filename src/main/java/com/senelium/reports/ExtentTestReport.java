package com.senelium.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.senelium.Senelium;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ExtentTestReport {
    static ExtentReports extentReports = createExtentReport();
    static ThreadLocal<ExtentTest> testThreadLocal = new ThreadLocal<>();

    private static ExtentReports createExtentReport() {
        ExtentReports extentReports = new ExtentReports();
        ExtentSparkReporter reporter = new ExtentSparkReporter("target/extent-report/extent-report.html");
        reporter.config().setReportName("Senelium 3");
        extentReports.attachReporter(reporter);
        return extentReports;
    }

    public static ExtentReports getExtentReports() {
        return extentReports;
    }

    public static void createTest(String testName) {
        testThreadLocal.set(extentReports.createTest(testName));
    }

    public static ExtentTest getTest() {
        return testThreadLocal.get();
    }

    public static void takeScreenshot(String message) {
        String base64Image = "data:image/png;base64," + ((TakesScreenshot) Senelium.getDriver()).getScreenshotAs(OutputType.BASE64);
        getTest().info(message, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Image).build());
    }

    public static void takeScreenshot() {
        takeScreenshot("Failed test screenshot");
    }

    public static void logMessage(String message) {
        getTest().info(message);
    }

    public static void logMessage(Status status, String message) {
        getTest().log(status, message);
    }
}

