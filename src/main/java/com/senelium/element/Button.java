package com.senelium.element;

public class Button extends BaseElement {
    @Override
    public void click() {
        findElement().click();
    }

    @Override
    public void type(String key) {
        throw new UnsupportedOperationException("Type action is not support for button!");
    }
}
