package com.i2i.ibus.dto;

import java.util.List;

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
public class BusDto {

    private int id;
    private String busNumber;
    private int numberOfSeats;
    private String type;
    private OperatorDto operatorDto;
    private List<BusHistoryDto> busHistories;
    private List<PickupPointDto> pickupPoints;
    private List<SeatDto> seats;
}
