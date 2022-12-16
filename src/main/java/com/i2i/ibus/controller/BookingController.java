/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.i2i.ibus.controller;

import com.i2i.ibus.constants.Constants;
import com.i2i.ibus.dto.BookingDto;
import com.i2i.ibus.dto.CancellationDto;
import com.i2i.ibus.dto.MessageDto;
import com.i2i.ibus.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

/**
 * class for booking that creates, displays and deletes the bookings.
 *
 * @author Esakkiraja E
 * @version 1.0
 * @since Nov 29 2022
 */
@RestController
@RequestMapping("api/v1/bookings")
public class BookingController {

    private BookingService bookingService;

    @Autowired
    private BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * Add a booking for a user.
     *
     * @param bookingDto This is the object that will be sent in the request body.
     * @return BookingDto
     */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    private BookingDto book(@RequestBody BookingDto bookingDto) {
        return bookingService.book(bookingDto);
    }

    /**
     * Get all bookings. This function returns a list of all the bookings.
     *
     * @return List of BookingDto
     */
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    private List<BookingDto> getAllBookings() {
        return bookingService.getAllBookings();
    }

    /**
     * Get all booking for booking for id(booking). This function returns a
     * bookingDto
     *
     * @param id The id of the booking to be retrieved.
     * @return A BookingDto object
     */
    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    private BookingDto getById(@PathVariable int id) {
        return bookingService.getById(id);
    }

    /**
     * Get all booking for id(user). This function returns a list of bookingDto
     *
     * @param userId The id of the user whose bookings are to be fetched.
     * @return A list of BookingDto objects.
     */
    @GetMapping("/users/{userId}")
    @ResponseStatus(value = HttpStatus.OK)
    private List<BookingDto> getByUserId(@PathVariable int userId) {
        return bookingService.getByUserId(userId);
    }

    /**
     * Get all booking for id(bus). This function returns a list of bookingDto
     *
     * @return A list of BookingDto objects.
     */
    @GetMapping("/buses/{busId}")
    @ResponseStatus(value = HttpStatus.OK)
    private List<BookingDto> getByBusId(@PathVariable int busId) {
        return bookingService.getByBusId(busId);
    }

    /**
     * It deletes a booking with the given bookingId.
     *
     * @param id The id of the booking to be deleted.
     * @return A MessageDto object with a status code and a message.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    private MessageDto deleteBooking(@PathVariable int id) {
        bookingService.deleteBooking(id);
        return new MessageDto(Constants.EVERYTHING_IS_OK, Constants.DELETE_MESSAGE);
    }

    /**
     * It cancels the booking with the given bookingId.
     *
     * @param bookingId The booking id of the booking to be cancelled.
     * @return A MessageDto object with a status code and a message.
     */
    @PostMapping("/cancellations/{bookingId}")
    @ResponseStatus(value = HttpStatus.OK)
    private CancellationDto cancel(@PathVariable @Valid int bookingId) {
        return bookingService.cancel(bookingId);
    }

    /**
     * Get all booking for id(bus) and travel date. This function returns a list of
     * bookingDto
     *
     * @param busId
     * @param travelDate
     * @return A list of BookingDto objects.
     */
    @GetMapping("/buses/{busId}/{travelDate}")
    @ResponseStatus(value = HttpStatus.OK)
    private List<BookingDto> getByBusIdAndTravelDate(@PathVariable int busId, @PathVariable String travelDate) {
        return bookingService.getByBusIdAndTravelDate(busId, LocalDate.parse(travelDate));
    }
}
