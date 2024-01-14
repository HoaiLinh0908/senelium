package com.senelium.enums;

public enum HotelFilter {
    BREAKFAST_INCLUDED("Breakfast included"),
    SWIMMING_POOL("Swimming pool");

    final String locator;

    HotelFilter(String locator) {
        this.locator = locator;
    }

    public String getLocator() {
        return this.locator;
    }
}
