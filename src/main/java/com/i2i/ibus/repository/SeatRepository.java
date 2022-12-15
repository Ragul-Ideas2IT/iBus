/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.repository;

import com.i2i.ibus.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Bus Ticket Booking Application
 * Used to save and retrieve the bus seat details.
 *
 * @author Ananth.
 * @version 1.0.
 * @since Nov 29 2022
 */
@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {

    /**
     * Used to get the bus seat details by the given bus id.
     *
     * @param busId given by the operator.
     * @return the bus details found or else nothing.
     */
    List<Seat> findAllByBusId(int busId);

    /**
     * Used to get seat details bus id and seat number
     *
     * @param seatNumber given by operator.
     * @param busId      given by operator.
     * @return the bus details found or else nothing.
     */
    Optional<Seat> findBySeatNumberAndBusId(String seatNumber, int busId);

    /**
     * Used to get seat details bus id and seat number and seat id.
     *
     * @param seatNumber given by operator.
     * @param busId      given by operator.
     * @param seatId     given by operator.
     * @return the bus details found or else nothing.
     */
    Optional<Seat> findBySeatNumberAndBusIdAndIdNot(String seatNumber, int busId, int seatId);
}
