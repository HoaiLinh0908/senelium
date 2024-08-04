package com.senelium.pom.swaglabs;

import com.senelium.element.Element;

public abstract class BasePage {
    protected Element cartIcon;
    protected Element title;

    public BasePage() {
        cartIcon = Element.byClass("a[data-test='shopping-cart-link']");
        title = this.getTitleElement();
    }

    protected abstract Element getTitleElement();
}
