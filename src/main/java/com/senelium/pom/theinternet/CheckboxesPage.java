package com.senelium.pom.theinternet;

import com.senelium.assertion.SeAssert;
import com.senelium.element.Element;

public class CheckboxesPage {
    private Element firstCheckbox;
    private Element secondCheckbox;

    public CheckboxesPage() {
        firstCheckbox = Element.byCssSelector("input:first-of-type");
        secondCheckbox = Element.byCssSelector("input:nth-of-type(2)");
    }

    public void setFirstCheckbox(boolean status) {
        this.setCheckbox(firstCheckbox, status);
    }

    public void setSecondCheckbox(boolean status) {
        this.setCheckbox(secondCheckbox, status);
    }

    private void setCheckbox(Element checkbox, boolean status) {
        boolean checkboxStatus = checkbox.isSelected();
        if (checkboxStatus != status) {
            checkbox.click();
        }
    }

    public void shouldFirstCheckboxStatusCorrect(boolean status) {
        this.shouldCheckboxStatusCorrect(firstCheckbox, status, "The first checkbox is " + (status ? "unselected." : "selected."));
    }

    public void shouldSecondCheckboxStatusCorrect(boolean status) {
        this.shouldCheckboxStatusCorrect(secondCheckbox, status, "The second checkbox is " + (status ? "unselected." : "selected."));
    }

    private void shouldCheckboxStatusCorrect(Element checkbox, boolean status, String message) {
        if (status) {
            SeAssert.expect(checkbox).toBeSelected(message);
        } else {
            SeAssert.expect(checkbox).toBeUnselected(message);
        }
    }
}
