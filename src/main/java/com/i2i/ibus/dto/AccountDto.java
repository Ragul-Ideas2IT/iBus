/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author Ragul
 * @version 1.0
 * @since Dec 12 2022
 */
@Getter
@Setter
public class AccountDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;
    @NotBlank(message = "Mail ID is mandatory")
    @Email(message = "Email ID should be in valid format")
    private String mailId;
    @Valid
    private RoleDto role;
    @NotBlank(message = "Password is mandatory")
    private String password;
}
