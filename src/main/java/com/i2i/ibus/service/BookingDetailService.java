package com.i2i.ibus.service;

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
    private BusRepository busRepository;
    private ModelMapper mapper;

    @Autowired
    private BookingDetailService(BookingDetailRepository bookingDetailRepository, BookingRepository bookingRepository,
	    BusRepository busRepository, ModelMapper mapper) {
	this.bookingDetailRepository = bookingDetailRepository;
	this.bookingRepository = bookingRepository;
	this.busRepository = busRepository;
	this.mapper = mapper;
    }

    public List<BookingDetailDto> createBookingDetails(int bookingId, List<BookingDetailDto> bookingDetailDtos)
	    throws IBusException {
	Booking booking = findBooking(bookingId);
	booking.setStatus("success");
	List<BookingDetail> bookingDetails = bookingDetailDtoToBookingDetail(bookingDetailDtos);
	bookingDetails.forEach(bookingDetail -> bookingDetail.setBooking(booking));
	bookingDetails.forEach(bookingDetail -> bookingDetailRepository.save(bookingDetail));
	return bookingDetailToBookingDetailDto(bookingDetails);
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

    public BookingDetailDto createbookingDetail(int bookingId, int busId, int seatId, BookingDetailDto bookingDetailDto)
	    throws IBusException {
	BookingDetail bookingDetail = mapper.map(bookingDetailDto, BookingDetail.class);
	//Bus bus = busRepository.findById(busId).orElseThrow(() -> new IBusException("No bus id found..."));
//	if(null != bookingDetailRepository.findBySeatId(bookingDetailDto.getSeatId())) {
//	    throw new IBusException("Seat is booked...");
//	}
	//bookingDetail.setSeat(null);
	return mapper.map(bookingDetailRepository.save(bookingDetail), BookingDetailDto.class);
    }

}