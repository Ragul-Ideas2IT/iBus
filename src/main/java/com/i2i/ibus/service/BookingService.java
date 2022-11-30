package com.i2i.ibus.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.i2i.ibus.dto.BookingDto;
import com.i2i.ibus.model.Booking;
import com.i2i.ibus.repository.BookingRepository;

/*
 * Author ESAKKIRAJA 
 */
@Service
public class BookingService {
	@Autowired
	private BookingRepository bookingRepository;
	
	private ModelMapper mapper = new ModelMapper();

	public BookingDto addBooking(BookingDto bookingDto) {
		Booking booking = mapper.map(bookingDto, Booking.class);
		bookingRepository.save(booking);
		return mapper.map(booking, BookingDto.class);
	}

	public List<BookingDto> getAllBooking() {
		List<Booking> bookingDetail = bookingRepository.findAll();
		List<BookingDto> bookingDtos = new ArrayList<BookingDto>(); 
		if(!bookingDetail.isEmpty()) {
			for(Booking booking : bookingDetail) {
				bookingDtos.add(mapper.map(booking, BookingDto.class));
			}
		}
		return bookingDtos;
	}

	public void deleteBookind(@PathVariable int bookingId) {
		bookingRepository.deleteById(bookingId);
	} 
	
//	public Booking getBookingIdPresent(int bookingId) {
//		return bookingRepository.findById(bookingId);
//		
//	}

}
