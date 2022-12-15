/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.i2i.ibus.constants.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * DTO class that contains the fields that are required to create an account with getters and setters
 *
 * @author Ragul
 * @version 1.0
 * @since Dec 12 2022
 */
@Getter
@Setter
public class AccountDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;
    @NotBlank
    @Email(message = Constants.INVALID_EMAILID_PATTERN_MESSAGE)
    private String mailId;
    @NotBlank
    @Pattern(regexp = Constants.ROLE_NAME_PATTERN, message = Constants.INVALID_ROLE_NAME_PATTERN_MESSAGE)
    private String role;
    @NotBlank
    @Pattern(regexp = Constants.PASSWORD_PATTERN, message = Constants.INVALID_PASSWORD_PATTERN_MESSAGE)
    private String password;
}
