package com.i2i.ibus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.i2i.ibus.dto.BookingDto;
import com.i2i.ibus.service.BookingService;

/*
 * Author ESAKKIRAJA 
 */
@RestController
@RequestMapping("api/v1/booking")
public class BookingController {

	private BookingService bookingService;
	
	@Autowired
	public BookingController (BookingService bookingService) {
		this.bookingService = bookingService;
	}
	
	@PostMapping
	public BookingDto addBooking(@RequestBody BookingDto bookingDto ) {
		return bookingService.addBooking(bookingDto);
	}
	
	@GetMapping
	public List<BookingDto> getAllBooking(){
		return bookingService.getAllBooking();
	}
	
    @DeleteMapping
	public void deleteBooking(@PathVariable int  bookingId) {
		 bookingService.deleteBookind(bookingId);
	}
	
	
}
