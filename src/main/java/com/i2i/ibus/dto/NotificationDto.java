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
 * NotificationDto are used to notifying the messages to user.
 * 
 * @author Tamilmani
 * @version 1.0
 * @since Nov 29 2022
 */
@Getter
@Setter
@AllArgsConstructor
public class NotificationDto {

    private int referenceId;
    private String message;
    private String referenceType;

}