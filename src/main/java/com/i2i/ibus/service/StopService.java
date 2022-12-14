package com.i2i.ibus.service;

import com.i2i.ibus.dto.StopDto;

import java.util.List;

/**
 * <h1>Bus Ticket Booking Application
 * <h1>
 * <p>
 * Used to manipulate the Bus stop details in the application. Operators are
 * manipulate the bus stop details.
 * <p>
 *
 * @author Ananth.
 * @version 1.0.
 * @created Nov 29 2022
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
     * It returns a list of stops for a given bus id
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
     * It deletes the stop with the given id.
     *
     * @param StopId The id of the stop to be deleted.
     * @return MessageDto
     */
    void deleteStop(int StopId);
}
