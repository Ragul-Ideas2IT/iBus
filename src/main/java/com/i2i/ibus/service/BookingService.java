package com.i2i.ibus.service;

import java.time.LocalDate;
import java.util.List;

import com.i2i.ibus.dto.BookingDto;
import com.i2i.ibus.model.Booking;
import com.i2i.ibus.model.BookingDetail;
import com.i2i.ibus.model.Bus;
import com.i2i.ibus.model.BusHistory;
import com.i2i.ibus.model.Cancellation;
import com.i2i.ibus.model.User;

/**
 * @author Esakkiraja E
 * @version 1.0
 *
 * @created Nov 29 2022
 */
public interface BookingService {
    /**
     * Add a booking for a user on a bus
     *
     * @param userId The id of the user who is booking the ticket.
     * @param busId The id of the bus that the user wants to book.
     * @param bookingDto This is the object that contains the details of the booking.
     * @return BookingDto
     */
    public BookingDto addBooking(int userId, int busId, BookingDto bookingDto);

    /**
     * > Get all bookings
     *
     * @return List of BookingDto
     */
    public List<BookingDto> getAllBooking();

    /**
     * This function returns a booking by id.
     *
     * @param id The id of the booking you want to retrieve.
     * @return A BookingDto object.
     */
    public BookingDto getBookingById(int id);

    /**
     * Get all bookings for a user.
     *
     * @param userId The id of the user who made the booking.
     * @return A list of BookingDto objects.
     */
    public List<BookingDto> getBookingByUserId(int userId);

    /**
     * This function returns a Bus object with the given id.
     *
     * @param id The id of the bus you want to get.
     * @return A bus object
     */
    public Bus getBusById(int id);

    /**
     * This function returns a User object with the given id.
     *
     * @param id The id of the user to be retrieved.
     * @return A User object
     */
    public User getUserById(int id);

    /**
     * "Get the bus history for a bus on a given travel date."
     *
     * The function name is `getBusHistoryByTravelDate`. It takes two parameters: `bus` and `travelDate`. The return type
     * is `BusHistory`
     *
     * @param bus The bus object that you want to get the history for.
     * @param travelDate The date of travel
     * @return A BusHistory object
     */
    public BusHistory getBusHistoryByTravelDate(Bus bus, LocalDate travelDate);

    /**
     * Cancel a booking.
     *
     * @param bookingId The booking id of the booking that is being cancelled.
     */
    public void cancellation(int bookingId);

    /**
     * Given a list of booking details and a bus id, calculate the fare for the bus
     *
     * @param bookingDetails List of BookingDetail objects.
     * @param busId The id of the bus for which the fare is to be calculated.
     * @return A double value
     */
    public double calculateFare(List<BookingDetail> bookingDetails, int busId);

    /**
     * Cancel a booking and return the cancellation.
     *
     * @param booking The booking to be cancelled.
     * @param cancellation The cancellation object that contains the cancellation details.
     * @return A Cancellation object.
     */
    public Cancellation cancelBooking(Booking booking, Cancellation cancellation);

    /**
     * Complete a booking.
     *
     * @param id The id of the booking to be completed.
     */
    public void completeBooking(int id);

	/**
	 * This function sets the seat status of a booking.
	 *
	 * @param booking The booking object that contains the seat number and the status of the seat.
	 */
	public void setSeatStatus(Booking booking);

    /**
     * It calculates the difference of time between the current time and the time when the bus was last seen.
     *
     * @param busHistory The BusHistory object that contains the time of the bus.
     * @return The difference of time between the current time and the time the bus was last seen.
     */
    public long calculateDifferenceOfTime(BusHistory busHistory);

    /**
     * Delete a booking with the given bookingId.
     *
     * @param bookingId The id of the booking to be deleted.
     */
    public void deleteBooking(int bookingId);

    /**
     * This function validates a user.
     *
     * @param id The id of the user to validate.
     */
    public void validateUser(int id);

	/**
	 * Validate the bus with the given id.
	 *
	 * @param id The id of the bus to be validated.
	 */
	public void validateBus(int id);

    /**
     * Validate a booking.
     *
     * @param id The id of the booking to be validated.
     */
    public void validateBooking(int id);

    /**
     *  Validates the pickup points of a booking
     *
     * @param booking The booking object that contains the pickup points.
     * @param busId The id of the bus that the user has selected.
     */
    public void validatePickupPoints(Booking booking, int busId);
}
