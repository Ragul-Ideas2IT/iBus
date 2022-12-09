package com.i2i.ibus.service;

import com.i2i.ibus.dto.SeatDto;

import java.util.List;

 public interface SeatService {

     /**
      * Add a seat to a bus
      *
      * @param seatDto This is the seat object that you want to add to the bus.
      * @param busId The id of the bus to which the seat is to be added.
      * @return SeatDto
      */
     SeatDto addSeat(SeatDto seatDto, int busId);

	/**
	 * It returns a list of all seats in a bus
	 *
	 * @param busId The id of the bus whose seats are to be fetched.
	 * @return List of SeatDto objects
	 */
	List<SeatDto> getAllByBusId(int busId);

	/**
	 * Get a seat by its id.
	 *
	 * @param id The id of the seat you want to get.
	 * @return A SeatDto object.
	 */
	SeatDto getBySeatId(int id);

	/**
	 * Update a seat in a bus.
	 *
	 * @param seatDto The seat object that you want to update.
	 * @param seatId The id of the seat to be updated.
	 * @param busId The id of the bus whose seat is to be updated.
	 * @return SeatDto
	 */
	SeatDto updateSeat(SeatDto seatDto, int seatId, int busId);

	/**
	 * Deletes a seat from the database.
	 *
	 * @param seatId The id of the seat to be deleted.
	 */
	void deleteSeat(int seatId);
}
