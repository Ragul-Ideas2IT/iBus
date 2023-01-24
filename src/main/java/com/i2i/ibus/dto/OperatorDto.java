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
import lombok.ToString;

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
@ToString
public class OperatorDto {

    @JsonProperty(access = Access.READ_ONLY)
    private int id;
    @Pattern(regexp = Constants.NAME_PATTERN, message = Constants.INVALID_TRAVELS_NAME_PATTERN_MESSAGE)
    @NotBlank
    private String travelsName;
    @Pattern(regexp = Constants.NAME_PATTERN, message = Constants.INVALID_OWNERS_NAME_PATTERN_MESSAGE)
    @NotBlank
    private String ownerName;
    @Pattern(regexp = Constants.PHONE_NUMBER_PATTERN, message = Constants.INVALID_PHONE_NUMBER_PATTERN_MESSAGE)
    @NotBlank
    private String phoneNumber;
    @NotBlank
    @Email(message = Constants.INVALID_EMAIL_ID_PATTERN_MESSAGE)
    private String mailId;
    @Pattern(regexp = Constants.PAN_NUMBER_PATTERN, message = Constants.INVALID_PAN_NUMBER_PATTERN_MESSAGE)
    @NotBlank
    private String panNumber;
    @Pattern(regexp = Constants.GST_NUMBER_PATTERN, message = Constants.INVALID_GST_NUMBER_PATTERN_MESSAGE)
    @NotBlank
    private String gstNumber;
    @JsonProperty(access = Access.WRITE_ONLY)
    @NotBlank
    @Pattern(regexp = Constants.PASSWORD_PATTERN, message = Constants.INVALID_PASSWORD_PATTERN_MESSAGE)
    private String password;
    @NotEmpty
    @Valid
    private List<AddressDto> addresses;
}
