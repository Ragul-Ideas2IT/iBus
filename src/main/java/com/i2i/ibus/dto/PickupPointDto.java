package com.i2i.ibus.dto;

import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Ananth.
 * @version 1.0.
 * 
 * @created Nov 29 2022
 *
 */
@Getter
@Setter
public class PickupPointDto {

    private int id;
    private String stopName;
    private String landMark;
    private LocalTime arrivingTime;
    private LocalTime departureTime;

}
