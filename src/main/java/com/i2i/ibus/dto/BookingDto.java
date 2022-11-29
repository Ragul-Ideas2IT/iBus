package com.i2i.ibus.dto;

import java.time.LocalDateTime;

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

	private int numberOfSeats;
	private double totalFare;
	private String pickUpPoint;
	private String dropPoint;
	private String status;
	private LocalDateTime travelDateAndTime;
	private Boolean isExpired;
	// private List<Payment> payment;
	// private Notification notification;
	// private Cancellation cancellation;

}
