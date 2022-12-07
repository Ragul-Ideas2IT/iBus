package com.i2i.ibus.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.i2i.ibus.model.PickupPoint;
import com.i2i.ibus.model.Seat;
import com.i2i.ibus.model.User;
import com.i2i.ibus.repository.BookingRepository;
import com.i2i.ibus.repository.BusHistoryRepository;
import com.i2i.ibus.repository.BusRepository;
import com.i2i.ibus.repository.PickupPointRepository;
import com.i2i.ibus.repository.SeatRepository;
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
    private BusRepository busRepository;
    private BusHistoryRepository busHistoryRepository;
    private PickupPointRepository pickupPointRepository;
    private SeatRepository seatRepository;
    private UserRepository userRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, PickupPointRepository pickupPointRepository,
	    BusRepository busRepository, BusHistoryRepository busHistoryRepository, UserRepository userRepository,
	    SeatRepository seatRepository) {
	this.bookingRepository = bookingRepository;
	this.busRepository = busRepository;
	this.userRepository = userRepository;
	this.busHistoryRepository = busHistoryRepository;
	this.pickupPointRepository = pickupPointRepository;
	this.seatRepository = seatRepository;
    }

    public BookingDto addBooking(int userId, int busId, BookingDto bookingDto) {
	Booking booking = Mapper.toBooking(bookingDto);
	validatePickupPoints(booking,busId);
	validateBookingDetails(booking.getBookingDetails(), busId);
	booking.setTotalFare(calculateFare(booking.getBookingDetails(), busId));
	booking.setDateTime(LocalDateTime.now());
	booking.setBus(getBusById(busId));
	booking.setPaymentStatus("unpaid");
	booking.setStatus("upcoming");
	booking.setUser(getUserById(userId));
	bookingRepository.save(booking);
	return Mapper.toBookingDto(booking);
    }
    

    public List<BookingDto> getAllBooking() throws IBusException {
	List<Booking> bookings = bookingRepository.findAll();
	List<BookingDto> bookingDtos = new ArrayList<BookingDto>();
	if (!bookings.isEmpty()) {
	    for (Booking booking : bookings) {
		completeBooking(booking.getId());
		bookingDtos.add(Mapper.toBookingDto(booking));
	    }
	} else {
	    throw new IBusException("Booking doesn't exists");
	}
	return bookingDtos;
    }

    public BookingDto getBookingById(int id) {
	completeBooking(id);
	return Mapper.toBookingDto(bookingRepository.findById(id).get());
    }

    public List<BookingDto> getBookingByUserId(int userId) {
	validateUser(userId);
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

    public Bus getBusById(int id) {
	validateBus(id);
	return busRepository.findById(id).get();
    }

    public User getUserById(int id) {
	validateUser(id);
	return userRepository.findById(id).get();
    }

    public BusHistory getBusHistoryByTravelDate(Bus bus, LocalDate travelDate) {
	return busHistoryRepository.findByBusIdAndDepartureDate(bus.getId(), travelDate).get();
    }

    public void cancellation(int bookingId) {
	validateBooking(bookingId);
	Booking booking = bookingRepository.findById(bookingId).get();

	if (booking.getCancellation() == null) {
	    Cancellation cancellation = new Cancellation();
	    cancellation.setDateAndTime(LocalDateTime.now());
	    booking.setCancellation(cancelBooking(booking, cancellation));
	    bookingRepository.save(booking);
	}
    }

    public double calculateFare(List<BookingDetail> bookingDetails, int busId) {
	double fare = 0;

	for (BookingDetail bookingDetail : bookingDetails) {
	    fare += seatRepository.findBySeatNumberAndBusId(bookingDetail.getSeatNumber(), busId).get().getFare();
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
	validateBooking(id);
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

    public void deleteBooking(int bookingId) {
	validateBooking(bookingId);
	bookingRepository.deleteById(bookingId);
    }
    
    public void validateUser(int id) {
	Optional<User> user = userRepository.findById(id);
	if (!user.isPresent()) {
	    throw new IBusException("User Id doesn't exists");
	}
    }

    public void validateBus(int id) {
	Optional<Bus> bus = busRepository.findById(id);
	if (!bus.isPresent()) {
	    throw new IBusException("Bus Id doesn't exists");
	}
    }
    
    public void validateBooking(int id) {
	Optional<Booking> booking = bookingRepository.findById(id);
	if (!booking.isPresent()) {
	    throw new IBusException("Booking Id doesn't exists");
	}
    }
    
    private void validatePickupPoints(Booking booking, int busId) {
	Optional<PickupPoint> dropOff = pickupPointRepository.findAllByBusIdCityAndStopName(busId, booking.getDestination(), booking.getDropPoint());
	Optional<PickupPoint> pickUp = pickupPointRepository.findAllByBusIdCityAndStopName(busId, booking.getSource(), booking.getPickUpPoint());
	if(!dropOff.isPresent()) {
	    throw new IBusException("Invalid Drop off point in ".concat(booking.getDestination()));
	}
	if(!pickUp.isPresent()) {
	    throw new IBusException("Invalid Pick up point in ".concat(booking.getSource()));
	}    }

    private void validateBookingDetails(List<BookingDetail> bookingDetails, int busId) {
	for (BookingDetail bookingDetail : bookingDetails) {
	    Seat seat = seatRepository.findBySeatNumberAndBusId(bookingDetail.getSeatNumber(), busId).get();
	    if (!bookingDetail.getGender().equalsIgnoreCase(seat.getGender())) {
		throw new IBusException("This seat is not for ".concat(bookingDetail.getGender()));
	    }
	}
    }
    
}
