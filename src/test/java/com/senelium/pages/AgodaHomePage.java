package com.senelium.pages;

import com.senelium.Senelium;
import com.senelium.element.Element;
import com.senelium.models.SearchedHotel;
import io.qameta.allure.Step;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AgodaHomePage extends BasePage {

    private final Element searchBar = Element.byCssSelector("div[class*='Searchbox'] input");
    private final Element checkInBox = Element.byId("check-in-box");
    private final Element roomsEle = Element.byCssSelector("[data-selenium='occupancyRooms'] h3");
    private final Element adultsEle = Element.byCssSelector("[data-selenium='occupancyAdults'] h3");
    private final Element childrenEle = Element.byCssSelector("[data-selenium='occupancyChildren'] h3");
    private final Element searchButton = Element.byCssSelector("[data-selenium='searchButton']");

    private final Element loadingIcon = Element.byCssSelector("div.ModalLoadingSpinner__content--icon");

    public void searchHotel(SearchedHotel hotel) {
        typeDestination(hotel.getDestination());
        selectDate(hotel.getCheckInDate(), hotel.getCheckOutDate());
        setOccupancy(hotel.getOccupancy());
        clickSearch();
        Senelium.closeCurrentAndSwitchToNewTab();
    }

    @Step("Search {0}")
    public void typeDestination(String dest) {
        searchBar.type(dest);
        Element suggestion = Element.byCssSelector("li[data-selenium=\"autosuggest-item\"]");
        suggestion.waitUntilDisplayed();
        searchBar.pressEsc();
    }

    public void selectDate(LocalDate checkIn, LocalDate checkOut) {
        String strCheckIn = checkIn.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String strCheckOut = checkOut.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));

        Element elementCheckIn = Element.byCssSelector(String.format("div[aria-label*='%s']", strCheckIn));
        Element elementCheckOut = Element.byCssSelector(String.format("div[aria-label*='%s']", strCheckOut));

        checkInBox.click();
        elementCheckIn.click();
        elementCheckOut.click();
    }

    public void setOccupancy(SearchedHotel.Occupancy occupancy) {
        if (!roomsEle.getText().equals(String.valueOf(occupancy.getRooms()))) {
            setOccupancy(occupancy.getRooms(), "occupancyRooms", roomsEle);
        }

        if (!adultsEle.getText().equals(String.valueOf(occupancy.getAdults()))) {
            setOccupancy(occupancy.getAdults(), "occupancyAdults", adultsEle);
        }

        if (!childrenEle.getText().equals(String.valueOf(occupancy.getChildren()))) {
            setOccupancy(occupancy.getChildren(), "occupancyChildren", childrenEle);
        }
    }

    private void setOccupancy(int expect, String type, Element setElement) {
        int current = Integer.parseInt(setElement.getText());

        if (current < expect) {
            Element plusElement = Element.byCssSelector("[data-selenium='%s'] button[data-selenium='plus']", type);
            while (!setElement.getText().equals(String.valueOf(expect))) {
                plusElement.click();
                setElement.waitUntilTextChangedTo(String.valueOf(++current));
            }
        } else if (current > expect) {
            Element minusElement = Element.byCssSelector("[data-selenium='%s'] button[data-selenium='minus']", type);
            while (!setElement.getText().equals(String.valueOf(expect))) {
                minusElement.click();
                setElement.waitUntilTextChangedTo(String.valueOf(++current));
            }
        }
    }

    public void clickSearch() {
        searchButton.click();
    }
}
