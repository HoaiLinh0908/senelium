package com.senelium.pages;

import com.senelium.element.Element;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BasePage {

    private final Element loadingIcon = Element.byCssSelector("div.ModalLoadingSpinner__content--icon");
    private final Element avatarIcon = Element.byCssSelector("[data-element-name='user-avatar']");
    private final Element savedPropertiesList = Element.byCssSelector("[data-element-name='favorite-menu']");

    public void waitUntilLoadingIconDisappeared() {
        loadingIcon.waitUntilNotDisplayed();
    }

    public void openFavoritePage() {
        avatarIcon.click();
        savedPropertiesList.click();
    }
}
