/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.service;

import java.time.LocalDate;
import java.util.List;

import com.i2i.ibus.dto.BookingDto;
import com.i2i.ibus.dto.CancellationDto;
import com.i2i.ibus.model.Booking;
import com.i2i.ibus.model.BookingDetail;
import com.i2i.ibus.model.Bus;
import com.i2i.ibus.model.Schedule;
import com.i2i.ibus.model.User;

/**
 * Used to manipulate the Booking details in the application. Operators are
 * manipulate the booking details.
 *
 * @author Esakkiraja E
 * @version 1.0
 * @since Nov 29 2022
 */
public interface BookingService {

    /**
     * Method is used to add a bus booking for a user.
     *
     * @param bookingDto This is the object that contains the details of the
     *                   booking.
     * @return BookingDto
     */
    BookingDto book(BookingDto bookingDto);

    /**
     * Get all bookings
     *
     * @return List of BookingDto
     */
    List<BookingDto> getAllBookings();

    /**
     * Method is used to get booking details for a bookingId returns a booking
     * details.
     *
     * @param id The id of the booking you want to retrieve.
     * @return A BookingDto object.
     */
    BookingDto getById(int id);

    /**
     * Method is used to get all booking details for a userId and return list of
     * bookingsDto.
     *
     * @param userId The id of the user who made the booking.
     * @return A list of BookingDto objects.
     */
    List<BookingDto> getByUserId(int userId);

    /**
     * The method used to check bus details and returns a Bus object with the given
     * id(bus).
     *
     * @param id The id of the bus you want to get.
     * @return A bus object
     */
    Bus getBusById(int id);

    /**
     * The method used to check user details and returns a User object with the
     * given id(user).
     *
     * @param id The id of the user to be retrieved.
     * @return A User object
     */
    User getUserById(int id);

    /**
     * Method is used to get the bus departure date for a bus schedule and check the
     * booking travel date.
     *
     * @param bus        The bus object that you want to get the schedule for.
     * @param travelDate The date of travel
     * @return A Schedule object
     */
    Schedule getScheduleByTravelDate(Bus bus, LocalDate travelDate);

    /**
     * Method is used to cancels a booking and returns a cancellation DTO.
     *
     * @param bookingId The id of the booking to be cancelled.
     * @return A BookingCancellationDto object.
     */
    CancellationDto cancel(int bookingId);

    /**
     * Method is used to calculate the seat fare for the bus.
     *
     * @param bookingDetails List of BookingDetail objects.
     * @param busId          The id of the bus for which the fare is to be
     *                       calculated.
     * @return A double value
     */
    double calculateFare(List<BookingDetail> bookingDetails, int busId);

    /**
     * Method is used to cancel a bus booking and calculate the refund amount.
     *
     * @param booking The booking to be cancelled. details.
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
     *         was last seen.
     */

    long calculateDifferenceOfTime(Schedule Schedule);

    /**
     * Delete a booking with the given bookingId.
     *
     * @param bookingId The id of the booking to be deleted.
     */
    void deleteBooking(int bookingId);

    /**
     * Methos is used to Validate a bookingId present or not.
     *
     * @param id The id of the booking to be validated.
     * @return Booking
     */
    Booking validateBooking(int id);

    /**
     * Method is used to Validate the pickup points of a booking.
     *
     * @param booking The booking object that contains the pickup points.
     * @param busId   The id of the bus that the user has selected.
     */
    void validateStops(Booking booking, int busId);

    /**
     * Method is used to check the seat availablity and check the gender of
     * bookingDetails.
     *
     * @param bookingDetails
     * @param busId
     */
    void validateBookingDetails(List<BookingDetail> bookingDetails, int busId);

    /**
     * Get all bookings for a id(bus).
     *
     * @param busId The id of the bus who made the booking.
     * @return A list of BookingDto objects.
     */
    List<BookingDto> getByBusId(int busId);

    /**
     * Get all bookings for a id(bus) and Travel date.
     *
     * @param busId The id of the bus who made the booking.
     * @param travel date This date is booking taravel date.
     * @return A list of BookingDto objects.
     */
    List<BookingDto> getByBusIdAndTravelDate(int busId, LocalDate date);
}
