/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.repository;

import com.i2i.ibus.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Used to save and retrieve the booking Details.
 *
 * @author Esakkiraja E
 * @version 1.0
 * @since Nov 29 2022
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {


    /**
     * Find all bookings for a given user.
     *
     * @param userId The id of the user whose bookings you want to find.
     * @return A list of Booking objects
     */
    List<Booking> findAllByUserId(int userId);
    
    /**
     * Find all bookings for a given user.
     *
     * @param busId The id of the user whose bookings you want to find.
     * @return A list of Booking objects
     */
    List<Booking> findAllByBusId(int busId);

    /**
     * Find all booking by bus id and travel date.
     *
     * @param busId Bus id to get the list of booking of the bus.
     * @param travelDate Date to find the list of booking of the bus.
     * @return list of Booking.
     */
    List<Booking> findAllByTravelDateAndBusId(LocalDate travelDate, int busId);
}
