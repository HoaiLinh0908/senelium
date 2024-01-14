package com.senelium.pages;

import com.senelium.Senelium;
import com.senelium.element.Element;
import com.senelium.enums.HotelFilter;

import java.util.List;

public class AgodaSearchPage extends BasePage {

    private final Element hotelDestination = Element.byCssSelector("li[data-selenium='hotel-item'] span[data-selenium='area-city-text']");
    private final Element names = Element.byCssSelector("h3[data-selenium='hotel-name']");

    public boolean areResultsContainDestination(String expectedDestination, int limit) {
        int visibleResults = hotelDestination.countVisibleElements();

        //Handle lazy load
        if (visibleResults < limit) {
            Senelium.scrollToElement(hotelDestination.findElements().get(visibleResults - 1));
        }

        return hotelDestination
                .getAllTexts()
                .subList(0, limit)
                .stream()
                .allMatch(t -> t.contains(expectedDestination));
    }

    public void filterResultsBy(HotelFilter filter) {
        Element filterElement = Element.byCssSelector("#SideBarLocationFilters span[aria-label='%s']", filter.getLocator());
        filterElement.scrollToView();
        filterElement.click();
    }

    public List<String> getHotelNames() {
        return names.getAllTexts();
    }

    public void openHotel(String name) {
        Element element = Element.byCssSelector("a[aria-label*=\"%s\"]", name);
        element.click();
        Senelium.closeCurrentAndSwitchToNewTab();
    }
}
