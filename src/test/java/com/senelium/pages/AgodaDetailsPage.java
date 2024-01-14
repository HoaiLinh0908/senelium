package com.senelium.pages;

import com.senelium.element.Element;
import org.apache.commons.lang3.StringUtils;

public class AgodaDetailsPage extends BasePage {
    private final Element hotelName = Element.byCssSelector("[data-selenium='hotel-header-name']");
    private final Element address = Element.byCssSelector("[data-selenium='hotel-address-map']");
    private final Element breakfastIncluded = Element.byCssSelector("[data-selenium='RoomGridFilter-container'] [data-element-value='breakfast-include']");
    private final Element availableFacilities = Element.byCssSelector("[data-element-name='facility-highlights-item'] p");
    private final Element favoriteIcon = Element.byCssSelector("[data-testid='favorite-icon']");

    public String getHotelName() {
        return hotelName.getText();
    }

    public String getAddress() {
        return address.getText();
    }

    public boolean isBreakfastIncluded() {
        return breakfastIncluded.isDisplayed();
    }

    public boolean isSwimmingPoolContained() {
        return availableFacilities.getAllTexts().stream().anyMatch(s -> StringUtils.containsIgnoreCase(s, "Swimming pool"));
    }

    public void addHotelToFavorite() {
        favoriteIcon.scrollToView();
        favoriteIcon.click();
    }
}
