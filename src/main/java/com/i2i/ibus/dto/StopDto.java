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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalTime;

/**
 * Bus Ticket Booking Application
 * Used to get the bus stop details from the operators and also used to read the
 * saved details.
 *
 * @author Ananth.
 * @version 1.0.
 * @since Nov 29 2022
 */
@Getter
@Setter
public class StopDto {

    @JsonProperty(access = Access.READ_ONLY)
    private int id;
    @NotBlank
    @Pattern(regexp = Constants.NAME_PATTERN, message = Constants.INVALID_NAME_PATTERN_MESSAGE)
    private String stopName;
    @NotBlank
    @Pattern(regexp = Constants.NAME_PATTERN,  message = Constants.INVALID_LANDMARK_PATTERN_MESSAGE)
    private String landMark;
    @NotBlank
    @Pattern(regexp = Constants.NAME_PATTERN, message = Constants.INVALID_CITY_PATTERN_MESSAGE)
    private String city;
    @NotNull
    private LocalTime arrivingTime;
    @NotNull
    private LocalTime departureTime;
    @JsonProperty(access = Access.WRITE_ONLY)
    private int busId;
    @JsonProperty(access = Access.READ_ONLY)
    private BusDto bus;

}
