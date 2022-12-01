package com.i2i.ibus.dto;

import java.time.LocalDate;
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
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate busDate;
    private String source;
    private String destination;

}
