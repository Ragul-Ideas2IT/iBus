package com.i2i.ibus.service.impl;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.ibus.dto.BookingDto;
import com.i2i.ibus.dto.CancellationDto;
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
import com.i2i.ibus.service.BookingService;

/**
 * @author Esakkiraja E
 * @version 1.0
 * @created Nov 29 2022
 */
@Service
public class BookingServiceImpl implements BookingService {

    private BookingRepository bookingRepository;
    private BusRepository busRepository;
    private BusHistoryRepository busHistoryRepository;
    private PickupPointRepository pickupPointRepository;
    private SeatRepository seatRepository;
    private UserRepository userRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, PickupPointRepository pickupPointRepository,
	    BusRepository busRepository, BusHistoryRepository busHistoryRepository, UserRepository userRepository,
	    SeatRepository seatRepository) {
	this.bookingRepository = bookingRepository;
	this.busRepository = busRepository;
	this.userRepository = userRepository;
	this.busHistoryRepository = busHistoryRepository;
	this.pickupPointRepository = pickupPointRepository;
	this.seatRepository = seatRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BookingDto book(int userId, int busId, BookingDto bookingDto) {
	Booking booking = Mapper.toBooking(bookingDto);
	validatePickupPoints(booking, busId);
	booking.setBus(getBusById(busId));
	getBusHistoryByTravelDate(booking.getBus(), booking.getTravelDate());
	validateBookingDetails(booking.getBookingDetails(), busId);
	booking.setTotalFare(calculateFare(booking.getBookingDetails(), busId));
	booking.setDateTime(LocalDateTime.now());
	booking.setPaymentStatus("unpaid");
	booking.setStatus("upcoming");
	booking.setUser(getUserById(userId));
	bookingRepository.save(booking);
	return Mapper.toBookingDto(booking);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BookingDto> getAllBookings() {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public BookingDto getById(int id) {
	completeBooking(id);
	Booking booking = bookingRepository.findById(id).get();
	setSeatStatus(booking);
	return Mapper.toBookingDto(booking);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BookingDto> getByUserId(int userId) {
	validateUser(userId);
	List<Booking> bookings = bookingRepository.findAllByUserId(userId);
	List<BookingDto> bookingDtos = new ArrayList<BookingDto>();
	if (!bookings.isEmpty()) {

	    for (Booking booking : bookings) {
		setSeatStatus(booking);
		completeBooking(booking.getId());
		bookingDtos.add(Mapper.toBookingDto(booking));
	    }
	}
	return bookingDtos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Bus getBusById(int id) {
	validateBus(id);
	return busRepository.findById(id).get();
    }

    /**
     * @param id
     * @return
     */
    public User getUserById(int id) {
	validateUser(id);
	return userRepository.findById(id).get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BusHistory getBusHistoryByTravelDate(Bus bus, LocalDate travelDate) {
	Optional<BusHistory> busHistory = busHistoryRepository.findByBusIdAndDepartureDate(bus.getId(), travelDate);
	if (!busHistory.isPresent()) {
	    throw new IBusException("This bus is not departure on this date");
	}
	return busHistory.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CancellationDto cancel(int bookingId) {
	validateBooking(bookingId);
	Booking booking = bookingRepository.findById(bookingId).get();

	if (booking.getCancellation() == null) {
	    Cancellation cancellation = new Cancellation();
	    cancellation.setDateTime(LocalDateTime.now());
	    booking.setCancellation(cancellation);
	    bookingRepository.save(cancelBooking(booking));
	} else {
	    throw new IBusException("This booking already cancelled");
	}
	return Mapper.toCancellationDto(booking.getCancellation());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double calculateFare(List<BookingDetail> bookingDetails, int busId) {
	double fare = 0;

	for (BookingDetail bookingDetail : bookingDetails) {
	    fare += seatRepository.findBySeatNumberAndBusId(bookingDetail.getSeatNumber(), busId).get().getFare();
	}
	return fare;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Booking cancelBooking(Booking booking) {
	if (booking.getPaymentStatus().equals("unpaid")) {
	    booking.getCancellation().setRefundAmount(0);
	    booking.getCancellation().setRefundStatus("Not paid");
	} else {
	    long min = calculateDifferenceOfTime(getBusHistoryByTravelDate(booking.getBus(), booking.getTravelDate()));
	    if (min >= 600) {
		booking.getCancellation().setRefundAmount((booking.getTotalFare() - (booking.getTotalFare() * 0.1)));
	    } else {
		booking.getCancellation()
			.setRefundAmount(booking.getTotalFare() - (booking.getTotalFare() * (100 - (6000 / min))));
	    }
	    booking.getCancellation().setRefundStatus("Refunded...");
	}
	booking.getCancellation().setCancellationStatus("Cancelled");
	return booking;
    }

    /*
     * @Override
     */
    public List<BookingDto> getByBusId(int busId) {
	validateUser(busId);
	List<Booking> bookings = bookingRepository.findAllByUserId(busId);
	List<BookingDto> bookingDtos = new ArrayList<BookingDto>();
	if (!bookings.isEmpty()) {

	    for (Booking booking : bookings) {
		setSeatStatus(booking);
		completeBooking(booking.getId());
		bookingDtos.add(Mapper.toBookingDto(booking));
	    }
	}
	return bookingDtos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void completeBooking(int id) {
	validateBooking(id);
	Booking booking = bookingRepository.findById(id).get();
	if (calculateDifferenceOfTime(getBusHistoryByTravelDate(booking.getBus(), booking.getTravelDate())) <= 0) {
	    booking.setStatus("Completed");
	}
	bookingRepository.save(booking);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSeatStatus(Booking booking) {
	if (booking.getPaymentStatus().equals("success")) {
	    for (BookingDetail bookingDetail : booking.getBookingDetails()) {
		Seat seat = seatRepository
			.findBySeatNumberAndBusId(bookingDetail.getSeatNumber(), booking.getBus().getId()).get();
		seat.setOccupied(true);
	    }
	}
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long calculateDifferenceOfTime(BusHistory busHistory) {
	return ChronoUnit.MINUTES.between(LocalDateTime.now(),
		LocalDateTime.of(busHistory.getArrivingDate(), busHistory.getArrivingTime()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteBooking(int bookingId) {
	validateBooking(bookingId);
	bookingRepository.deleteById(bookingId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateUser(int id) {
	Optional<User> user = userRepository.findById(id);
	if (!user.isPresent()) {
	    throw new IBusException("User Id doesn't exists");
	}
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateBus(int id) {
	Optional<Bus> bus = busRepository.findById(id);
	if (!bus.isPresent()) {
	    throw new IBusException("Bus Id doesn't exists");
	}
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateBooking(int id) {
	Optional<Booking> booking = bookingRepository.findById(id);
	if (!booking.isPresent()) {
	    throw new IBusException("Booking Id doesn't exists");
	}
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validatePickupPoints(Booking booking, int busId) {
	Optional<PickupPoint> dropOff = pickupPointRepository.findAllByBusIdAndCityAndStopName(busId,
		booking.getDestination(), booking.getDropPoint());
	Optional<PickupPoint> pickUp = pickupPointRepository.findAllByBusIdAndCityAndStopName(busId,
		booking.getSource(), booking.getPickUpPoint());
	if (booking.getSource().equals(booking.getDestination())) {
	    throw new IBusException("Source and destination are same");
	}
	if (!dropOff.isPresent()) {
	    throw new IBusException("Invalid Drop off point in ".concat(booking.getDestination()));
	}
	if (!pickUp.isPresent()) {
	    throw new IBusException("Invalid Pick up point in ".concat(booking.getSource()));
	}
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateBookingDetails(List<BookingDetail> bookingDetails, int busId) {
	for (BookingDetail bookingDetail : bookingDetails) {
	    Optional<Seat> seat = seatRepository.findBySeatNumberAndBusId(bookingDetail.getSeatNumber(), busId);
	    if (!seat.isPresent()) {
		throw new IBusException("Seat is not available ".concat(bookingDetail.getSeatNumber()));
	    }
	    if (seat.get().isOccupied()) {
		throw new IBusException(bookingDetail.getSeatNumber().concat("seat is already booked"));
	    }
	    if (!bookingDetail.getGender().equalsIgnoreCase(seat.get().getGender())) {
		throw new IBusException("This seat is not for ".concat(bookingDetail.getGender()));
	    }
	}
    }
}
