package com.i2i.ibus.service.impl;

import com.i2i.ibus.repository.PaymentRepository;
import com.i2i.ibus.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PaymentServiceImplTest {

    @Autowired
    private PaymentRepository paymentRepositoryDB;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void createPayment() {
    }

    @Test
    void validateBookingCancellationStatus() {
    }

    @Test
    void validateBookingStatus() {
    }

    @Test
    void validateBookingTotalFare() {
    }

    @Test
    void validatePaymentTime() {
    }

    @Test
    void getByBookingId() {
    }

    @Test
    void getAll() {
    }

    @Test
    void deleteAll() {
    }
}