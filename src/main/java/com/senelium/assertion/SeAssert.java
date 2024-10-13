package com.senelium.assertion;

import com.senelium.element.Element;

public class SeAssert {
    private static final ThreadLocal<Assertion> threadAssert = new ThreadLocal<>();

    public static Assertion expect(Element element) {
        if (threadAssert.get() == null) {
            threadAssert.set(new Assertion(false));
        }
        threadAssert.get().setElement(element);
        return threadAssert.get();
    }

    public static Assertion softAssert() {
        return new Assertion(true);
    }
}
