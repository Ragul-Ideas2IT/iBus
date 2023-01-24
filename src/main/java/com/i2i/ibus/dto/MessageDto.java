/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Simple DTO that holds a message code and a message with constructor, getters and setters
 *
 * @author Ragul
 * @version 1.0
 * @since Nov 30 2022
 */
@Getter
@Setter
@AllArgsConstructor
public class MessageDto {
    private String code;
    private String message;
}
