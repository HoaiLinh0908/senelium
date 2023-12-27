package com.senelium.assertion;

import com.senelium.reports.AllureReport;

import java.util.ArrayList;
import java.util.List;

public class TestAssert {
    private final List<String> errors = new ArrayList<>();

    public void assertTrue(boolean checkpoint, String message) {
        if (!checkpoint) {
            AllureReport.takeScreenshot();
            errors.add(message);
        }
    }

    //The object type must implement equals() and toString() appropriately
    public <T> void assertEqual(T actual, T expected, String message) {
        if (actual == null || !actual.equals(expected)) {
            AllureReport.takeScreenshot();
            errors.add(message + " - Expect " + expected + " but actual is " + actual);
        }
    }

    public boolean getResult() {
        return errors.isEmpty();
    }

    public String getErrors() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < errors.size(); i++) {
            builder.append(i + 1).append(". ").append(errors.get(i)).append(" ");
        }
        return builder.toString();
    }
}
