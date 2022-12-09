package com.i2i.ibus.controller;

import java.util.List;

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

import com.i2i.ibus.dto.BookingDto;
import com.i2i.ibus.dto.CancellationDto;
import com.i2i.ibus.dto.MessageDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.service.BookingService;

import jakarta.validation.Valid;

/**
 * @author Esakkiraja E
 * @version 1.0
 * @created Nov 29 2022
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
     * > Add a booking for a user on a bus
     *
     * @param userId     The id of the user who is making the booking.
     * @param busId      The id of the bus that the user wants to book.
     * @param bookingDto This is the object that will be sent in the request body.
     * @return BookingDto
     */
    @PostMapping("/users/{userId}/buses/{busId}")
    @ResponseStatus(value = HttpStatus.CREATED)
    private BookingDto addBooking(@PathVariable @Valid int userId, @PathVariable int busId, @RequestBody BookingDto bookingDto) {
        return bookingService.addBooking(userId, busId, bookingDto);
    }

    /**
     * > This function returns a list of all the bookings in the database
     *
     * @return List of BookingDto
     */
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    private List<BookingDto> getAllBookings() throws IBusException {
        return bookingService.getAllBooking();
    }

    /**
     * > This function returns a booking by id
     *
     * @param id The id of the booking to be retrieved.
     * @return A BookingDto object
     */
    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    private BookingDto getById(@PathVariable int id) {
        return bookingService.getBookingById(id);
    }

    /**
     * This function returns a list of bookingDto objects that are associated with the userId passed in as a parameter
     *
     * @param userId The id of the user whose bookings are to be fetched.
     * @return A list of BookingDto objects.
     */
    @GetMapping("/users/{userId}")
    @ResponseStatus(value = HttpStatus.OK)
    private List<BookingDto> getByUserId(@PathVariable int userId) {
        return bookingService.getBookingByUserId(userId);
    }

    /**
     * It deletes a booking with the given id.
     *
     * @param id The id of the booking to be deleted.
     * @return A MessageDto object with a status code and a message.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    private MessageDto deleteBooking(@PathVariable int id) {
        bookingService.deleteBooking(id);
        return new MessageDto("200", "Booking deleted sucessfully");
    }

    /**
     * It cancels the booking with the given bookingId.
     *
     * @param bookingId The booking id of the booking to be cancelled.
     * @return A MessageDto object with a status code and a message.
     */
    @PostMapping("/cancellations/{bookingId}")
    @ResponseStatus(value = HttpStatus.OK)
    private CancellationDto cancellation(@PathVariable @Valid int bookingId) {
        return bookingService.cancellation(bookingId);
    }
}
