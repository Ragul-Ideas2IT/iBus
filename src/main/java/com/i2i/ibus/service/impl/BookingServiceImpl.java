/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.i2i.ibus.dto.NotificationDto;
import com.i2i.ibus.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.ibus.constants.Constants;
import com.i2i.ibus.dto.BookingDto;
import com.i2i.ibus.dto.CancellationDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.Booking;
import com.i2i.ibus.model.BookingDetail;
import com.i2i.ibus.model.Bus;
import com.i2i.ibus.model.Cancellation;
import com.i2i.ibus.model.Schedule;
import com.i2i.ibus.model.Seat;
import com.i2i.ibus.model.Stop;
import com.i2i.ibus.model.User;
import com.i2i.ibus.repository.BookingRepository;


/**
 * <h1> BUS BOOKING APPLICATION</h1>
 * <p>
 * Used to manipulate the Booking details in the application. Operators are
 * manipulate the booking details.
 *
 * @author Esakkiraja E
 * @version 1.0
 * @since Nov 29 2022
 */
@Service
public class BookingServiceImpl implements BookingService {

    private BookingRepository bookingRepository;
    private BusService busService;
    private ScheduleService scheduleService;
    private SeatService seatService;
    private UserService userService;
    private StopService stopService;
    private NotificationService notificationService;
    private Logger logger = LogManager.getLogger(BookingServiceImpl.class);

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, StopService stopService,
                              BusService busService, ScheduleService scheduleService,
                              UserService userService, SeatService seatService,
                              NotificationService notificationService) {
        this.bookingRepository = bookingRepository;
        this.busService = busService;
        this.userService = userService;
        this.scheduleService = scheduleService;
        this.seatService = seatService;
        this.stopService = stopService;
        this.notificationService = notificationService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BookingDto book(BookingDto bookingDto) {
        Booking booking = Mapper.toBooking(bookingDto);
        validateStops(booking, bookingDto.getBusesId());
        booking.setBus(getBusById(bookingDto.getBusesId()));
        getScheduleByTravelDate(booking.getBus(), booking.getTravelDate(), booking.getTravelTime());
        validateBookingDetails(booking.getBookingDetails(), bookingDto.getBusesId());
        booking.setTotalFare(calculateFare(booking.getBookingDetails(), bookingDto.getBusesId()));
        booking.setDateTime(LocalDateTime.now());
        booking.setStatus(Constants.NOT_CONFIRMED);
        setSeatStatus(booking);
        booking.setUser(getUserById(bookingDto.getUsersId()));
        bookingRepository.save(booking);
        logger.info(Constants.CREATE_MESSAGE.concat(Constants.BOOKING_ID) + booking.getId());
        notificationService.addNotification(new NotificationDto(booking.getId(), Constants.BOOKING_SUCCESSFULLY,
                Constants.BOOKING));
        return Mapper.toBookingDto(booking);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BookingDto> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        List<BookingDto> bookingsDto = new ArrayList<BookingDto>();
        if (!bookings.isEmpty()) {
            for (Booking booking : bookings) {
                bookingsDto.add(Mapper.toBookingDto(booking));
            }
        } else {
            logger.error(Constants.BOOKING_NOT_EXIST);
            throw new IBusException(Constants.BOOKING_NOT_EXIST);
        }
        return bookingsDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BookingDto getById(int id) {
        Booking booking = bookingRepository.findById(id).get();
        setSeatStatus(booking);
        return Mapper.toBookingDto(booking);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BookingDto> getByUserId(int userId) {
        List<Booking> bookings = bookingRepository.findAllByUserId(userId);
        List<BookingDto> bookingsDto = new ArrayList<BookingDto>();
        if (!bookings.isEmpty()) {
            for (Booking booking : bookings) {
                setSeatStatus(booking);
                bookingsDto.add(Mapper.toBookingDto(booking));
            }
        } else {
            logger.info(Constants.BOOKING_NOT_EXIST.concat(Constants.USER_ID) + userId);
        }
        return bookingsDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Bus getBusById(int id) {
        return Mapper.toBus(busService.getById(id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getUserById(int id) {
        return Mapper.toUser(userService.getUserDtoById(id));
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schedule getScheduleByTravelDate(Bus bus, LocalDate travelDate, LocalTime travelTime) {
        Optional<Schedule> schedule = scheduleService.findByBusIdAndDepartureDateAndDepartureTime(bus.getId(),
                travelDate, travelTime);
        if (!schedule.isPresent()) {
            logger.error(Constants.INVALID_BUS_DEPARTURE_DATE.concat(Constants.BUS_ID) + bus.getId());
            throw new IBusException(Constants.INVALID_BUS_DEPARTURE_DATE);
        }
        return schedule.get();
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
            notificationService.addNotification(new NotificationDto(booking.getId(), Constants.BOOKING_CANCEL_MESSAGE,
                    Constants.CANCELLED));
        } else {
            logger.error(Constants.BOOKING_ALREADY_CANCEL_MESSAGE.concat(Constants.BOOKING_ID) + bookingId);
            throw new IBusException(Constants.BOOKING_ALREADY_CANCEL_MESSAGE);
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
            fare += seatService.findBySeatNumberAndBusId(bookingDetail.getSeatNumber(), busId).get().getFare();
        }
        return fare;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Booking cancelBooking(Booking booking) {
        if (booking.getStatus().equals("Not confirmed")) {
            booking.getCancellation().setRefundAmount(0);
            booking.getCancellation().setRefundStatus(Constants.NOT_PAID);
        } else {
            long min = calculateDifferenceOfTime(getScheduleByTravelDate(booking.getBus(), booking.getTravelDate(),
                    booking.getTravelTime()));
            if (min >= 600) {
                booking.getCancellation().setRefundAmount((booking.getTotalFare() - (booking.getTotalFare() * 0.1)));
            } else {
                booking.getCancellation()
                        .setRefundAmount(booking.getTotalFare() - (booking.getTotalFare() * (100 - (6000 / min))));
            }
            logger.info(Constants.REFUNDED.concat(Constants.REFUND_AMOUNT) + booking.getCancellation().getRefundAmount());
            booking.getCancellation().setRefundStatus(Constants.REFUNDED);
        }
        logger.info(Constants.BOOKING_CANCEL_MESSAGE + Constants.BOOKING_ID + booking.getId());
        booking.getCancellation().setCancellationStatus(Constants.CANCELLED);
        return booking;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BookingDto> getByBusId(int busId) {
        List<Booking> bookings = bookingRepository.findAllByBusId(busId);
        List<BookingDto> bookingsDto = new ArrayList<BookingDto>();
        if (!bookings.isEmpty()) {

            for (Booking booking : bookings) {
                bookingsDto.add(Mapper.toBookingDto(booking));
            }
        }
        return bookingsDto;
    }
    
    /**
     * {@inheritDoc} 
     */
    @Override
    public List<BookingDto> getByBusIdAndTravelDate(int busId, LocalDate date) {
        List<Booking> bookings = bookingRepository.findAllByTravelDateAndBusId(date, busId);
        System.out.println(bookings);
        List<BookingDto> bookingsDto = new ArrayList<BookingDto>();
        if (!bookings.isEmpty()) {

            for (Booking booking : bookings) {
                bookingsDto.add(Mapper.toBookingDto(booking));
            }
        }
        System.out.println(bookingsDto);
        return bookingsDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSeatStatus(Booking booking) {
        if (booking.getStatus().equals(Constants.CONFIRMED)) {

            for (BookingDetail bookingDetail : booking.getBookingDetails()) {
                Seat seat = seatService
                        .findBySeatNumberAndBusId(bookingDetail.getSeatNumber(), booking.getBus().getId()).get();
                seat.setOccupied(true);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long calculateDifferenceOfTime(Schedule schedule) {
        return ChronoUnit.MINUTES.between(LocalDateTime.now(),
                LocalDateTime.of(schedule.getArrivingDate(), schedule.getArrivingTime()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteBooking(int bookingId) {
        Booking booking = validateBooking(bookingId);
        booking.setDeleted(true);
        bookingRepository.save(booking);
        logger.info(Constants.DELETE_MESSAGE.concat(Constants.BOOKING_ID) + bookingId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Booking validateBooking(int id) {
        Booking booking;
        try {
            booking = bookingRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException exception) {
            logger.error(Constants.BUS_ID_NOT_EXIST.concat(Constants.BOOKING_ID) + id);
            throw new IBusException(Constants.BUS_ID_NOT_EXIST);
        }
        return booking;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateStops(Booking booking, int busId) {
        Optional<Stop> dropOff = stopService.findAllByBusIdAndCityAndStopName(busId, booking.getDestination(),
                booking.getDropPoint());
        Optional<Stop> pickUp = stopService.findAllByBusIdAndCityAndStopName(busId, booking.getSource(),
                booking.getPickUpPoint());
        if (booking.getSource().equals(booking.getDestination())) {
            logger.error(Constants.SAME_SOURCE_AND_DESTINATION.concat(Constants.BUS_ID) + busId);
            throw new IBusException(Constants.SAME_SOURCE_AND_DESTINATION);
        }
        if (!dropOff.isPresent()) {
            logger.error(Constants.INVALID_DROP_POINT_MESSAGE.concat(booking.getDestination()));
            throw new IBusException(Constants.INVALID_DROP_POINT_MESSAGE.concat(booking.getDestination()));
        }
        if (!pickUp.isPresent()) {
            logger.error(Constants.INVALID_PICKUP_POINT_MESSAGE.concat(booking.getSource()));
            throw new IBusException(Constants.INVALID_PICKUP_POINT_MESSAGE.concat(booking.getSource()));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateBookingDetails(List<BookingDetail> bookingDetails, int busId) {
        for (BookingDetail bookingDetail : bookingDetails) {
            Optional<Seat> seat = seatService.findBySeatNumberAndBusId(bookingDetail.getSeatNumber(), busId);

            if (!seat.isPresent()) {
                logger.error(Constants.SEAT_NOT_AVAILABLE.concat(bookingDetail.getSeatNumber()));
                throw new IBusException(Constants.SEAT_NOT_AVAILABLE.concat(bookingDetail.getSeatNumber()));
            }
            if (seat.get().isOccupied()) {
                logger.error(bookingDetail.getSeatNumber().concat(Constants.SEAT_ALREADY_BOOKED));
                throw new IBusException(bookingDetail.getSeatNumber().concat(Constants.SEAT_ALREADY_BOOKED));
            }
            if (!bookingDetail.getGender().equalsIgnoreCase(seat.get().getGender())) {
                logger.error(Constants.INVALID_SEAT_INPUT_MESSAGE.concat(bookingDetail.getGender()));
                throw new IBusException(Constants.INVALID_SEAT_INPUT_MESSAGE.concat(bookingDetail.getGender()));
            }
        }
    }
}
