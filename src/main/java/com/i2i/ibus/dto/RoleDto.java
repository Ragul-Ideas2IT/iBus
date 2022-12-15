/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author Ragul
 * @version 1.0
 * @since Dec 12 2022
 */
@Getter
@Setter
public class RoleDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;
    @Pattern(regexp = "(?i)^(user)|(operator)$", message = "Role should be user or operator")
    @NotBlank(message = "Role name is mandatory")
    private String name;
}
