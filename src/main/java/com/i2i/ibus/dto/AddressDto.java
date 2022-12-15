/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author Ragul
 * @version 1.0
 * @since Nov 29 2022
 */
@Getter
@Setter
public class AddressDto {

    @JsonProperty(access = Access.READ_ONLY)
    private int id;
    @Pattern(regexp = "^([a-zA-Z][ ]?){2,}$", message = "Landmark should be in valid format")
    @NotBlank(message = "Landmark is mandatory")
    private String landmark;
    @Pattern(regexp = "^([a-zA-Z0-9,][ ]?){2,}$", message = "Street should be in valid format")
    @NotBlank(message = "Street name is mandatory")
    private String street;
    @Pattern(regexp = "^([a-zA-Z][ ]?){2,}$", message = "City should be in valid format")
    @NotBlank(message = "City name is mandatory")
    private String city;
    @Pattern(regexp = "^([a-zA-Z][ ]?){2,}$", message = "District should be in valid format")
    @NotBlank(message = "District name is mandatory")
    private String district;
    @Pattern(regexp = "^([a-zA-Z][ ]?){2,}$", message = "State should be in valid format")
    @NotBlank(message = "State name is mandatory")
    private String state;
    @Min(100000)
    @Max(999999)
    private int pincode;

}
