/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

/**
 * Booking detail used to connect the database, to save the passenger details and
 * to get the passenger details.
 * 
 * @author Tamilmani
 * @version 1.0
 * @since Nov 29 2022
 */
@Getter
@Setter
@Entity
@Where(clause = "is_deleted = false")
public class BookingDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int age;
    private String name;
    private String gender;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Booking booking;
    private String seatNumber;
    private boolean isDeleted;

}