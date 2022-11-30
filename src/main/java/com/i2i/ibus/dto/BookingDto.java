package com.i2i.ibus.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.i2i.ibus.model.Cancellation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * Author ESAKKIRAJA 
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
    private Timestamp DateTime;
    private Boolean isExpired;
    // private List<Payment> payment;
    private Cancellation cancellation;

}
