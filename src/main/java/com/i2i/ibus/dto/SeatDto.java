/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.i2i.ibus.constants.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * Bus Ticket Booking Application
 * Used to get the bus seat details from the operators and also used to read the
 * saved details.
 *
 * @author Ananth.
 * @version 1.0.
 * @since Nov 29 2022
 */
@Getter
@Setter
public class SeatDto {

    @JsonProperty(access = Access.READ_ONLY)
    private int id;
    @NotBlank
    @Pattern(regexp = Constants.SEAT_TYPE_PATTERN, message = Constants.INVALID_SEAT_TYPE_PATTERN_MESSAGE)
    private String seatType;
    @NotBlank
    @Pattern(regexp = Constants.SEAT_NUMBER_PATTERN, message = Constants.INVALID_SEAT_NUMBER_PATTERN_MESSAGE)
    private String seatNumber;
    @NotBlank
    @Pattern(regexp = Constants.GENDER_PATTERN, message = Constants.INVALID_SEAT_GENDER_PATTERN_MESSAGE)
    private String gender;
    @JsonProperty(access = Access.READ_ONLY)
    private BusDto bus;
    @Min(value = 0)
    private double fare;
    @JsonProperty(access = Access.WRITE_ONLY)
    private int busesId;
    @JsonProperty(access = Access.READ_ONLY)
    private boolean isOccupied;
}