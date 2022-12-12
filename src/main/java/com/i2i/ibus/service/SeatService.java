package com.i2i.ibus.service;

import com.i2i.ibus.dto.SeatDto;

import java.util.List;

/**
 * <h1>Bus Ticket Booking Application
 * <h1>
 * <p>
 * Used to manipulate the Bus schedule details in the application. Operators are
 * manipulate the bus schedule details.
 * <p>
 *
 * @author Ananth.
 * @version 1.0.
 * @created Nov 29 2022
 */
public interface SeatService {

    /**
     * Uses to add bus seat deatils given by the operators.
     *
     * @param seatDto seat deatils given by the operators.
     * @param busId   The id of the bus to which the seat is to be added.
     * @return the saved bus seat deatils will return.
     */
    SeatDto addSeat(SeatDto seatDto, int busId);

    /**
     * It returns a list of all seats in a bus
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
     * @param seatId The id of the seat you want to update.
     * @param busId The id of the bus that the seat belongs to.
     * @return the update seat details.
     */
    SeatDto updateSeat(SeatDto seatDto, int seatId, int busId);

    /**
     * It deletes a seat deatils.
     *
     * @param seatId The id of the seat to be deleted.
     */
    void deleteSeat(int seatId);
}
