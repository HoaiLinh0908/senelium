package com.senelium.test;

import com.senelium.Senelium;
import com.senelium.assertion.TestAssert;
import com.senelium.enums.HotelFilter;
import com.senelium.models.SearchedHotel;
import com.senelium.pages.AgodaDetailsPage;
import com.senelium.pages.AgodaHomePage;
import com.senelium.pages.AgodaSearchPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class AgodaFirstTest extends TestBase {

    final AgodaHomePage homePage = new AgodaHomePage();
    final AgodaDetailsPage detailPage = new AgodaDetailsPage();
    final AgodaSearchPage searchPage = new AgodaSearchPage();

    SearchedHotel hotel = new SearchedHotel();
    final String destination = "Da Lat";

    @BeforeClass
    void beforeClass() {
        hotel = new SearchedHotel();
        hotel.setDestination(destination);
        LocalDate checkInDate = LocalDate.now().plusDays(1);
        LocalDate checkOutDate = checkInDate.plusDays(3);
        hotel.setCheckInDate(checkInDate);
        hotel.setCheckOutDate(checkOutDate);
        hotel.setOccupancy(new SearchedHotel.Occupancy(1, 2, 0));
    }

    @Test(description = "Agoda first test")
    void agodaFirstTest() {
        Senelium.open("https://www.agoda.com/");

        homePage.searchHotel(hotel);
        searchPage.waitUntilLoadingIconDisappeared();
        TestAssert testAssert = new TestAssert();
        testAssert.assertTrue(searchPage.areResultsContainDestination(destination, 5),
                "Results do not contain destination " + destination);

        searchPage.filterResultsBy(HotelFilter.BREAKFAST_INCLUDED);
        String firstHotel = searchPage.getHotelNames().get(0);

        searchPage.openHotel(firstHotel);
        testAssert.assertEqual(detailPage.getHotelName(), firstHotel, "Hotel name does not match");
        testAssert.assertTrue(detailPage.getAddress().contains(destination), "Hotel address does not contains destination " + destination);
        testAssert.assertTrue(detailPage.isBreakfastIncluded(), "Breakfast is not included");

        testAssert.assertAll();
    }
}
