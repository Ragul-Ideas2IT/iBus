/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.i2i.ibus.service;

import com.i2i.ibus.dto.PaymentDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.model.Payment;

import java.util.List;

/**
 * The validated payment details are passed to the payment repository to store
 * the payment details in the database, to get payment details and delete operation are processed.
 * 
 * @author Tamilmani K
 * @version 1.0
 * @since Nov 29 2022
 *
 */
public interface PaymentService {

    /**
     * Create a payment for a booking. If the payment details are validated
     * successfully, it saves the payment details to the database, otherwise it
     * throws {@code IBusException}.
     *
     * @param paymentDto The payment details for validation.
     * @return A PaymentDto If the payment details are validated successfully, it
     *         returns paymentDto.
     */
    PaymentDto createPayment(PaymentDto paymentDto);

    /**
     * This function validate the cancellation status of the booking. If the
     * booking is cancelled then the payment is cancelled, it throws
     * {@link IBusException}.
     *
     * @param payment To get the cancellation status of the booking.
     */
    void validateBookingCancellationStatus(Payment payment);

    /**
     * This function validate the booking status. If the booking status is
     * confirmed then the payment is cancelled, it throws
     * {@link IBusException}.
     *
     * @param payment To get the booking status.
     */
    void validateBookingStatus(Payment payment);

    /**
     * This function validate the amount to the booking total fare.
     * If the amount is invalid, then it throws {@link IBusException}.
     *
     * @param payment To get the amount of the payment and total fare
     *               of the booking.
     */
    void validateBookingTotalFare(Payment payment);

    /**
     * This function compare the time of the booking and payment time.
     * If the time difference is more than 5 minutes, then it throws
     * {@link IBusException}.
     *
     * @param payment To get the time of the booking and the payment time.
     */
    void validatePaymentTime(Payment payment);

    /**
     * Get all payments for a booking. If the booking aren't found it throw
     * {@code IBusException}.
     *
     * @param bookingId The id of the booking you want to get the payment details.
     * @return List<PaymentDto> A list of PaymentDto objects.
     */
    List<PaymentDto> getByBookingId(int bookingId);

    /**
     * This function returns a list of all payments for a particular date and time.
     *
     * @return List<PaymentDto> A list of PaymentDto objects.
     */
    List<PaymentDto> getAll();
    
    /**
     * Delete all payments from the database.
     */
    void deleteAll();
}