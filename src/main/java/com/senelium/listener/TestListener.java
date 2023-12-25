package com.senelium.listener;

import com.aventstack.extentreports.Status;
import com.senelium.reports.Reporter;
import com.senelium.reports.allurereport.AllureManager;
import com.senelium.reports.extentreport.ExtentReportManager;
import com.senelium.reports.extentreport.ExtentTestManager;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.TestResult;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

@Slf4j
public class TestListener implements ITestListener, TestLifecycleListener {

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTestManager.createExtentTest(result.getName(), "Descriptions Here");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.logMessage(Status.PASS, result.getName() + " is passed.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Reporter.takeScreenshot();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTestManager.logMessage(Status.SKIP, result.getThrowable().toString());
    }

    @Override
    public void onFinish(ITestContext result) {
        ExtentReportManager.getExtentReports().flush();
    }

    @Override
    public void beforeTestStop(TestResult result) {
        if (result.getStatus() == io.qameta.allure.model.Status.FAILED || result.getStatus() == io.qameta.allure.model.Status.BROKEN) {
            AllureManager.addScreenshot();
        }
    }
}
