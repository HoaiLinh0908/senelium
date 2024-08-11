package com.senelium.pom.swaglabs;

import com.senelium.assertion.Expect;
import com.senelium.element.Element;

public class ProductsPage extends BasePage {
    public ProductsPage() {
        super();
    }

    @Override
    protected Element getTitleElement() {
        return Element.byXpath("//span[text()='Products']");
    }

    public void addProductToCart(String product) {
        Element locator = Element.byXpath(
                String.format("//div[a[div[text()='%s']]]/following-sibling::div/button[text()='Add to cart']", product)
        );
        locator.click();
    }

    public boolean isPageTitleDisplayed() {
        return Expect.toBeVisible(this.title);
    }
}
