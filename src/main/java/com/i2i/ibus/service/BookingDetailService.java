package com.i2i.ibus.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.ibus.dto.BookingDetailDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.model.Booking;
import com.i2i.ibus.model.BookingDetail;
import com.i2i.ibus.model.Bus;
import com.i2i.ibus.model.Seat;
import com.i2i.ibus.repository.BookingDetailRepository;
import com.i2i.ibus.repository.BookingRepository;
import com.i2i.ibus.repository.BusRepository;

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
    private ModelMapper mapper;

    @Autowired
    private BookingDetailService(BookingDetailRepository bookingDetailRepository, BookingRepository bookingRepository,
	    ModelMapper mapper) {
	this.bookingDetailRepository = bookingDetailRepository;
	this.bookingRepository = bookingRepository;
	this.mapper = mapper;
    }

    public void updateBookingDetails(int bookingId, List<BookingDetailDto> bookingDetailDtos) throws IBusException {
	Booking booking = findBooking(bookingId);
	List<BookingDetail> bookingDetails = bookingDetailDtoToBookingDetail(bookingDetailDtos);
	bookingDetails.forEach(bookingDetail -> bookingDetail.setBooking(booking));
	bookingDetails.forEach(bookingDetail -> bookingDetailRepository.save(bookingDetail));
    }

    public Booking findBooking(int bookingId) throws IBusException {
	return bookingRepository.findById(bookingId).orElseThrow(() -> new IBusException("No booking id found..."));
    }

    public List<BookingDetail> bookingDetailDtoToBookingDetail(List<BookingDetailDto> bookingDetailDtos) {
	return bookingDetailDtos.stream().map(bookingDetail -> mapper.map(bookingDetailDtos, BookingDetail.class))
		.toList();
    }

    public List<BookingDetailDto> bookingDetailToBookingDetailDto(List<BookingDetail> bookingDetails) {
	return bookingDetails.stream().map(bookingDetail -> mapper.map(bookingDetails, BookingDetailDto.class))
		.toList();
    }

    public BookingDetailDto createBookingDetail(int bookingId, int seatId, BookingDetailDto bookingDetailDto)
	    throws IBusException {
	Seat bookingSeat = null;
	BookingDetail bookingDetail = mapper.map(bookingDetailDto, BookingDetail.class);
	Booking booking = findBooking(bookingId);
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
	return mapper.map(bookingDetail, BookingDetailDto.class);
    }

    public List<BookingDetailDto> getBookingDetailsByBookId(int bookingId)
	    throws IBusException {
	findBooking(bookingId);
	return bookingDetailsToBookingDetailDtos(bookingDetailRepository.findAllById(bookingId));
    }

    public List<BookingDetailDto> bookingDetailsToBookingDetailDtos(List<BookingDetail> bookingDetails) {
	List<BookingDetailDto> bookingDetailDtos = new ArrayList<BookingDetailDto>();
	for (BookingDetail bookingDetail : bookingDetails) {
	    bookingDetailDtos.add(mapper.map(bookingDetail, BookingDetailDto.class));
	}
	return bookingDetailDtos;
    }

}