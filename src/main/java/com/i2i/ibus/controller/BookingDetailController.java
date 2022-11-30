package com.i2i.ibus.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
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

import com.i2i.ibus.dto.PassengerDto;
import com.i2i.ibus.service.BookingDetailService;

/**
 * CRUD operation for passenger.
 * 
 * @author Tamilmani
 * @version 1.0
 * 
 * @created Nov 30 2022
 *
 */
@RestController
@RequestMapping("bookingdetail")
public class BookingDetailController {

    private BookingDetailService bookingDetailService;
    private ModelMapper modelMapper;

    @Autowired
    public BookingDetailController(BookingDetailService bookingDetailService,
	    ModelMapper modelMapper) {
    	this.bookingDetailService = bookingDetailService;
    	this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    private List<PassengerDto> createPassenger(@PathVariable("booking_id")
            int bookingId, @RequestBody List<PassengerDto> passengerDtos) {
    	return bookingDetailService.createPassenger(bookingId, passengerDtos);
    }

    @GetMapping
    private List<PassengerDto> readAllPassengers() {
	return bookingDetailService.findAllPassengers();
    }

    @DeleteMapping
    private void deleteAllPassenger() {
	bookingDetailService.deleteAllPassenger();
    }

}