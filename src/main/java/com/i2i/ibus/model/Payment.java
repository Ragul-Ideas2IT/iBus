/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

/**
 * Payment are used to connect the database.
 *
 * @author Tamilmani K
 * @version 1.0
 * @since Nov 29 2022
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Where(clause = "is_deleted = false")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double amount;
    private String status;
    private String modeOfPayment;
    private int cvvNumber;
    private long cardNumber;
    private String cardHolderName;
    private LocalDateTime time;
    private String message;
    @ManyToOne
    private Booking booking;
    private boolean isDeleted;

}