package com.senelium.reports.extentreport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports getExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("target/extent-report/extent-report.html");
        reporter.config().setReportName("Senelium 3");
        extentReports.attachReporter(reporter);
        return extentReports;
    }
}
