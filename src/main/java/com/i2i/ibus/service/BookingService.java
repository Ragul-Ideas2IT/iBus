package com.i2i.ibus.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.i2i.ibus.dto.BookingDto;
import com.i2i.ibus.model.Booking;
import com.i2i.ibus.model.Cancellation;
import com.i2i.ibus.repository.BookingRepository;

/**
 * @author Esakkiraja E
 * @version 1.0
 *
 * @created Nov 29 2022
 */
@Service
public class BookingService {

    private BookingRepository bookingRepository;
    private ModelMapper mapper;

    @Autowired
    public BookingService(BookingRepository bookingRepository, ModelMapper mapper) {
	this.bookingRepository = bookingRepository;
	this.mapper = mapper;
    }

    public BookingDto addBooking(BookingDto bookingDto) {
	Booking booking = mapper.map(bookingDto, Booking.class);
	booking.setDateTime(LocalDateTime.now());
	bookingRepository.save(booking);
	return mapper.map(booking, BookingDto.class);
    }

    public List<BookingDto> getAllBooking() {
	List<Booking> bookingDetail = bookingRepository.findAll();
	List<BookingDto> bookingDtos = new ArrayList<BookingDto>();
	if (!bookingDetail.isEmpty()) {
	    for (Booking booking : bookingDetail) {
		bookingDtos.add(mapper.map(booking, BookingDto.class));
	    }
	}
	return bookingDtos;
    }

    public void deleteBooking(int bookingId) {
	bookingRepository.deleteById(bookingId);
    }

    public BookingDto searchBooking(int id) {
	return mapper.map(bookingRepository.findById(id).get(), BookingDto.class);
    }

    public void cancellation(int id) {
	Booking booking = mapper.map(searchBooking(id), Booking.class);
	if (booking.getCancellation() == null) {
	    Cancellation cancellation = new Cancellation();
	    cancellation.setStatus(true);
	    booking.setCancellation(cancellation);
	    bookingRepository.save(booking);
	}
    }
}
