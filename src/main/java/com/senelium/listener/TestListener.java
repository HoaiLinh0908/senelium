package com.senelium.listener;

import com.aventstack.extentreports.Status;
import com.senelium.reports.ExtentTestReport;
import io.qameta.allure.listener.TestLifecycleListener;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

@Slf4j
public class TestListener implements ITestListener, TestLifecycleListener {

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTestReport.createTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestReport.logMessage(Status.PASS, "Pass test");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTestReport.logMessage(Status.FAIL, "Failed test");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTestReport.logMessage(Status.SKIP, "Skip test");
    }

    @Override
    public void onFinish(ITestContext result) {
        ExtentTestReport.getExtentReports().flush();
    }

}
