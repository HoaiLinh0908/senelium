package com.senelium.test;

import com.senelium.Senelium;
import com.senelium.assertion.TestAssert;
import com.senelium.models.SearchedHotel;
import com.senelium.pages.AgodaPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class AgodaFirstTest extends TestBase {

    AgodaPage homePage = new AgodaPage();
    final String destination = "Da Lat";

    @BeforeClass
    void beforeClass() {
        Senelium.open("https://www.agoda.com/");
    }

    @Test(description = "Agoda first test")
    void agodaFirstTest() {
        SearchedHotel hotel = new SearchedHotel();
        hotel.setDestination(destination);
        LocalDate checkInDate = LocalDate.now().plusDays(1);
        LocalDate checkOutDate = checkInDate.plusDays(3);
        hotel.setCheckInDate(checkInDate);
        hotel.setCheckOutDate(checkOutDate);
        hotel.setOccupancy(new SearchedHotel.Occupancy(1, 2, 0));

        homePage.searchHotel(hotel);

        TestAssert testAssert = new TestAssert();
        testAssert.assertTrue(homePage.hotelResultsContainCorrectDestination(destination), "Results do not contain expected destination");

        Senelium.sleep(5000);
    }
}
