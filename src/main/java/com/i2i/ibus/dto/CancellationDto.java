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

import java.time.LocalDateTime;

/**
 * Cancellation is used to cancel the booking for user.
 * 
 * @author Esakkiraja E
 * @version 1.0
 * @since Nov 29 2022
 */
@Getter
@Setter
public class CancellationDto {
    @JsonProperty(access = Access.READ_ONLY)
    private int id;
    @JsonProperty(access = Access.READ_ONLY)
    private LocalDateTime dateTime;
    @JsonProperty(access = Access.READ_ONLY)
    private double refundAmount;
    @JsonProperty(access = Access.READ_ONLY)
    private String cancellationStatus;
    @JsonProperty(access = Access.READ_ONLY)
    private String refundStatus;
}
