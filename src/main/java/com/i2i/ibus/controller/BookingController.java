package com.i2i.ibus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.i2i.ibus.dto.BookingDto;
import com.i2i.ibus.dto.MessageDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.service.BookingService;

import jakarta.validation.Valid;

/**
 * @author Esakkiraja E
 * @version 1.0
 *
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

    @PostMapping("/users/{userId}/buses/{busId}")
    @ResponseStatus(value = HttpStatus.CREATED)
    private BookingDto addBooking(@PathVariable @Valid int userId, @PathVariable int busId, @RequestBody BookingDto bookingDto) {
	return bookingService.addBooking(userId, busId, bookingDto);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    private List<BookingDto> getAllBooking() throws IBusException {
	return bookingService.getAllBooking();
    }
    
    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    private BookingDto getBookingById(@PathVariable int id) {
	return bookingService.getBookingById(id);
    }

    @GetMapping("/users/{userId}")
    @ResponseStatus(value = HttpStatus.OK)
    private List<BookingDto> getBookingByUserId(@PathVariable int userId) {
	return bookingService.getBookingDtoByUserId(userId);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    private MessageDto deleteBooking(@PathVariable int id) {
	bookingService.deleteBooking(id);
	return new MessageDto("200", "Booking deleted sucessfully");
    }
    
    @PutMapping("/cancellations/{bookingId}")
    @ResponseStatus(value = HttpStatus.OK)
    private MessageDto cancellation(@PathVariable @Valid int bookingId) {
	bookingService.cancellation(bookingId);
	return new MessageDto("200", "Booking cancellation successfully");
    }
}
