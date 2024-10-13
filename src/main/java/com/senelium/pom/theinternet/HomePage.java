package com.senelium.pom.theinternet;

import com.senelium.element.Element;

public class HomePage {

    public void openPage(String name) {
        Element.byXpath(String.format("//li/a[text()=\"%s\"]", name)).click();
        Element.byXpath("//h1[text()='Welcome to the-internet']").waitForInvisible();
    }
}
