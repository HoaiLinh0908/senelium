package com.senelium.pom.theinternet;

import com.senelium.assertion.SeAssert;
import com.senelium.element.Element;

public class BrokenImagesPage {
    public void shouldImageNotBroken(String src) {
        Element img = Element.byCssSelector("img[src=\"%s\"]", src);
        SeAssert.expect(img).imgToBeVisible();
    }
}
