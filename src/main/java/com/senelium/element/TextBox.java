package com.senelium.element;

public class TextBox extends BaseElement {
    @Override
    public void click() {
        findElement().click();
    }

    @Override
    public void type(String key) {
        findElement().sendKeys(key);
    }
}
