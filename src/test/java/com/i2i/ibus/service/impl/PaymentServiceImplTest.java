package com.i2i.ibus.service.impl;

import com.i2i.ibus.constants.Constants;
import com.i2i.ibus.dto.BookingDto;
import com.i2i.ibus.dto.NotificationDto;
import com.i2i.ibus.dto.PaymentDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.Booking;
import com.i2i.ibus.model.Cancellation;
import com.i2i.ibus.model.Payment;
import com.i2i.ibus.repository.BookingRepository;
import com.i2i.ibus.repository.PaymentRepository;
import com.i2i.ibus.service.BookingService;
import com.i2i.ibus.service.NotificationService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@Log4j2
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PaymentServiceImplTest {
    @Mock
    private BookingService bookingService;

    @Mock
    private NotificationService notificationService;

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Autowired
    private PaymentRepository paymentRepositoryDB;

    @Autowired
    private BookingRepository bookingRepositoryDB;

    PaymentDto paymentDto;
    BookingDto bookingDto;
    Payment payment;
    Booking booking;
    int bookingId;
    int paymentId;

    @BeforeAll
    public void init() {
        bookingId = 1;
        paymentId = 1;
    }

    @BeforeEach
    public void setup() {
        payment = paymentRepositoryDB.findById(paymentId).get();
        booking = bookingRepositoryDB.findById(bookingId).get();
        payment.setBooking(booking);
        paymentDto = Mapper.toPaymentDto(payment);
    }

    @Test
    public void createPayment() {
        when(bookingService.validateBooking(bookingId)).thenReturn(booking);
        when(paymentRepository.save(ArgumentMatchers.any(Payment.class))).thenReturn(payment);
        when(notificationService.addNotification(ArgumentMatchers.any(NotificationDto.class))).thenReturn((new NotificationDto(payment.getId(), Constants.PAID_MESSAGE,
                Constants.PAYMENT)));
        PaymentDto newPaymentDto = paymentService.createPayment(paymentDto);
        org.assertj.core.api.Assertions.assertThat(paymentDto).usingRecursiveComparison().isEqualTo(newPaymentDto);
        log.info(paymentDto.toString());
        log.info(newPaymentDto.toString());
    }

    @Test
    public void validateBookingCancellationStatus() {
        when(paymentRepository.save(ArgumentMatchers.any(Payment.class))).thenReturn(payment);
        payment.getBooking().setCancellation(new Cancellation());
        Assertions.assertThrows(IBusException.class, () -> paymentService.validateBookingCancellationStatus(payment));
    }

    @Test
    void validateBookingStatus() {
        when(paymentRepository.save(ArgumentMatchers.any(Payment.class))).thenReturn(payment);
        payment.getBooking().setStatus(Constants.CONFIRMED);
        Assertions.assertThrows(IBusException.class, () -> paymentService.validateBookingStatus(payment));
    }

    @Test
    void validateBookingTotalFare() {
        when(paymentRepository.save(ArgumentMatchers.any(Payment.class))).thenReturn(payment);
        payment.setAmount(payment.getAmount()+1000);
        Assertions.assertThrows(IBusException.class, () -> paymentService.validateBookingTotalFare(payment));
    }

    @Test
    void validatePaymentTime() {
        when(paymentRepository.save(ArgumentMatchers.any(Payment.class))).thenReturn(payment);
        payment.getBooking().setDateTime(LocalDateTime.now().minusDays(5));
//        payment.getBooking().setDateTime(payment.getBooking().getDateTime().minusMinutes(5));
        Assertions.assertThrows(IBusException.class, () -> paymentService.validatePaymentTime(payment));
    }
    @Test
    void getByBookingId() {
        List<Payment> payments = paymentRepositoryDB.findAllByBookingId(bookingId);
        List<PaymentDto> paymentDtos = Mapper.toPaymentDtos(payments);
        when(bookingService.validateBooking(booking.getId())).thenReturn(booking);
        when(paymentRepository.findAllByBookingId(booking.getId())).thenReturn(payments);
        List<PaymentDto> newPaymentDtos = paymentService.getByBookingId(booking.getId());
        Assertions.assertEquals(newPaymentDtos.size(), paymentDtos.size());
        for (int i = 0; i < newPaymentDtos.size(); i++) {
            org.assertj.core.api.Assertions.assertThat(paymentDtos.get(i)).usingRecursiveComparison().isEqualTo(newPaymentDtos.get(i));
            log.info(paymentDtos.get(i).toString());
            log.info(newPaymentDtos.get(i).toString());
        }
    }

    @Test
    void getAll() {
        LocalDateTime dateAndTime = LocalDateTime.now().minusDays(20);
        List<Payment> payments = paymentRepository.findByTimeBetween(dateAndTime, LocalDateTime.now());
        List<PaymentDto> paymentDtos = Mapper.toPaymentDtos(payments);
        when(bookingService.validateBooking(booking.getId())).thenReturn(booking);
        when(paymentRepository.findByTimeBetween(ArgumentMatchers.any(LocalDateTime.class),
                ArgumentMatchers.any(LocalDateTime.class))).thenReturn(payments);
        List<PaymentDto> newPaymentDtos = paymentService.getAll();
        Assertions.assertEquals(newPaymentDtos.size(), paymentDtos.size());
        for (int i = 0; i < newPaymentDtos.size(); i++) {
            org.assertj.core.api.Assertions.assertThat(paymentDtos.get(i)).usingRecursiveComparison().isEqualTo(newPaymentDtos.get(i));
            log.info(paymentDtos.get(i).toString());
            log.info(newPaymentDtos.get(i).toString());
        }
    }

    @Test
    void deleteAll() {
        List<Payment> paymentList = paymentRepositoryDB.findAll();
        when(paymentRepository.findAll()).thenReturn(paymentList);
        for (Payment newPayment : paymentList) {
            log.info(newPayment.isDeleted());
            newPayment.setDeleted(true);
            when(paymentRepository.save(ArgumentMatchers.any(Payment.class))).thenReturn(newPayment);
            Payment savedPayment = paymentRepository.save(payment);
            Assertions.assertTrue(savedPayment.isDeleted());
            log.info(savedPayment.isDeleted());
        }
        paymentService.deleteAll();
    }
}