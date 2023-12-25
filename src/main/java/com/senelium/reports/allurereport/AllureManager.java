package com.senelium.reports.allurereport;

import com.senelium.Senelium;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class AllureManager {

    @Attachment(value = "{0}", type = "text/plain")
    public static String logMessage(String message) {
        return message;
    }

    @Attachment(value = "Test failed screenshot", type = "image/png")
    public static byte[] addScreenshot() {
        return ((TakesScreenshot) Senelium.getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
