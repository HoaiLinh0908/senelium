package com.senelium.assertion;

import com.senelium.Senelium;
import com.senelium.element.Element;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Expect {
    private Expect() {
    }

    public static boolean toBeVisible(Element element) {
        try {
            getWaiter().until(ExpectedConditions.visibilityOfElementLocated(element.getLocator()));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public static boolean toBeVisible(Element element, long timeout) {
        try {
            getWaiter(timeout).until(ExpectedConditions.visibilityOfElementLocated(element.getLocator()));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public static boolean toBeNotVisible(Element element) {
        try {
            getWaiter().until(ExpectedConditions.invisibilityOfElementLocated(element.getLocator()));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public static boolean toBeNotVisible(Element element, long timeout) {
        try {
            getWaiter(timeout).until(ExpectedConditions.invisibilityOfElementLocated(element.getLocator()));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    private static WebDriverWait getWaiter() {
        return Senelium.getDefaultWaiter();
    }

    private static WebDriverWait getWaiter(long millis) {
        return Senelium.getSeneDriver().getWaiter(Duration.ofMillis(millis));
    }
}
