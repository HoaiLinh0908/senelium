package com.senelium.reports;

import com.senelium.reports.allurereport.AllureManager;
import com.senelium.reports.extentreport.ExtentTestManager;

public class Reporter {

    private Reporter() {
    }

    public static void takeScreenshot() {
        AllureManager.addScreenshot();
        ExtentTestManager.addScreenShot();
    }

    public static void logMessage(String message) {
        AllureManager.logMessage(message);
        ExtentTestManager.logMessage(message);
    }
}
