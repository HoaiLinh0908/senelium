package com.senelium.pages;

import com.senelium.Senelium;
import com.senelium.element.Element;

public class AgodaLoginPage {

    Element emailInput = Element.byCssSelector("input#email");
    Element passwordInput = Element.byCssSelector("input#password");
    Element signInButton = Element.byCssSelector("button[data-element-name='universal-login-signin-email-button']");

    public void login(String email, String password) {
        Senelium.switchToFrame(0);
        emailInput.type(email);
        passwordInput.type(password);
        signInButton.click();
        Senelium.switchToMainWindow();
    }

    public boolean loginPageIsDisplayed() {
        Senelium.switchToFrame(0);
        boolean result = signInButton.isDisplayed();
        Senelium.switchToMainWindow();
        return result;
    }
}
