/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.i2i.ibus.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import com.i2i.ibus.dto.NotificationDto;
import com.i2i.ibus.service.NotificationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.ibus.constants.Constants;
import com.i2i.ibus.dto.PaymentDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.Booking;
import com.i2i.ibus.model.Payment;
import com.i2i.ibus.repository.PaymentRepository;
import com.i2i.ibus.service.BookingService;
import com.i2i.ibus.service.PaymentService;

/**
 * The validated payment details are passed to the payment repository to store
 * the payment details in the database, to get payment details and delete operation are processed.
 * 
 * @author Tamilmani K
 * @version 1.0
 * @since Nov 29 2022
 *
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    private BookingService bookingService;
    private NotificationService notificationService;
    private PaymentRepository paymentRepository;
    private Logger logger = LogManager.getLogger(PaymentServiceImpl.class);

    /**
     * Create a new payment repository and booking repository to initialing the
     * specified targets to connect the database.
     *
     * @param bookingService To get the booking details.
     * @param notificationService To save the notified messages.
     * @param paymentRepository To save, read and delete the payment details.
     */
    @Autowired
    private PaymentServiceImpl(BookingService bookingService, NotificationService notificationService,
                               PaymentRepository paymentRepository) {
	this.bookingService = bookingService;
    this.notificationService = notificationService;
	this.paymentRepository = paymentRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PaymentDto createPayment(PaymentDto paymentDto) {
        Booking booking = bookingService.validateBooking(paymentDto.getBookingId());
        Payment payment = Mapper.toPayment(paymentDto);
        payment.setTime(LocalDateTime.now());
        payment.setBooking(booking);
        validateBookingCancellationStatus(payment);
        validateBookingStatus(payment);
        validateBookingTotalFare(payment);
        validatePaymentTime(payment);
        payment.setStatus(Constants.PAID);
        payment.setMessage(Constants.PAID_MESSAGE);
        booking.setStatus(Constants.CONFIRMED);
        payment = paymentRepository.save(payment);
        notificationService.addNotification(new NotificationDto(payment.getId(), Constants.BOOKING_SUCCESSFULLY,
                Constants.PAYMENT));
        return Mapper.toPaymentDto(payment);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PaymentDto validateBookingCancellationStatus(Payment payment) {
        if (payment.getBooking().getCancellation() != null) {
            payment.setMessage(Constants.PAYMENT_CANCEL_MESSAGE);
            payment = paymentRepository.save(payment);
            logger.error(Constants.PAYMENT_CANCEL_MESSAGE + Constants.PAYMENT_ID + payment.getId());
            notificationService.addNotification(new NotificationDto(payment.getId(), Constants.PAYMENT_CANCEL_MESSAGE,
                    Constants.PAYMENT));
            throw new IBusException(Constants.PAYMENT_CANCEL_MESSAGE);
        }
        return Mapper.toPaymentDto(payment);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PaymentDto validateBookingStatus(Payment payment){
        if (payment.getBooking().getStatus().equalsIgnoreCase(Constants.CONFIRMED)) {
            payment.setMessage(Constants.ALREADY_PAYMENT_SUCCEED_MESSAGE);
            payment = paymentRepository.save(payment);
            logger.error(Constants.ALREADY_PAYMENT_SUCCEED_MESSAGE + Constants.PAYMENT_ID
                    + payment.getId());
            notificationService.addNotification(new NotificationDto(payment.getId(),
                    Constants.ALREADY_PAYMENT_SUCCEED_MESSAGE, Constants.PAYMENT));
            throw new IBusException(Constants.ALREADY_PAYMENT_SUCCEED_MESSAGE);
        }
        return Mapper.toPaymentDto(payment);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PaymentDto validateBookingTotalFare(Payment payment) {
        if (payment.getBooking().getTotalFare() != payment.getAmount()) {
            payment.setStatus(Constants.UNPAID);
            payment.setMessage(Constants.INVALID_PAYMENT_MESSAGE);
            payment = paymentRepository.save(payment);
            logger.info(Constants.INVALID_PAYMENT_MESSAGE + Constants.PAYMENT_ID + payment.getId()
                    + Constants.AMOUNT + payment.getAmount());
            notificationService.addNotification(new NotificationDto(payment.getId(), Constants.INVALID_PAYMENT_MESSAGE,
                    Constants.PAYMENT));
            throw new IBusException(Constants.INVALID_PAYMENT_MESSAGE + payment.getBooking().getTotalFare());
        }
        return Mapper.toPaymentDto(payment);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PaymentDto validatePaymentTime(Payment payment) {
        if (5 < Duration.between(payment.getBooking().getDateTime(), LocalDateTime.now()).toMinutes()) {
            payment.setStatus(Constants.DECLINED);
            payment.setMessage(Constants.EXPIRED_PAYMENT_TIME_MESSAGE);
            payment = paymentRepository.save(payment);
            logger.error(Constants.EXPIRED_PAYMENT_TIME_MESSAGE + Constants.PAYMENT_ID + payment.getId());
            notificationService.addNotification(new NotificationDto(payment.getId(),
                    Constants.EXPIRED_PAYMENT_TIME_MESSAGE, Constants.PAYMENT));
            throw new IBusException(Constants.EXPIRED_PAYMENT_TIME_MESSAGE);
        }
        return Mapper.toPaymentDto(payment);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PaymentDto> getByBookingId(int bookingId) {
	bookingService.validateBooking(bookingId);
	return Mapper.toPaymentDtos(paymentRepository.findAllByBookingId(bookingId));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PaymentDto> getAll() {
	LocalDateTime dateAndTime = LocalDateTime.now().minusDays(20);
	List<Payment> payments = paymentRepository.findByTimeBetween(dateAndTime, LocalDateTime.now());
	List<PaymentDto> paymentDtos = Mapper.toPaymentDtos(payments);
	logger.info(Constants.PAYMENT_DETAILS_GET_MESSAGE, dateAndTime, LocalDateTime.now());
	return paymentDtos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
	List<Payment> payments = paymentRepository.findAll();
	for (Payment payment : payments) {
	    payment.setDeleted(true);
	    paymentRepository.save(payment);
	}
    }
}