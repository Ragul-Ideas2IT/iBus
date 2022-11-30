package com.i2i.ibus.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Ananth.
 *
 */
@Getter
@Setter
public class BusDto {

	private int id;
	private String busNumber;
	private int numberOfSeats;
	private int type;
	private List<BusHistoryDto> busHistories;
	private List<PickupPointDto> pickupPoints;
}
