package com.i2i.ibus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.i2i.ibus.constants.Constants;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 * Used to get the bus seat details from the operators.
 * 
 * @author Ananth.
 * @version 1.0.
 * 
 * @since Nov 29 2022
 *
 */
@Getter
@Setter
public class SeatDto {

    @JsonProperty(access = Access.READ_ONLY)
    private int id;
    @NotBlank
    @Pattern(regexp = Constants.SEAT_TYPE_PATTERN)
    private String seatType;
    @NotBlank
    @Pattern(regexp = Constants.SEAT_NUMBER_PATTERN)
    private String seatNumber;
    @NotBlank
    @Pattern(regexp = Constants.GENDER_PATTERN)
    private String gender;
    @JsonProperty(access = Access.READ_ONLY)
    private BusDto bus;
    @Min(value = 0)
    private double fare;
    @JsonProperty(access = Access.WRITE_ONLY)
    private int busId;
    @JsonProperty(access = Access.READ_ONLY)
    private boolean isOccupied;
}