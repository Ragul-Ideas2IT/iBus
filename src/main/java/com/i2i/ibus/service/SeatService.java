/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.i2i.ibus.service;

import com.i2i.ibus.dto.SeatDto;
import com.i2i.ibus.model.Seat;

import java.util.List;
import java.util.Optional;

/**
 * Bus Ticket Booking Application
 * Used to manipulate the Bus schedule details in the application. Operators are
 * manipulate the bus schedule details.
 *
 * @author Ananth.
 * @version 1.0.
 * @since Nov 29 2022
 */
public interface SeatService {

    /**
     * Uses to add bus seat details given by the operators.
     *
     * @param seatDto seat deatils given by the operators.
     * @param busId   The id of the bus to which the seat is to be added.
     * @return the saved bus seat deatils will return.
     */
    SeatDto addSeat(SeatDto seatDto);

    /**
     * Returns a list of all seats in a bus
     *
     * @param busId The id of the bus whose seats are to be fetched.
     * @return A list of seats for a specific bus.
     */
    List<SeatDto> getAllByBusId(int busId);

    /**
     * Used to get the seat details of the bus by given seat id.
     *
     * @param seatId The id of the seat you want to get.
     * @return A Seat details is being returned.
     */
    SeatDto getBySeatId(int id);

    /**
     * Used to update seat details.
     *
     * @param seatDto seat details given by operators will be updated.
     * @param seatId  The id of the seat you want to update.
     * @param busId   The id of the bus that the seat belongs to.
     * @return the update seat details.
     */
    SeatDto updateSeat(SeatDto seatDto, int seatId);

    /**
     * Deletes a seat details.
     *
     * @param seatId The id of the seat to be deleted.
     */
    void deleteSeat(int seatId);

    /**
     * Used to get the seat details by the seatNumber and budId. The seat were
     * given to the booking repository for validation process.
     * 
     * @param seatNumber from the booking user
     * @param busId      from the booking user
     * @return returns the seat or else nothing.
     */
    Optional<Seat> findBySeatNumberAndBusId(String seatNumber, int busId);

}
