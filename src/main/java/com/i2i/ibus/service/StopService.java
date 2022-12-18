/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.i2i.ibus.service;

import com.i2i.ibus.dto.StopDto;
import com.i2i.ibus.model.Stop;

import java.util.List;
import java.util.Optional;

/**
 * Bus Ticket Booking Application
 * Used to manipulate the Bus stop details in the application. Operators are
 * manipulate the bus stop details.
 *
 * @author Ananth.
 * @version 1.0.
 * @since Nov 29 2022
 */
public interface StopService {

    /**
     * Used to add bus stop deatils given by operators.
     *
     * @param stopDto stop details given by operators to add.
     * @param busId   The id of the bus to which the stop is to be added.
     * @return returns the added bus stop details.
     */
    StopDto addStop(StopDto stopDto);

    /**
     * Returns a list of stops for a given bus id
     *
     * @param busId The id of the bus whose stops are to be fetched.
     * @return A list of stops for a given bus id.
     */
    List<StopDto> getStopsByBusId(int busId);

    /**
     * Update the stop details
     *
     * @param stopDto given by operators will be used to update the stop.
     * @param StopId  The id of the stop to be updated.
     * @param busId   The id of the bus that the stop is associated with.
     * @return returns the updated stop details.
     */
    StopDto updateStop(StopDto stopDto, int StopId);

    /**
     * Deletes the stop with the given id.
     *
     * @param StopId The id of the stop to be deleted.
     * @return MessageDto
     */
    void deleteStop(int StopId);

    Optional<Stop> findAllByBusIdAndCityAndStopName(int busId, String city, String stopName);
}
