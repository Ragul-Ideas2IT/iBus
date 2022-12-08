package com.i2i.ibus.service.impl;

import com.i2i.ibus.dto.BookingDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.*;
import com.i2i.ibus.repository.BookingRepository;
import com.i2i.ibus.repository.BusRepository;
import com.i2i.ibus.repository.BusHistoryRepository;
import com.i2i.ibus.repository.PickupPointRepository;
import com.i2i.ibus.repository.SeatRepository;
import com.i2i.ibus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	validatePickupPoints(booking, busId);
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

    public List<BookingDto> getAllBooking() {
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
     * method is used to get Booking by id(bookingId)
     * 
     * @param id
     * @return
     */
    public BookingDto getBookingById(int id) {
	completeBooking(id);
	Booking booking = bookingRepository.findById(id).get();
	setSeatStatus(booking);
	return Mapper.toBookingDto(booking);
    }

    /**
     * method is used to get Booking by id(userId)
     * 
     * @param userId
     * @return
     */
    public List<BookingDto> getBookingByUserId(int userId) {
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
     * method is used to get Booking by id(userId)
     * 
     * @param id
     * @return
     */
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
     * method is used to get Booking by Date(travelDate)
     * 
     * @param bus
     * @param travelDate
     * @return
     */
    public BusHistory getBusHistoryByTravelDate(Bus bus, LocalDate travelDate) {
	Optional<BusHistory> busHistory = busHistoryRepository.findByBusIdAndDepartureDate(bus.getId(), travelDate);
	if (!busHistory.isPresent()) {
	    throw new IBusException("This bus is not departure on this date");
	}
	return busHistory.get();
    }

    /**
     * method is used to cancellation the booking.
     * 
     * @param bookingId
     */
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

    /**
     * method is used to Calculate the fare.
     * 
     * @param bookingDetails
     * @param busId
     * @return
     */
    public double calculateFare(List<BookingDetail> bookingDetails, int busId) {
	double fare = 0;

	for (BookingDetail bookingDetail : bookingDetails) {
	    fare += seatRepository.findBySeatNumberAndBusId(bookingDetail.getSeatNumber(), busId).get().getFare();
	}
	return fare;
    }

    /**
     * method is used to calculate refund far.
     * 
     * @param booking
     * @param cancellation
     * @return
     */
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

    /**
     * method is used to check the booking complete or not.
     * 
     * @param id
     */
    public void completeBooking(int id) {
	validateBooking(id);
	Booking booking = bookingRepository.findById(id).get();
	if (calculateDifferenceOfTime(getBusHistoryByTravelDate(booking.getBus(), booking.getTravelDate())) <= 0) {
	    booking.setStatus("Completed");
	}
	bookingRepository.save(booking);
    }

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
     * method is used to Calculate the time.
     * 
     * @param busHistory
     * @return
     */
    public long calculateDifferenceOfTime(BusHistory busHistory) {
	return ChronoUnit.MINUTES.between(LocalDateTime.now(),
		LocalDateTime.of(busHistory.getArrivingDate(), busHistory.getArrivingTime()));
    }

    /**
     * method is used to Delete booking.
     * 
     * @param bookingId
     */
    public void deleteBooking(int bookingId) {
	validateBooking(bookingId);
	bookingRepository.deleteById(bookingId);
    }

    /**
     * method is used to Validate the id(userId).
     * 
     * @param id
     */
    public void validateUser(int id) {
	Optional<User> user = userRepository.findById(id);
	if (!user.isPresent()) {
	    throw new IBusException("User Id doesn't exists");
	}
    }

    /**
     * method is used to Validate the id(BusId).
     * 
     * @param id
     */
    public void validateBus(int id) {
	Optional<Bus> bus = busRepository.findById(id);
	if (!bus.isPresent()) {
	    throw new IBusException("Bus Id doesn't exists");
	}
    }

    /**
     * method is used to Validate the id(bookingId).
     * 
     * @param id
     */
    public void validateBooking(int id) {
	Optional<Booking> booking = bookingRepository.findById(id);
	if (!booking.isPresent()) {
	    throw new IBusException("Booking Id doesn't exists");
	}
    }

    /**
     * method is used to alidate the pickup/droppoints.
     * 
     * @param booking
     * @param busId
     */
    private void validatePickupPoints(Booking booking, int busId) {
	Optional<PickupPoint> dropOff = pickupPointRepository.findAllByBusIdCityAndStopName(busId,
		booking.getDestination(), booking.getDropPoint());
	Optional<PickupPoint> pickUp = pickupPointRepository.findAllByBusIdCityAndStopName(busId, booking.getSource(),
		booking.getPickUpPoint());
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
     * method is used to Validate the ValidateBookingDetails.
     * 
     * @param bookingDetails
     * @param busId
     */
    private void validateBookingDetails(List<BookingDetail> bookingDetails, int busId) {
	for (BookingDetail bookingDetail : bookingDetails) {
	    Optional<Seat> seat = seatRepository.findBySeatNumberAndBusId(bookingDetail.getSeatNumber(), busId);
	    if (seat.isPresent()) {
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
