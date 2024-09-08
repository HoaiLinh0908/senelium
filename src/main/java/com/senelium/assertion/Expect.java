package com.senelium.assertion;

import com.senelium.Senelium;
import com.senelium.element.Element;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Expect {
    private Expect() {
    }

    public static boolean toBeVisible(Element element) {
        return toBeVisible(element, -1);
    }

    public static boolean toBeVisible(Element element, long timeout) {
        try {
            getWaiter(timeout).until(ExpectedConditions.visibilityOfElementLocated(element.getLocator()));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public static boolean toBeInvisible(Element element) {
        return toBeInvisible(element, -1);
    }

    public static boolean toBeInvisible(Element element, long timeout) {
        try {
            getWaiter(timeout).until(ExpectedConditions.invisibilityOfElementLocated(element.getLocator()));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public static boolean toHaveText(Element element, String expectedText) {
        return toHaveText(element, expectedText, -1);
    }

    public static boolean toHaveText(Element element, String expectedText, long timeout) {
        try {
            getWaiter(timeout).until(ExpectedConditions.textToBe(element.getLocator(), expectedText));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    private static WebDriverWait getWaiter(long mil) {
        return mil < 0 ? Senelium.getDefaultWaiter() : Senelium.getWaiter(mil);
    }
}
