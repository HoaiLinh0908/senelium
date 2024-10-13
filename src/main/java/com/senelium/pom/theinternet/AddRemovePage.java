package com.senelium.pom.theinternet;

import com.senelium.assertion.SeAssert;
import com.senelium.element.Element;

public class AddRemovePage {
    private final Element addElementButton;
    private final Element deleteButton;

    public AddRemovePage() {
        addElementButton = Element.byXpath("//button[text()='Add Element']");
        deleteButton = Element.byXpath("//button[text()='Delete']");
    }

    public void clickAddElementButton() {
        this.addElementButton.click();
    }

    public void clickDeleteButton() {
        this.deleteButton.click();
    }

    public void shouldDeleteButtonVisible() {
        SeAssert.expect(this.deleteButton).toBeVisible();
    }

    public void shouldDeleteButtonNotVisible() {
        SeAssert.expect(this.deleteButton).toBeInvisible();
    }
}
