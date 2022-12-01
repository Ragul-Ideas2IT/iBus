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
import com.i2i.ibus.service.BookingService;

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
    public BookingController(BookingService bookingService) {
	this.bookingService = bookingService;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public BookingDto addBooking(@RequestBody BookingDto bookingDto) {
	return bookingService.addBooking(bookingDto);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<BookingDto> getAllBooking() {
	return bookingService.getAllBooking();
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public BookingDto searchBooking(@PathVariable int id) {
	return bookingService.searchBooking(id);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteBooking(@PathVariable int id) {
	bookingService.deleteBooking(id);
    }
    
    @PutMapping("/cancellations/{bookingId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void cancellation(@PathVariable int bookingId) {
	bookingService.cancellation(bookingId);
    }
}
