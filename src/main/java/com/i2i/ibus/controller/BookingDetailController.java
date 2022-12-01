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
    private ModelMapper modelMapper;

    @Autowired
    public BookingDetailController(BookingDetailService bookingDetailService, ModelMapper modelMapper) {
	this.bookingDetailService = bookingDetailService;
	this.modelMapper = modelMapper;
    }

    @PostMapping(value = "/bookings/{booking_id}")
    @ResponseStatus(value = HttpStatus.OK)
    private List<BookingDetailDto> createBookingDetails(@PathVariable("booking_id") int bookingId,
	    @RequestBody List<BookingDetailDto> bookingDetailDtos) throws IBusException {
	return bookingDetailService.createBookingDetails(bookingId, bookingDetailDtos);
    }

    @PutMapping(value = "/bookings/{booking_id}")
    private void updateBookingDetails(@PathVariable("booking_id") int bookingId,
	    @RequestBody List<BookingDetailDto> bookingDetailDtos) throws IBusException {
	bookingDetailService.updateBookingDetails(bookingId, bookingDetailDtos);
    }

}