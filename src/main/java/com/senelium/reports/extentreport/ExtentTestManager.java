package com.senelium.reports.extentreport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.senelium.Senelium;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ExtentTestManager {
    static ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();
    static ExtentReports extent = ExtentReportManager.getExtentReports();

    public static ExtentTest getTest() {
        return extentTestThreadLocal.get();
    }

    public static synchronized void createExtentTest(String testName, String desc) {
        ExtentTest test = extent.createTest(testName, desc);
        extentTestThreadLocal.set(test);
    }

    public static void addScreenShot() {
        addScreenShot(Status.INFO, "Failed test screenshot");
    }

    public static void addScreenShot(Status status, String message) {
        String base64Image = "data:image/png;base64," + ((TakesScreenshot) Senelium.getSeneDriver().getDriver()).getScreenshotAs(OutputType.BASE64);

        getTest().log(status, message, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Image).build());
    }

    public static void logMessage(String message) {
        getTest().log(Status.INFO, message);
    }

    public static void logMessage(Status status, String message) {
        getTest().log(status, message);
    }
}

