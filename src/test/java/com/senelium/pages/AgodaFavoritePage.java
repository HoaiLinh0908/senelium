package com.senelium.pages;

import com.senelium.element.Element;

import java.util.List;

public class AgodaFavoritePage extends BasePage {
    Element favoriteGroup = Element.byCssSelector("[data-selenium='favorite-group-card']");
    Element favoriteCardName = Element.byCssSelector("[data-selenium='favorite-property-card'] a div:has(+p)");
    Element checkInDate = Element.byCssSelector("[data-selenium=\"checkInText\"]");
    Element checkOutDate = Element.byCssSelector("[data-selenium=\"checkOutText\"]");

    public void openFavoriteGroup() {
        favoriteGroup.click();
    }

    public List<String> getFavoriteCardNames() {
        return favoriteCardName.getAllTexts();
    }

    public String getCheckInDate() {
        return checkInDate.getText();
    }

    public String getCheckOutDate() {
        return checkOutDate.getText();
    }
}
