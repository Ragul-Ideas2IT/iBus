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
import com.i2i.ibus.model.Schedule;
import com.i2i.ibus.model.Cancellation;
import com.i2i.ibus.model.Stop;
import com.i2i.ibus.model.Seat;
import com.i2i.ibus.model.User;
import com.i2i.ibus.repository.BookingRepository;
import com.i2i.ibus.repository.ScheduleRepository;
import com.i2i.ibus.repository.BusRepository;
import com.i2i.ibus.repository.StopRepository;
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
    private ScheduleRepository scheduleRepository;
    private StopRepository stopRepository;
    private SeatRepository seatRepository;
    private UserRepository userRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, StopRepository stopRepository,
                              BusRepository busRepository, ScheduleRepository scheduleRepository, UserRepository userRepository,
                              SeatRepository seatRepository) {
        this.bookingRepository = bookingRepository;
        this.busRepository = busRepository;
        this.userRepository = userRepository;
        this.scheduleRepository = scheduleRepository;
        this.stopRepository = stopRepository;
        this.seatRepository = seatRepository;
    }

    public BookingDto addBooking(int userId, int busId, BookingDto bookingDto) {
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
     * {@inheritDoc}
     */
    @Override
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
    public Schedule getBusHistoryByTravelDate(Bus bus, LocalDate travelDate) {
        Optional<Schedule> schedule = scheduleRepository.findByBusIdAndDepartureDate(bus.getId(), travelDate);
        if (!schedule.isPresent()) {
            throw new IBusException("This bus is not departure on this date");
        }
        return schedule.get();
    }

    /**
     * method is used to cancellation the booking.
     *
     * @param bookingId
     */
    public CancellationDto cancellation(int bookingId) {
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
     * @return
     */
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
     * @param schedule
     * @return
     */
    public long calculateDifferenceOfTime(Schedule schedule) {
        return ChronoUnit.MINUTES.between(LocalDateTime.now(),
                LocalDateTime.of(schedule.getArrivingDate(), schedule.getArrivingTime()));
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
    public void validatePickupPoints(Booking booking, int busId) {
        Optional<Stop> dropOff = stopRepository.findAllByBusIdAndCityAndStopName(busId,
                booking.getDestination(), booking.getDropPoint());
        Optional<Stop> pickUp = stopRepository.findAllByBusIdAndCityAndStopName(busId, booking.getSource(),
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
