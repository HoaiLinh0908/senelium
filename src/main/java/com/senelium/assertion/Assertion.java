package com.senelium.assertion;

import com.senelium.Senelium;
import com.senelium.config.DriverConfig;
import com.senelium.element.Element;
import com.senelium.reports.AllureReport;
import lombok.Setter;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

@Setter
public class Assertion {
    private Element element;
    private boolean isSoft;
    private List<String> errors;

    public Assertion(boolean isSoft) {
        this.isSoft = isSoft;
        if (isSoft) {
            errors = new ArrayList<>();
        }
    }

    public Assertion expect(Element element) {
        this.element = element;
        return this;
    }

    public void assertAll() {
        if (isSoft && errors != null && !errors.isEmpty()) {
            throw new AssertionError(String.join("\n", errors));
        }
    }

    public void mergeAssert(Assertion assertion) {
        this.errors.addAll(assertion.errors);
    }

    public void toBeVisible() {
        toBeVisible("");
    }

    public void toBeVisible(String message) {
        toBeVisible(message, null);
    }

    public void toBeVisible(String message, Integer timeout) {
        try {
            getWaiter(timeout).until(ExpectedConditions.visibilityOfElementLocated(element.getLocator()));
        } catch (TimeoutException e) {
            handleFailedCheck(
                    "visible",
                    "invisible",
                    message + "\nElement [" + element.getLocator() + "] is expected to be visible but found invisible. ",
                    timeout);
        }
    }

    private <T> void handleFailedCheck(T expected, T actual, String message, Integer timeout) {
        String logMessage = composeElementMessage(expected, actual, message, timeout);
        AllureReport.takeScreenshot();
        if (isSoft) {
            errors.add(logMessage);
        } else {
            throw new AssertionError(logMessage);
        }
    }

    public void toBeInvisible() {
        toBeInvisible("");
    }

    public void toBeInvisible(String message) {
        toBeInVisible(message, null);
    }

    public void toBeInVisible(String message, Integer timeout) {
        try {
            getWaiter(timeout).until(ExpectedConditions.invisibilityOfElementLocated(element.getLocator()));
        } catch (TimeoutException e) {
            handleFailedCheck(
                    "invisible",
                    "visible",
                    message + "\nElement [" + element.getLocator() + "] is expected to be invisible but found visible. ",
                    timeout);
        }
    }

    public void toHaveText(String expectedText) {
        toHaveText(expectedText, "", null);
    }

    public void toHaveText(String expectedText, String message, Integer timeout) {
        try {
            //Get text already get the visible text
            getWaiter(timeout).until(ExpectedConditions.textToBe(element.getLocator(), expectedText));
        } catch (TimeoutException e) {
            handleFailedCheck(
                    expectedText,
                    element.getText(true),
                    message + "\nElement [" + element.getLocator() + "] is expected to be invisible but found visible. ",
                    timeout);
        }
    }

    public void imgToBeVisible() {
        this.imgToBeVisible("", null);
    }

    public void imgToBeVisible(String message, Integer timeout) {
        try {
            getWaiter(timeout).until(
                    ExpectedConditions.not(ExpectedConditions.domPropertyToBe(
                            element.findVisibleElement(),
                            "naturalWidth",
                            "0"))
            );
        } catch (TimeoutException e) {
            handleFailedCheck(
                    "visible",
                    "invisible or broken",
                    message + "\nImage element [" + element.getLocator() + "] is expected to be visible but found invisible or broken. ",
                    timeout);
        }
    }

    public void toBeSelected() {
        toBeSelected("", null);
    }

    public void toBeSelected(String message) {
        toBeSelected(message, null);
    }

    public void toBeSelected(Integer timeout) {
        toBeSelected("", timeout);
    }

    public void toBeSelected(String message, Integer timeout) {
        try {
            getWaiter(timeout).until(ExpectedConditions.elementToBeSelected(element.getLocator()));
        } catch (TimeoutException e) {
            handleFailedCheck(
                    "selected",
                    "unselected",
                    message + "\nElement [" + element.getLocator() + "] is expected to be selected but found unselected. ",
                    timeout);
        }
    }

    public void toBeUnselected() {
        toBeUnselected("", null);
    }

    public void toBeUnselected(String message) {
        toBeUnselected(message, null);
    }

    public void toBeUnselected(Integer timeout) {
        toBeUnselected("", timeout);
    }

    public void toBeUnselected(String message, Integer timeout) {
        try {
            getWaiter(timeout).until(ExpectedConditions.elementSelectionStateToBe(this.element.getLocator(), false));
        } catch (TimeoutException e) {
            handleFailedCheck(
                    "unselected",
                    "selected",
                    message + "\nElement [" + element.getLocator() + "] is expected to be unselected but found selected. ",
                    timeout);
        }
    }

    private static <T> String composeMessage(T expected, T actual, String message) {
        String logMsg = message == null ? "" : message;
        return logMsg + "\nExpected: " + expected + "\nActual:   " + actual;
    }

    private static <T> String composeElementMessage(T expected, T actual, String message, Integer timeout) {
        int to = timeout == null ? getDefaultTimeout() : timeout;
        return composeMessage(expected, actual, message + "Timeout " + to + " millisecond(s).");
    }

    private static int getDefaultTimeout() {
        return DriverConfig.getInstance().getTimeout().getElementWait();
    }

    private static WebDriverWait getWaiter(Integer mil) {
        return mil != null ? Senelium.getWaiter(mil) : Senelium.getDefaultWaiter();
    }
}
