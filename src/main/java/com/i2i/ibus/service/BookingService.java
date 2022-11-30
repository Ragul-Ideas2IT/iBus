package com.i2i.ibus.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.ibus.dto.BookingDto;
import com.i2i.ibus.model.Booking;
import com.i2i.ibus.model.Cancellation;
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
	if (!bookingDetail.isEmpty()) {
	    for (Booking booking : bookingDetail) {
		bookingDtos.add(mapper.map(booking, BookingDto.class));
	    }
	}
	return bookingDtos;
    }

    public void deleteBookind(int id) {
        bookingRepository.deleteById(id);
    }

    public BookingDto searchBooking(int id) {
	return mapper.map(bookingRepository.findById(id).get(), BookingDto.class);
    }

    public void cancellation(int id) {
	Booking booking = mapper.map(searchBooking(id), Booking.class);
	if (booking.getCancellation() == null) {
	    Cancellation cancellation = new Cancellation();
	    cancellation.setCanceled(true);
	    booking.setCancellation(cancellation);
	    bookingRepository.save(booking);
	}
    }
}
