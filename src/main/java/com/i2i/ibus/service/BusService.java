/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.service;

import com.i2i.ibus.dto.BusDto;

import java.util.List;

/**
 * Bus Ticket Booking Application
 * Used to manipulate the Bus details in the application. Operators are
 * manipulate the bus details.
 *
 * @author Ananth.
 * @version 1.0.
 * @since Nov 29 2022
 */
public interface BusService {

    /**
     * Used to add the bus details from the operators.
     *
     * @param bus        details given by the operator.
     * @param operatorId The id of the operator to which the bus belongs.
     * @return the saved bus details and httpstatus.
     */
    BusDto addBus(BusDto busDto);

    /**
     * Returns a list of buses that belong to a specific operator.
     *
     * @param OperatorId The id of the operator.
     * @return A list of BusDto objects.
     */
    List<BusDto> getAllByOperatorId(int OperatorId);

    /**
     * Get a bus by its id.
     *
     * @param id The id of the bus you want to get.
     * @return A BusDto object
     */
    BusDto getById(int id);

    /**
     * Update a bus with the given busId and operatorId.
     *
     * @param busDto     This is the object that contains the updated information of
     *                   the bus.
     * @param busId      The id of the bus you want to update.
     * @param operatorId The id of the operator who owns the bus.
     * @return BusDto
     */
    BusDto updateBus(BusDto busDto, int busId);

    /**
     * Deletes the bus with the given id.
     *
     * @param busId The id of the bus to be deleted.
     */
    void deleteBus(int busId);

    /**
     * Get all buses.
     *
     * @return A list of BusDto objects.
     */
    List<BusDto> getAllBuses();
}