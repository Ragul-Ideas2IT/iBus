package com.i2i.ibus.service;

import java.time.LocalDate;
import java.util.List;

import com.i2i.ibus.dto.BookingDto;
import com.i2i.ibus.dto.CancellationDto;
import com.i2i.ibus.model.Booking;
import com.i2i.ibus.model.BookingDetail;
import com.i2i.ibus.model.Bus;
import com.i2i.ibus.model.Schedule;
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
     * Add a booking for a user.
     *
     * @param userId     The id of the user who is booking the ticket.
     * @param busId      The id of the bus that the user wants to book.
     * @param bookingDto This is the object that contains the details of the booking.
     * @return BookingDto
     */
    BookingDto book(int userId, int busId, BookingDto bookingDto);

    /**
     * Get all bookings
     *
     * @return List of BookingDto
     */
    List<BookingDto> getAllBookings();

    /**
     * This function returns a booking.
     *
     * @param id The id of the booking you want to retrieve.
     * @return A BookingDto object.
     */
    BookingDto getById(int id);

    /**
     * Get all bookings for a user.
     *
     * @param userId The id of the user who made the booking.
     * @return A list of BookingDto objects.
     */
    List<BookingDto> getByUserId(int userId);

    /**
     * This function returns a Bus object with the given id(bus).
     *
     * @param id The id of the bus you want to get.
     * @return A bus object
     */
    Bus getBusById(int id);

    /**
     * This function returns a User object with the given id(user).
     *
     * @param id The id of the user to be retrieved.
     * @return A User object
     */
    User getUserById(int id);

    /**
     * Get the bus history for a bus on a given travel date.
     *
     * @param bus        The bus object that you want to get the history for.
     * @param travelDate The date of travel
     * @return A Schedule object
     */
    Schedule getScheduleByTravelDate(Bus bus, LocalDate travelDate);

    /**
     * Method is used to Cancel a booking.
     *
     * @param bookingId The booking id of the booking that is being cancelled.
     * @return
     */
    CancellationDto cancel(int bookingId);

    /**
     * Given a list of booking details and a bus id, calculate the fare for the bus.
     *
     * @param bookingDetails List of BookingDetail objects.
     * @param busId The id of the bus for which the fare is to be calculated.
     * @return A double value
     */
    double calculateFare(List<BookingDetail> bookingDetails, int busId);

    /**
     * Method is used to Cancel a booking, calculate the refund.
     *
     * @param booking      The booking to be cancelled.
     *                     details.
     * @return A Cancellation object.
     */
    Booking cancelBooking(Booking booking);

    /**
     * Method is used to Checking the booking status.
     *
     * @param id The id of the booking to be completed.
     */
    void completeBooking(int id);

    /**
     * Method is used to sets the seat status of a booking.
     *
     * @param booking The booking object that contains the seat number and the
     *                status of the seat.
     */
    void setSeatStatus(Booking booking);

    /**
     * Method is used to calculates the difference of time between the current time
     * and time(bus arriving time), then it convert into minutes.
     *
     * @param Schedule The Schedule object that contains the time of the bus.
     * @return The difference of time between the current time and the time the bus
     *      was last seen.
     */
    
    long calculateDifferenceOfTime(Schedule Schedule);

    /**
     * Delete a booking with the given bookingId.
     *
     * @param bookingId The id of the booking to be deleted.
     */
    void deleteBooking(int bookingId);

    /**
     * Method is used to validate the user with the given id(userId)
     *
     * @param id The id of the user to validate.
     */
    void validateUser(int id);

    /**
     * Method is used to Validate the bus with the given id(busId).
     *
     * @param id The id of the bus to be validated.
     */
    void validateBus(int id);

    /**
     * Methos is used to Validate a bookingId present or not.
     *
     * @param id The id of the booking to be validated.
     */
    void validateBooking(int id);

    /**
     * Method is used to Validate the pickup points of a booking.
     *
     * @param booking The booking object that contains the pickup points.
     * @param busId   The id of the bus that the user has selected.
     */
    void validateStops(Booking booking, int busId);

    /**
     * Method is used to Validate the Seats(BookingDetails).
     *
     * @param bookingDetails
     * @param busId
     */
    void validateBookingDetails(List<BookingDetail> bookingDetails, int busId);

    /*
     * Get all bookings for a id(bus).
     *
     * @param userId The id of the user who made the booking.
     * 
     * @return A list of BookingDto objects.
     */
    List<BookingDto> getByBusId(int busId);
}
