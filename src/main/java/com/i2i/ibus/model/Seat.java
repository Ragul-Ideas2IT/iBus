/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

/**
 * Bus Ticket Booking Application
 * Used to get the bus seat details from the operators and also used to save
 * details.
 *
 * @author Ananth.
 * @version 1.0.
 * @since Nov 29 2022
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Where(clause = "is_deleted = false")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String seatType;
    private String seatNumber;
    private String gender;
    private double fare;
    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;
    private boolean isDeleted;
    private boolean isOccupied;

}
