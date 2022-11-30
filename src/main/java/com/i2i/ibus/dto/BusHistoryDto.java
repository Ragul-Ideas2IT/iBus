package com.i2i.ibus.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Ananth.
 *
 */
@Getter
@Setter
public class BusHistoryDto {

    private int id;
    private LocalTime busTiming;
    private LocalDate busDate;
    private String source;
    private String destination;

}
