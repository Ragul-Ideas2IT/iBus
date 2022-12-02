package com.i2i.ibus.dto;

import java.time.LocalDateTime;

import com.i2i.ibus.model.Cancellation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Esakkiraja E
 * @version 1.0
 *
 * @created Nov 29 2022
 */
@Setter
@Getter
@NoArgsConstructor
public class BookingDto {

    private int id; 
    private int numberOfSeats;
    private double totalFare;
    private String pickUpPoint;
    private String dropPoint;
    private String status;
    private LocalDateTime travelDateAndTime;
    private LocalDateTime dateTime;
    private Cancellation cancellation;
}
