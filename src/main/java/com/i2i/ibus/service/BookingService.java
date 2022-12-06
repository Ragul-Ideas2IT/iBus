package com.i2i.ibus.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.ibus.dto.BookingDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.Booking;
import com.i2i.ibus.model.BookingDetail;
import com.i2i.ibus.model.Bus;
import com.i2i.ibus.model.BusHistory;
import com.i2i.ibus.model.Cancellation;
import com.i2i.ibus.model.User;
import com.i2i.ibus.repository.BookingDetailRepository;
import com.i2i.ibus.repository.BookingRepository;
import com.i2i.ibus.repository.BusRepository;
import com.i2i.ibus.repository.UserRepository;

/**
 * @author Esakkiraja E
 * @version 1.0
 *
 * @created Nov 29 2022
 */
@Service
public class BookingService {

    private BookingRepository bookingRepository;
    private BookingDetailRepository bookingDetailRepository;
    private BusRepository busRepository;
    private UserRepository userRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, BookingDetailRepository bookingDetailRepository,
	    BusRepository busRepository, UserRepository userRepository) {
	this.bookingRepository = bookingRepository;
	this.bookingDetailRepository = bookingDetailRepository;
	this.busRepository = busRepository;
	this.userRepository = userRepository;
    }

    public BookingDto addBooking(int userId, int busId, BookingDto bookingDto) {
	Booking booking = Mapper.toBooking(bookingDto);
	booking.setDateTime(LocalDateTime.now());
	booking.setBus(getBusById(busId));
	booking.setPaymentStatus("unpaid");
	booking.setStatus("upcoming");
	booking.setUser(getUserById(userId));
	bookingRepository.save(booking);
	return Mapper.toBookingDto(booking);
    }

    public List<BookingDto> getAllBooking() throws IBusException {
	List<Booking> bookingDetail = bookingRepository.findAll();
	List<BookingDto> bookingDtos = new ArrayList<BookingDto>();

	if (!bookingDetail.isEmpty()) {

	    for (Booking booking : bookingDetail) {
		completeBooking(booking.getId());
		bookingDtos.add(Mapper.toBookingDto(booking));
	    }
	} else {
	    throw new IBusException("Booking doesn't exists");
	}
	return bookingDtos;
    }

    public void deleteBooking(int bookingId) {
	bookingRepository.deleteById(bookingId);
	bookingDetailRepository.deleteAllByBookingId(bookingId);
    }

    public BookingDto getBookingDtoById(int id) {
	completeBooking(id);
	return Mapper.toBookingDto(bookingRepository.findById(id).get());
    }

    public List<BookingDto> getBookingDtoByUserId(int userId) {
	List<Booking> bookingDetail = bookingRepository.findAllByUserId(userId);
	List<BookingDto> bookingDtos = new ArrayList<BookingDto>();

	if (!bookingDetail.isEmpty()) {

	    for (Booking booking : bookingDetail) {
		completeBooking(booking.getId());
		bookingDtos.add(Mapper.toBookingDto(booking));
	    }
	}
	return bookingDtos;
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
	long min = calculateDifferenceOfTime(getBusHistoryByTravelDate(booking.getBus(), booking.getTravelDate()));

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

	if (calculateDifferenceOfTime(getBusHistoryByTravelDate(booking.getBus(), booking.getTravelDate())) <= 0) {
	    booking.setStatus("Completed");
	}
	bookingRepository.save(booking);
    }

    public long calculateDifferenceOfTime(BusHistory busHistory) {
	return ChronoUnit.MINUTES.between(LocalDateTime.now(),
		LocalDateTime.of(busHistory.getArrivingDate(), busHistory.getArrivingTime()));
    }

    public Bus getBusById(int id) {
	return busRepository.findById(id);
    }

    public User getUserById(int id) {
	return userRepository.findById(id).get();
    }

    public BusHistory getBusHistoryByTravelDate(Bus bus, LocalDate travelDate) {
	BusHistory busHistory1 = null;

	for (BusHistory busHistory : bus.getBusHistories()) {

	    if (travelDate.equals(busHistory.getDepartureDate())) {
		busHistory1 = busHistory;
	    }
	}
	return busHistory1;
    }

    public BookingDto getBookingById(int id) {
	return Mapper.toBookingDto(bookingRepository.findById(id).get());
    }
}
