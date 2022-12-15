/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.i2i.ibus.service.impl;

import com.i2i.ibus.constants.Constants;
import com.i2i.ibus.dto.PaymentDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.Booking;
import com.i2i.ibus.model.Payment;
import com.i2i.ibus.repository.BookingRepository;
import com.i2i.ibus.repository.PaymentRepository;
import com.i2i.ibus.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * The validated payment details are passed to the payment repository to store
 * the details in the database, otherwise it throws {@code IBusException}.
 *
 * @author Tamilmani K
 * @version 1.0
 * @since Nov 29 2022
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository paymentRepository;
    private BookingRepository bookingRepository;

    /**
     * Create a new payment repository and booking repository to initialing the
     * specified targets to connect the database.
     *
     * @param paymentRepository To save, read and delete the payment details.
     * @param bookingRepository To save, read and delete the booking details.
     */
    @Autowired
    private PaymentServiceImpl(PaymentRepository paymentRepository, BookingRepository bookingRepository) {
        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PaymentDto createPayment(int bookingId, PaymentDto paymentDto) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IBusException(Constants.BOOKING_NOT_EXIST));
        Payment payment = Mapper.toPayment(paymentDto);
        payment.setTime(LocalDateTime.now());
        payment.setBooking(booking);
        if (booking.getCancellation() != null) {
            throw new IBusException(Constants.PAYMENT_CANCEL_MESSAGE);
        }
        if (0 == paymentDto.getCvvNumber()) {
            throw new IBusException(Constants.CVV_NUMBER_MANDATORY_MESSAGE);
        }
        if (booking.getPaymentStatus().equalsIgnoreCase(Constants.SUCCESS)) {
            throw new IBusException(Constants.ALREADY_PAYMENT_SUCCEED_MESSAGE);
        }
        if (booking.getTotalFare() != paymentDto.getAmount()) {
            booking.setPaymentStatus(Constants.DECLINED);
            payment.setStatus(Constants.UNPAID);
            paymentDto = Mapper.toPaymentDto(paymentRepository.save(payment));
            throw new IBusException(Constants.INVALID_PAYMENT_MESSAGE + booking.getTotalFare());
        }
        if (5 < Duration.between(booking.getDateTime(), LocalDateTime.now()).toMinutes()) {
            booking.setPaymentStatus(Constants.DECLINED);
            payment.setStatus(Constants.UNPAID);
            paymentDto = Mapper.toPaymentDto(paymentRepository.save(payment));
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
    public PaymentDto getById(int paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new IBusException(Constants.PAYMENT_NOT_EXIST));
        return Mapper.toPaymentDto(payment);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PaymentDto> getAllByBookingId(int bookingId) {
        bookingRepository.findById(bookingId).orElseThrow(() -> new IBusException(Constants.BOOKING_NOT_EXIST));
        return Mapper.toPaymentDtos(paymentRepository.findAllByBookingId(bookingId));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAllByBookingId(int bookingId) {
        bookingRepository.findById(bookingId).orElseThrow(() -> new IBusException(Constants.BOOKING_NOT_EXIST));
        paymentRepository.deleteAllByBookingId(bookingId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        paymentRepository.deleteAll();
    }
}