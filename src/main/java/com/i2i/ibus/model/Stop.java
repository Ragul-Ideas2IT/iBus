/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.model;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

/**
 * Bus Ticket Booking Application
 * Used to get the bus stop details from the operators and also used to save
 * details.
 *
 * @author Ananth.
 * @version 1.0.
 * @since Nov 29 2022
 */
@Getter
@Setter
@Entity
@Where(clause = "is_deleted = false")
public class Stop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String stopName;
    private String landMark;
    private String city;
    private LocalTime arrivingTime;
    private LocalTime departureTime;
    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;
    private boolean isDeleted;
}
