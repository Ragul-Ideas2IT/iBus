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
import java.time.LocalDateTime;

/**
 * PaymentDTO are used to get the request from the server and send the response
 * to the server in the type of {@link PaymentDto} object. It contains the
 * attributes. Here, the pattern of the attributes are specified.
 * 
 * @author Tamilmani K
 * @version 1.0
 * @since Nov 29 2022
 *
 */
@Getter
@Setter
public class PaymentDto {

    @Min(value = 0)
    private double amount;
    @JsonProperty(access = Access.READ_ONLY)
    private String status;
    @NotBlank(message = Constants.PAYMENT_TYPE_MANDATORY_MESSAGE)
    @Pattern(regexp = Constants.PAYMENT_TYPE_PATTERN, message = Constants.INVALID_PAYMENT_TYPE_MESSAGE)
    private String modeOfPayment;
    @Min(0)
    @Max(999)
    @JsonProperty(access = Access.WRITE_ONLY)
    private int cvvNumber;
    @NotBlank(message = Constants.CARD_NUMBER_MANDATORY_MESSAGE)
    @Pattern(regexp = Constants.CARD_NUMBER_PATTERN, message = Constants.INVALID_CARD_NUMBER_PATTERN_MESSAGE)
    private String cardNumber;
    @NotBlank(message = Constants.NAME_MANDATORY_MESSAGE)
    @Pattern(regexp = Constants.NAME_PATTERN, message = Constants.INVALID_NAME_PATTERN_MESSAGE)
    private String cardHolderName;
    @JsonProperty(access = Access.READ_ONLY)
    private LocalDateTime time;
    @JsonProperty(access = Access.READ_ONLY)
    private String message;

}