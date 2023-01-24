/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.dto;

import lombok.Getter;

import lombok.Setter;

import javax.validation.constraints.*;

import com.i2i.ibus.constants.Constants;

/**
* BookingDetailDTO are used to get the passenger details from the server and send the passenger
* details to the server in the type of {@link BookingDetailDto} object. It contains the
* attributes of the passenger details. Here, the pattern of the attributes are specified.
* 
* @author Tamilmani K
* @version 1.0
* @since Nov 29 2022
*
*/
@Getter
@Setter
public class BookingDetailDto {

    @NotNull
    @Min(value = 2)
    @Max(value = 120)
    private int age;
    @NotBlank
    @Pattern(regexp = Constants.NAME_PATTERN, message = Constants.INVALID_NAME_PATTERN_MESSAGE)
    private String name;
    @NotBlank
    @Pattern(regexp = Constants.GENDER_PATTERN, message = Constants.INVALID_GENDER_PATTERN_MESSAGE)
    private String gender;
    @NotBlank
    @Pattern(regexp = Constants.SEAT_NUMBER_PATTERN, message = Constants.INVALID_SEAT_NUMBER_PATTERN_MESSAGE)
    private String seatNumber;

}