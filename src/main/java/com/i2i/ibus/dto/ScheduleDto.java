/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.i2i.ibus.constants.Constants;

import lombok.Getter;
import lombok.Setter;

/**
 * Bus Ticket Booking Application
 * Used to get the bus schedule details from the operators and also used to read the
 * saved details.
 *
 * @author Ananth.
 * @version 1.0.
 * @since Nov 29 2022
 */
@Getter
@Setter
public class ScheduleDto {

    @JsonProperty(access = Access.READ_ONLY)
    private int id;
    @NotNull
    @FutureOrPresent
    private LocalDate departureDate;
    @NotNull
    private LocalTime departureTime;
    @NotNull
    @FutureOrPresent
    private LocalDate arrivingDate;
    @NotNull
    private LocalTime arrivingTime;
    @NotBlank
    @Pattern(regexp = Constants.NAME_PATTERN, message = Constants.INVALID_SOURCE_PATTERN_MESSAGE)
    private String source;
    @NotBlank
    @Pattern(regexp = Constants.NAME_PATTERN, message = Constants.INVALID_DESTINATION_PATTERN_MESSAGE)
    private String destination;
    @JsonProperty(access = Access.READ_ONLY)
    private BusDto bus;
    private LocalTime actualDepartureTime;
    private LocalTime actualArrivingTime;
    @JsonProperty(access = Access.WRITE_ONLY)
    private int busId;
    @JsonProperty(access = Access.READ_ONLY)
    private String status;

}
