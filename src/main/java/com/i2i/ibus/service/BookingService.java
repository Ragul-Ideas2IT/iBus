package com.i2i.ibus.service;

import java.time.LocalDateTime;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.ibus.dto.BookingDto;
import com.i2i.ibus.model.Booking;
import com.i2i.ibus.model.BookingDetail;
import com.i2i.ibus.model.Bus;
import com.i2i.ibus.model.BusHistory;
import com.i2i.ibus.model.Cancellation;
import com.i2i.ibus.repository.BookingRepository;
import com.i2i.ibus.repository.BusRepository;
import com.i2i.ibus.repository.PaymentRepository;

/**
 * @author Esakkiraja E
 * @version 1.0
 *
 * @created Nov 29 2022
 */
@Service
public class BookingService {

    private BookingRepository bookingRepository;
    private PaymentRepository paymentRepository;
    private BusRepository busRepository;
    private ModelMapper mapper;

    @Autowired
    public BookingService(BookingRepository bookingRepository, ModelMapper mapper, PaymentRepository paymentRepository,
	    BusRepository busRepository) {
	this.bookingRepository = bookingRepository;
	this.mapper = mapper;
	this.paymentRepository = paymentRepository;
	this.busRepository = busRepository;
    }

    public BookingDto addBooking(int busId, BookingDto bookingDto) {
	Booking booking = mapper.map(bookingDto, Booking.class);
	booking.setDateTime(LocalDateTime.now());
	booking.setBus(getBusById(busId));
	booking.setPaymentStatus("unpaid");
	booking.setStatus("upcoming");
	bookingRepository.save(booking);
	return mapper.map(booking, BookingDto.class);
    }

    public List<BookingDto> getAllBooking() {
	List<Booking> bookingDetail = bookingRepository.findAll();
	List<BookingDto> bookingDtos = new ArrayList<BookingDto>();
	if (!bookingDetail.isEmpty()) {
	    for (Booking booking : bookingDetail) {
		completeBooking(booking.getId());
		bookingDtos.add(mapper.map(booking, BookingDto.class));
	    }
	}
	return bookingDtos;
    }

    public void deleteBooking(int bookingId) {
	bookingRepository.deleteById(bookingId);
    }

    public BookingDto getBookingDto(int id) {
	completeBooking(id);
	return mapper.map(bookingRepository.findById(id).get(), BookingDto.class);
    }

    public void cancellation(int bookingId) {
	Booking booking = bookingRepository.findById(bookingId).get();
	if (booking.getCancellation() == null) {

	    Cancellation cancellation = new Cancellation();
	    cancellation.setDateAndTime(LocalDateTime.now());
	    booking.setCancellation(cancelBooking(booking, cancellation));
	    bookingRepository.save(booking);
	}
    }

    public double calculateFare(List<BookingDetail> bookingDetails) {
	double fare = 0;
	for (BookingDetail bookingDetail : bookingDetails) {
	    fare += bookingDetail.getSeat().getFare();
	}
	return fare;
    }

    public Cancellation cancelBooking(Booking booking, Cancellation cancellation) {
	long min = calculateDifferenceOfTime(
		getBusHistoryByTravelDateTime(booking.getBus(), booking.getTravelDateAndTime()).getArrivingDateTime());
	if (min >= 600) {
	    cancellation.setRefundAmount((booking.getTotalFare() - (booking.getTotalFare() * 0.1)));
	} else {
	    cancellation.setRefundAmount(booking.getTotalFare() - (booking.getTotalFare() * (100 - (6000 / min))));
	}
	cancellation.setCancelled(true);
	return cancellation;
    }

    public void completeBooking(int id) {
	Booking booking = bookingRepository.findById(id).get();
	if (calculateDifferenceOfTime(getBusHistoryByTravelDateTime(booking.getBus(), booking.getTravelDateAndTime())
		.getArrivingDateTime()) <= 0) {
	    booking.setStatus("Completed");
	}
	bookingRepository.save(booking);
    }

    public long calculateDifferenceOfTime(LocalDateTime dateTime) {
	return ChronoUnit.MINUTES.between(LocalDateTime.now(), dateTime);
    }

    public Bus getBusById(int id) {
	return busRepository.findById(id);
    }

    public BusHistory getBusHistoryByTravelDateTime(Bus bus, LocalDateTime travelDateTime) {
	BusHistory busHistory1 = null;
	for (BusHistory busHistory : bus.getBusHistories()) {
	    if (travelDateTime.equals(busHistory.getDepartureDateTime())) {
		busHistory1 = busHistory;
	    }
	}
	return busHistory1;
    }

}
