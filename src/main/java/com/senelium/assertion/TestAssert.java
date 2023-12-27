package com.senelium.assertion;

import com.senelium.reports.AllureReport;

import java.util.ArrayList;
import java.util.List;

public class TestAssert {
    private List<String> errors = new ArrayList<>();

    public void checkTrue(boolean checkpoint, String message) {
        if (!checkpoint) {
            AllureReport.takeScreenshot();
            errors.add(message);
        }
    }

    public <T> void checkEqual(T actual, T expected, String message) {
        if (!actual.equals(expected)) {
            AllureReport.takeScreenshot();
            errors.add(message);
        }
    }

    public boolean isPassed() {
        return errors.isEmpty();
    }

    public String getErrors() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < errors.size(); i++) {
            builder.append(i + 1).append(". ").append(errors.get(i)).append("; ");
        }
        return builder.toString();
    }
}
