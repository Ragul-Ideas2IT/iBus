package com.i2i.ibus.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Ananth.
 *
 */
@Getter
@Setter
public class PickupPointDto {
	
	private String stopName;
	private String landMark;
	private LocalDate arrivingTime;
	private LocalDate departureTime;

}
