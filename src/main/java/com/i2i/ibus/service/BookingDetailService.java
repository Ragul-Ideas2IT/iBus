package com.i2i.ibus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.ibus.dto.BookingDetailDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.Booking;
import com.i2i.ibus.model.BookingDetail;
import com.i2i.ibus.model.Bus;
import com.i2i.ibus.model.Seat;
import com.i2i.ibus.repository.BookingDetailRepository;
import com.i2i.ibus.repository.BookingRepository;

/**
 * @author Tamilmani
 * @version 1.0
 * 
 * @created Nov 30 2022
 *
 */
@Service
public class BookingDetailService {

    private BookingDetailRepository bookingDetailRepository;
    private BookingRepository bookingRepository;

    @Autowired
    private BookingDetailService(BookingDetailRepository bookingDetailRepository, BookingRepository bookingRepository) {
	this.bookingDetailRepository = bookingDetailRepository;
	this.bookingRepository = bookingRepository;
    }

    public BookingDetailDto createBookingDetail(int bookingId, int seatId, BookingDetailDto bookingDetailDto)
	    throws IBusException {
	Seat bookingSeat = null;
	BookingDetail bookingDetail = Mapper.toBookingDetail(bookingDetailDto);
	Booking booking = findBookingByBookingId(bookingId);
	Bus bus = booking.getBus();
	for (Seat seat : bus.getSeats()) {
	    if (seat.getId() == seatId) {
		bookingSeat = seat;
	    }
	}
	if (null == bookingDetailRepository.findBySeatId(seatId)) {
	    if (bookingSeat.getGender().equalsIgnoreCase(bookingDetailDto.getGender())) {
		bookingDetail.setBooking(booking);
		bookingDetail.setSeat(bookingSeat);
		bookingDetail = bookingDetailRepository.save(bookingDetail);
	    } else {
		throw new IBusException("this seat is only for ".concat(bookingDetail.getGender()));
	    }
	} else {
	    throw new IBusException("Seat is already booked..");
	}
	return Mapper.toBookingDetailDto(bookingDetail);
    }

    public List<BookingDetailDto> getBookingDetailsByBookingId(int bookingId) throws IBusException {
	findBookingByBookingId(bookingId);
	return Mapper.toBookingDetailDtos(bookingDetailRepository.findAllById(bookingId));
    }

    public Booking findBookingByBookingId(int bookingId) throws IBusException {
	return bookingRepository.findById(bookingId).orElseThrow(() -> new IBusException("No booking id found..."));
    }

    public void deleteAllByBookingId(int bookingId) {
	bookingDetailRepository.deleteAllByBookingId(bookingId);
    }

}