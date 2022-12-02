package com.i2i.ibus.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("api/v1/bookingdetails")
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

    @PostMapping("/bookings/{bookingId}/buses/{busId}/seats/{seatId}")
    private BookingDetailDto createBookingDetail(@PathVariable int bookingId, @PathVariable int busId,
	    @PathVariable int seatId, @RequestBody BookingDetailDto bookingDetailDto) throws IBusException {
	return bookingDetailService.createbookingDetail(bookingId, busId, seatId, bookingDetailDto);
    }

    @PostMapping("/bookings/{bookingId}")
    @ResponseStatus(value = HttpStatus.OK)
    private List<BookingDetailDto> createBookingDetails(@PathVariable int bookingId,
	    @RequestBody List<BookingDetailDto> bookingDetailDtos) throws IBusException {
	return bookingDetailService.createBookingDetails(bookingId, bookingDetailDtos);
    }

    @PutMapping("/bookings/{bookingId}")
    @ResponseStatus(value = HttpStatus.OK)
    private void updateBookingDetails(@PathVariable int bookingId,
	    @RequestBody List<BookingDetailDto> bookingDetailDtos) throws IBusException {
	bookingDetailService.updateBookingDetails(bookingId, bookingDetailDtos);
    }

}