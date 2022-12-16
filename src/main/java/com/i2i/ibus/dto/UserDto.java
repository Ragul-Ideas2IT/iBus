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
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * DTO class that contains the fields that are required to create a user with getters and setters
 *
 * @author Ragul
 * @version 1.0
 * @since Nov 28 2022
 */
@Getter
@Setter
public class UserDto {

    @JsonProperty(access = Access.READ_ONLY)
    private int id;
    @Pattern(regexp = Constants.NAME_PATTERN, message = Constants.INVALID_NAME_PATTERN_MESSAGE)
    @NotBlank
    private String name;
    @Pattern(regexp = Constants.PHONE_NUMBER_PATTERN, message = Constants.INVALID_PHONE_NUMBER_PATTERN_MESSAGE)
    @NotBlank
    private String phoneNumber;
    @NotBlank
    @Email(message = Constants.INVALID_EMAIL_ID_PATTERN_MESSAGE)
    private String mailId;
    @Pattern(regexp = Constants.GENDER_PATTERN, message = Constants.INVALID_GENDER_PATTERN_MESSAGE)
    @NotBlank
    private String gender;
    @Min(18)
    @Max(120)
    private int age;
    @JsonProperty(access = Access.WRITE_ONLY)
    @NotBlank
    @Pattern(regexp = Constants.PASSWORD_PATTERN, message = Constants.INVALID_PASSWORD_PATTERN_MESSAGE)
    private String password;
}