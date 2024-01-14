package com.senelium.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchedHotel {
    String destination;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    Occupancy occupancy;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Occupancy {
        int rooms;
        int adults;
        int children;
    }
}
