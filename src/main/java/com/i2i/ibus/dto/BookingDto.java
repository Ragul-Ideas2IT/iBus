/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.i2i.ibus.constants.Constants;

import lombok.Getter;
import lombok.Setter;

/**
 * BookingDTO are used to get the request from the server and send the response
 * to the server in the type of BookingDto object. It contains the
 * attributes. Here, the pattern of the attributes are specified.
 * 
 * @author Esakkiraja E
 * 
 * @version 1.0
 * @since Nov 29 2022
 */
@Setter
@Getter
public class BookingDto {

    @JsonProperty(access = Access.READ_ONLY)
    private int id;
    @NotBlank
    @JsonProperty(access = Access.WRITE_ONLY)
    private int busesId;
    @JsonProperty(access = Access.WRITE_ONLY)
    @NotBlank
    private int usersId;
    @JsonProperty(access = Access.READ_ONLY)
    private double totalFare;
    @NotBlank
    @Pattern(regexp = Constants.PLACE_NAME_PATTERN, message = Constants.INVALID_PICKUP_POINT_MESSAGE)
    private String pickUpPoint;
    @NotBlank
    @Pattern(regexp = Constants.PLACE_NAME_PATTERN, message = Constants.INVALID_DROP_POINT_MESSAGE)
    private String dropPoint;
    @NotBlank
    @Pattern(regexp = Constants.PLACE_NAME_PATTERN, message = Constants.INVALID_SOURCE_PATTERN_MESSAGE)
    private String source;
    @NotBlank
    @Pattern(regexp = Constants.PLACE_NAME_PATTERN, message = Constants.INVALID_SOURCE_PATTERN_MESSAGE)
    private String destination;
    @JsonProperty(access = Access.READ_ONLY)
    private String status;
    @FutureOrPresent
    private LocalTime travelTime;
    @NotBlank
    @FutureOrPresent
    private LocalDate travelDate;
    @NotEmpty
    @Valid
    private List<BookingDetailDto> bookingDetails;
    @JsonProperty(access = Access.READ_ONLY)
    private LocalDateTime dateTime;
    @JsonProperty(access = Access.READ_ONLY)
    private CancellationDto cancellation;
}
