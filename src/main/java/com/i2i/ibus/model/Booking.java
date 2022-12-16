/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

/**
 * Booking used to connect the database, to save the user booking details and to
 * get the user booking details.
 *
 * @author Esakkiraja E
 * @version 1.0
 * @since Nov 29 2022
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Where(clause = "is_deleted = false")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double totalFare;
    private LocalDateTime dateTime;
    private String source;
    private String destination;
    private String pickUpPoint;
    private String dropPoint;
    private String status;
    private LocalTime travelTime;
    private LocalDate travelDate;
    private boolean isDeleted;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "booking_id")
    private List<BookingDetail> bookingDetails;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cancellation_id")
    private Cancellation cancellation;
    @OneToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;
}
