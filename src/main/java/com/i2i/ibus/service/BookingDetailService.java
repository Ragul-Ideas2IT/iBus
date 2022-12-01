package com.i2i.ibus.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.ibus.dto.BookingDetailDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.model.Booking;
import com.i2i.ibus.model.BookingDetail;
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
    private ModelMapper mapper;

    @Autowired
    private BookingDetailService(BookingDetailRepository bookingDetailRepository, BookingRepository bookingRepository,
	    ModelMapper mapper) {
	this.bookingDetailRepository = bookingDetailRepository;
	this.bookingRepository = bookingRepository;
	this.mapper = mapper;
    }

    public List<BookingDetailDto> createBookingDetails(int bookingId, List<BookingDetailDto> bookingDetailDtos)
	    throws IBusException {
	Booking booking;
	try {
	    booking = bookingRepository.findById(bookingId).orElseThrow();
	} catch (NoSuchElementException noBookingFound) {
	    throw new IBusException("No booking id found...");
	}
	List<BookingDetail> bookingDetails = bookingDetailDtos.stream()
		.map(bookingDetail -> mapper.map(bookingDetailDtos, BookingDetail.class)).toList();
	bookingDetails.stream().forEach(bookingDetail -> bookingDetail.setBooking(booking));
	bookingDetails.forEach(bookingDetail -> bookingDetailRepository.save(bookingDetail));
	return bookingDetails.stream().map(bookingDetail -> mapper.map(bookingDetails, BookingDetailDto.class))
		.toList();
    }

    public void updateBookingDetails(int bookingId, List<BookingDetailDto> bookingDetailDtos) throws IBusException {
	Booking booking;
	try {
	    booking = bookingRepository.findById(bookingId).orElseThrow();
	} catch (NoSuchElementException noBookingFound) {
	    throw new IBusException("No booking id found...");
	}
	List<BookingDetail> bookingDetails = bookingDetailDtos.stream()
		.map(bookingDetail -> mapper.map(bookingDetailDtos, BookingDetail.class)).toList();
	bookingDetails.stream().forEach(bookingDetail -> bookingDetail.setBooking(booking));
	bookingDetails.forEach(bookingDetail -> bookingDetailRepository.save(bookingDetail));
    }

}