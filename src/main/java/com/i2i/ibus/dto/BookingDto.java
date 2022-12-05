package com.i2i.ibus.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.i2i.ibus.model.Bus;
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
    @JsonProperty(access = Access.READ_ONLY)
    private String status;
    @JsonProperty(access = Access.READ_ONLY)
    private String paymentStatus;
    private LocalTime travelTime;
    private LocalDate travelDate;
    @JsonProperty(access = Access.READ_ONLY)    
    private LocalDate date;
    @JsonProperty(access = Access.READ_ONLY)    
    private LocalTime time;
    private Cancellation cancellation;
}
