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

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * Bus Ticket Booking Application
 * Used to get the bus details from the operators and also used to read the
 * saved details.
 *
 * @author Ananth.
 * @version 1.0.
 * @since Nov 29 2022
 */
@Getter
@Setter
public class BusDto {

    @JsonProperty(access = Access.READ_ONLY)
    private int id;
    @NotBlank
    @Pattern(regexp = Constants.BUS_NUMBER_PATTERN, message = Constants.INVALID_BUS_NUMBER_PATTERN_MESSAGE)
    private String busNumber;
    @Min(value = 1)
    @Max(value = 40)
    private int numberOfSeats;
    @NotBlank
    @Pattern(regexp = Constants.BUS_TYPE_PATTERN, message = Constants.INVALID_BUS_TYPE_PATTERN_MESSAGE)
    private String type;
    @JsonProperty(access = Access.WRITE_ONLY)
    private int operatorsId;
    @JsonProperty(access = Access.READ_ONLY)
    private OperatorDto operator;
}
