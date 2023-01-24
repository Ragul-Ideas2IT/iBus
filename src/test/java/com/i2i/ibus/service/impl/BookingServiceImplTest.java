package com.i2i.ibus.service.impl;

import com.i2i.ibus.dto.BookingDto;
import com.i2i.ibus.dto.BusDto;
import com.i2i.ibus.dto.ScheduleDto;
import com.i2i.ibus.dto.SeatDto;
import com.i2i.ibus.dto.StopDto;
import com.i2i.ibus.dto.UserDto;
import com.i2i.ibus.model.Account;
import com.i2i.ibus.model.Booking;
import com.i2i.ibus.repository.BookingRepository;
import com.i2i.ibus.service.BusService;
import com.i2i.ibus.service.NotificationService;
import com.i2i.ibus.service.ScheduleService;
import com.i2i.ibus.service.SeatService;
import com.i2i.ibus.service.StopService;
import com.i2i.ibus.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BookingServiceImplTest {

    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private BusService busService;
    @Mock
    private ScheduleService scheduleService;
    @Mock
    private SeatService seatService;
    @Mock
    private UserService userService;
    @Mock
    private StopService stopService;
    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private BookingServiceImpl bookingService;

    private BookingDto bookingDto;
    private Booking booking;
    private UserDto userDto;
    private BusDto busDto;
    private ScheduleDto scheduleDto;
    private SeatDto seatDto;
    private StopDto stopDto;


    @BeforeEach
    public void setup() {

    }
    @Test
    void book() {
        when(bookingRepository.findById(bookingDto.getId())).thenReturn(Optional.of(booking));

    }

    @Test
    void getAllBookings() {
    }

    @Test
    void getById() {
    }

    @Test
    void getByUserId() {
    }

    @Test
    void getBusById() {
    }

    @Test
    void getUserById() {
    }

    @Test
    void getScheduleByTravelDate() {
    }

    @Test
    void cancel() {
    }

    @Test
    void calculateFare() {
    }

    @Test
    void cancelBooking() {
    }

    @Test
    void getByBusId() {
    }

    @Test
    void getByBusIdAndTravelDate() {
    }

    @Test
    void setSeatStatus() {
    }

    @Test
    void calculateDifferenceOfTime() {
    }

    @Test
    void deleteBooking() {
    }

    @Test
    void validateBooking() {
        when(bookingRepository.findById(bookingDto.getId())).thenReturn(Optional.of(booking));

    }

    @Test
    void validateStops() {
    }

    @Test
    void validateBookingDetails() {
    }
}