package com.i2i.ibus.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
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

import com.i2i.ibus.dto.BookingDetailDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.service.BookingDetailService;

/**
 * CRUD operation for booking detail.
 * 
 * @author Tamilmani
 * @version 1.0
 * 
 * @created Nov 30 2022
 *
 */
@RestController
@RequestMapping("api/v1/bookingdetails/bookings/{bookingId}/seats/{seatId}")
public class BookingDetailController {

    private BookingDetailService bookingDetailService;

    /**
     * 
     * @param bookingDetailService
     */
    @Autowired
    private BookingDetailController(BookingDetailService bookingDetailService) {
	this.bookingDetailService = bookingDetailService;
    }

    @PostMapping
    private BookingDetailDto createBookingDetail(@PathVariable int bookingId, @PathVariable int seatId,
	    @RequestBody BookingDetailDto bookingDetailDto) throws IBusException {
	return bookingDetailService.createBookingDetail(bookingId, seatId, bookingDetailDto);
    }

    @GetMapping
    private List<BookingDetailDto> getBookingDetailsByBookingId(@PathVariable int bookingId) throws IBusException {
	return bookingDetailService.getBookingDetailsByBookId(bookingId);
    }

}