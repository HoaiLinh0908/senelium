package com.senelium.test;

import com.senelium.Senelium;
import com.senelium.assertion.TestAssert;
import com.senelium.enums.HotelFilter;
import com.senelium.models.SearchedHotel;
import com.senelium.pages.*;
import com.senelium.utils.Constants;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class AgodaSecondTest extends TestBase {
    final AgodaHomePage homePage = new AgodaHomePage();
    final AgodaDetailsPage detailPage = new AgodaDetailsPage();
    final AgodaSearchPage searchPage = new AgodaSearchPage();
    final AgodaLoginPage loginPage = new AgodaLoginPage();
    final AgodaFavoritePage favoritePage = new AgodaFavoritePage();

    SearchedHotel hotel = new SearchedHotel();
    final String destination = "Da Lat";
    LocalDate checkInDate;
    LocalDate checkOutDate;

    @BeforeClass
    void beforeClass() {
        hotel = new SearchedHotel();
        hotel.setDestination(destination);
        checkInDate = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        checkOutDate = checkInDate.plusDays(3);
        hotel.setCheckInDate(checkInDate);
        hotel.setCheckOutDate(checkOutDate);
        hotel.setOccupancy(new SearchedHotel.Occupancy(2, 4, 0));
    }

    @Test(description = "Agoda second test")
    void agodaSecondTest() {
        Senelium.open("https://www.agoda.com/");

        homePage.searchHotel(hotel);
        searchPage.waitUntilLoadingIconDisappeared();
        TestAssert testAssert = new TestAssert();
        testAssert.assertTrue(searchPage.areResultsContainDestination(destination, 5),
                "Results do not contain destination " + destination);

        searchPage.filterResultsBy(HotelFilter.SWIMMING_POOL);
        String firstHotel = searchPage.getHotelNames().get(0);
        searchPage.openHotel(firstHotel);

        testAssert.assertEqual(detailPage.getHotelName(), firstHotel, "Hotel name does not match");
        testAssert.assertTrue(detailPage.getAddress().contains(destination), "Hotel address does not contains destination " + destination);
        testAssert.assertTrue(detailPage.isSwimmingPoolContained(), "This hotel does not have swimming pool");

        detailPage.addHotelToFavorite();
        testAssert.assertTrue(loginPage.loginPageIsDisplayed(), "Login page is not displayed");

        loginPage.login(Constants.SAMPLE_EMAIL, Constants.SAMPLE_PASSWORD);

        detailPage.addHotelToFavorite();
        detailPage.openFavoritePage();
        favoritePage.openFavoriteGroup();

        String strCheckIn = checkInDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        String strCheckOut = checkOutDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));

        testAssert.assertEqual(favoritePage.getCheckInDate(), strCheckIn, "Check in date is not correct");
        testAssert.assertEqual(favoritePage.getCheckOutDate(), strCheckOut, "Check out date is not correct");
        testAssert.assertTrue(favoritePage.getFavoriteCardNames().contains(firstHotel), "Hotel is not added to Favorite");

        testAssert.assertAll();
    }
}
