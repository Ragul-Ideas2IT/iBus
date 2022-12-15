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

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @author Ragul
 * @version 1.0
 * @since Nov 29 2022
 */
@Getter
@Setter
public class OperatorDto {

    @JsonProperty(access = Access.READ_ONLY)
    private int id;
    @Pattern(regexp = "^([a-zA-Z][ ]?){2,}$", message = "Travel's name should be in valid format...(a-zA-Z)")
    @NotBlank(message = "Travel's name is mandatory")
    private String travelsName;
    @Pattern(regexp = "^([a-zA-Z][ ]?){2,}$", message = "Owner's name should be in valid format...(a-zA-Z)")
    @NotBlank(message = "Owner's name is mandatory")
    private String ownerName;
    @Pattern(regexp = "^(\\+91|0)?[6789][0-9]{9}$", message = "Phone no should be 10 digits or with \"+91\" or \"0\"")
    @NotBlank(message = "Phone number is mandatory")
    private String phoneNumber;
    @NotBlank(message = "Mail ID is mandatory")
    @Email(message = "Email ID should be in valid format")
    private String mailId;
    @Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]$", message = "PAN number should be in valid format...(ABCDE1234A)")
    @NotBlank(message = "PAN number is mandatory")
    private String panNumber;
    @Pattern(regexp = "^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z][0-9](Z)[0-9]$", message = "GST number should be in valid format...(12ABCDE1234A1Z1)")
    @NotBlank(message = "GST number is mandatory")
    private String gstNumber;
    @NotEmpty
    @Valid
    private List<AddressDto> addresses;
}
