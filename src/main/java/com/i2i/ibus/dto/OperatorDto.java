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

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * DTO class that contains the fields that are required to create an Operator with getters and setters
 *
 * @author Ragul
 * @version 1.0
 * @since Nov 29 2022
 */
@Getter
@Setter
public class OperatorDto {

    @JsonProperty(access = Access.READ_ONLY)
    private int id;
    @Pattern(regexp = Constants.NAME_PATTERN, message = Constants.INVALID_TRAVELSNAME_PATTERN_MESSAGE)
    @NotBlank
    private String travelsName;
    @Pattern(regexp = Constants.NAME_PATTERN, message = Constants.INVALID_OWNERNAME_PATTERN_MESSAGE)
    @NotBlank
    private String ownerName;
    @Pattern(regexp = Constants.PHONENUMBER_PATTERN, message = Constants.INVALID_PHONENUMBER_PATTERN_MESSAGE)
    @NotBlank
    private String phoneNumber;
    @NotBlank
    @Email(message = Constants.INVALID_EMAILID_PATTERN_MESSAGE)
    private String mailId;
    @Pattern(regexp = Constants.PANNUMBER_PATTERN, message = Constants.INVALID_PANNUMBER_PATTERN_MESSAGE)
    @NotBlank
    private String panNumber;
    @Pattern(regexp = Constants.GST_NUMBER_PATTERN, message = Constants.INVALID_GST_NUMBER_PATTERN_MESSAGE)
    @NotBlank
    private String gstNumber;
    @NotEmpty
    @Valid
    private List<AddressDto> addresses;
}
