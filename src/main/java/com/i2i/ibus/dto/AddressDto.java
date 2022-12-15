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
 * DTO class that contains the fields that are required to create an address with getters and setters
 *
 * @author Ragul
 * @version 1.0
 * @since Nov 29 2022
 */
@Getter
@Setter
public class AddressDto {

    @JsonProperty(access = Access.READ_ONLY)
    private int id;
    @Pattern(regexp = Constants.NAME_PATTERN, message = Constants.INVALID_LANDMARK_PATTERN_MESSAGE)
    @NotBlank
    private String landmark;
    @Pattern(regexp = Constants.STREET_NAME_PATTERN, message = Constants.INVALID_STREET_PATTERN_MESSAGE)
    @NotBlank
    private String street;
    @Pattern(regexp = Constants.NAME_PATTERN, message = Constants.INVALID_CITY_PATTERN_MESSAGE)
    @NotBlank
    private String city;
    @Pattern(regexp = Constants.NAME_PATTERN, message = Constants.INVALID_DISTRICT_PATTERN_MESSAGE)
    @NotBlank
    private String district;
    @Pattern(regexp = Constants.NAME_PATTERN, message = Constants.INVALID_STATE_PATTERN_MESSAGE)
    @NotBlank
    private String state;
    @Min(100000)
    @Max(999999)
    private int pincode;

}
