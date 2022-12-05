package com.i2i.ibus.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class BusHistoryDto {

    private int id;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalDate arrivingDate;
    private LocalTime arrivingTime;
    private String source;
    private String destination;

}
