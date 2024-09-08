package com.senelium.assertion;

import com.senelium.reports.AllureReport;
import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;
import org.testng.collections.Maps;

import java.util.Map;

public class SenAssertion {

    public static void assertTrue(boolean checkpoint, String message) {
        if (!checkpoint) {
            AllureReport.takeScreenshot();
            //TODO: Improve message
            throw new AssertionError(message);
        }
    }

    public static void assertFalse(boolean checkpoint, String message) {
        if (checkpoint) {
            AllureReport.takeScreenshot();
            //TODO: Improve message
            throw new AssertionError(message);
        }
    }

    //TODO: implement assertEquals()

    public static SenSoftAssertion softAssertion() {
        return new SenSoftAssertion();
    }

    // This class is almost the same as SoftAssert but with implementation of method 'onAssertFailure()'
    public static class SenSoftAssertion extends Assertion {
        private final Map<AssertionError, IAssert<?>> errors = Maps.newLinkedHashMap();
        private static final String DEFAULT_SOFT_ASSERT_MESSAGE = "The following asserts failed:";

        @Override
        protected void doAssert(IAssert<?> a) {
            onBeforeAssert(a);
            try {
                a.doAssert();
                onAssertSuccess(a);
            } catch (AssertionError ex) {
                onAssertFailure(a, ex);
                errors.put(ex, a);
            } finally {
                onAfterAssert(a);
            }
        }

        @Override
        public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
            AllureReport.takeScreenshot();
        }

        public void assertAll() {
            assertAll(null);
        }

        public void assertAll(String message) {
            if (!errors.isEmpty()) {
                StringBuilder sb = new StringBuilder(null == message ? DEFAULT_SOFT_ASSERT_MESSAGE : message);
                boolean first = true;
                for (AssertionError error : errors.keySet()) {
                    if (first) {
                        first = false;
                    } else {
                        sb.append(",");
                    }
                    sb.append("\n\t");
                    sb.append(getErrorDetails(error));
                }
                throw new AssertionError(sb.toString());
            }
        }
    }
}
