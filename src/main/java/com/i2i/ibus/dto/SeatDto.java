package com.i2i.ibus.dto;

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
public class SeatDto {

    private int id;
    private String seatType;
    private String seatNumber;
    private String gender;
    private double fare;
}