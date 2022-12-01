package com.i2i.ibus.dto;

import java.time.LocalDateTime;
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
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivingDateTime;
    private String source;
    private String destination;

}
