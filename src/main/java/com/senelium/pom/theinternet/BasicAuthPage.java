package com.senelium.pom.theinternet;

import com.senelium.Senelium;
import com.senelium.assertion.SeAssert;
import com.senelium.config.TestConfig;
import com.senelium.element.Element;

public class BasicAuthPage {
    private final Element congratulationText;

    public BasicAuthPage() {
        congratulationText = Element.byXpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]");
    }

    public void signIn(String username, String password) {
        if (!congratulationText.isExisted()) {
            String url = TestConfig.getInstance().getTheInternetUrl();
            Senelium.open(url + "/basic_auth", username, password);
        }
    }

    public void shouldBasicAuthCongratulationDisplay() {
        SeAssert.expect(congratulationText).toBeVisible("The Basic auth congratulation is not displayed.");
    }
}
