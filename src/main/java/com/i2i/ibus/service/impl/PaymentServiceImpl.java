/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.i2i.ibus.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.ibus.constants.Constants;
import com.i2i.ibus.controller.PaymentController;
import com.i2i.ibus.dto.PaymentDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.Booking;
import com.i2i.ibus.model.Payment;
import com.i2i.ibus.repository.BookingRepository;
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
    private PaymentRepository paymentRepository;
    private Logger logger = LogManager.getLogger(PaymentController.class);

    /**
     * Create a new payment repository and booking repository to initialing the
     * specified targets to connect the database.
     *
     * @param bookingService To get the booking details.
     * @param paymentRepository To save, read and delete the payment details.
     */
    @Autowired
    private PaymentServiceImpl(BookingService bookingService, PaymentRepository paymentRepository) {
	this.bookingService = bookingService;
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
        if (booking.getCancellation() != null) {
            logger.error(Constants.PAYMENT_CANCEL_MESSAGE + Constants.BOOKING_ID + paymentDto.getBookingId());
            throw new IBusException(Constants.PAYMENT_CANCEL_MESSAGE);
        }
        if (0 == paymentDto.getCvvNumber()) {
            logger.error(Constants.CVV_NUMBER_MANDATORY_MESSAGE + Constants.BOOKING_ID + paymentDto.getBookingId());
            throw new IBusException(Constants.CVV_NUMBER_MANDATORY_MESSAGE);
        }
        if (booking.getPaymentStatus().equalsIgnoreCase(Constants.SUCCESS)) {
            logger.error(Constants.ALREADY_PAYMENT_SUCCEED_MESSAGE + Constants.BOOKING_ID
        	    + paymentDto.getBookingId());
            throw new IBusException(Constants.ALREADY_PAYMENT_SUCCEED_MESSAGE);
        }
        if (booking.getTotalFare() != paymentDto.getAmount()) {
            booking.setPaymentStatus(Constants.DECLINED);
            payment.setStatus(Constants.UNPAID);
            paymentDto = Mapper.toPaymentDto(paymentRepository.save(payment));
            logger.error(Constants.INVALID_PAYMENT_MESSAGE + booking.getTotalFare() + Constants.BOOKING_ID
        	    + paymentDto.getBookingId());
            throw new IBusException(Constants.INVALID_PAYMENT_MESSAGE + booking.getTotalFare());
        }
        if (5 < Duration.between(booking.getDateTime(), LocalDateTime.now()).toMinutes()) {
            booking.setPaymentStatus(Constants.DECLINED);
            payment.setStatus(Constants.UNPAID);
            paymentDto = Mapper.toPaymentDto(paymentRepository.save(payment));
            logger.error(Constants.EXPIRED_PAYMENT_TIME_MESSAGE);
            throw new IBusException(Constants.EXPIRED_PAYMENT_TIME_MESSAGE);
        } else {
            payment.setStatus(Constants.PAID);
            booking.setPaymentStatus(Constants.SUCCESS);
            paymentDto = Mapper.toPaymentDto(paymentRepository.save(payment));
        }
        return paymentDto;
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