package com.senelium.pom.swaglabs;

import com.senelium.element.Element;

public class LoginPage {
    private Element usernameTxt = Element.byId("user-name");
    private Element passwordTxt = Element.byId("password");
    private Element loginBtn = Element.byId("login-button");

    public void login(String username, String password) {
        usernameTxt.type(username);
        passwordTxt.type(password);
        loginBtn.click();
    }
}
