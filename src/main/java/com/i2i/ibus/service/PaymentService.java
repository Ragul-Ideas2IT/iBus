/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.i2i.ibus.service;

import java.util.List;

import com.i2i.ibus.dto.PaymentDto;

/**
 * The validated payment details are passed to the payment repository to store
 * the details in the database, otherwise it throws {@code IBusException}.
 * 
 * @author Tamilmani K
 * @version 1.0
 * @created Nov 29 2022
 *
 */
public interface PaymentService {

    /**
     * Create a payment for a booking. If the payment details are validated
     * successfully, it saves the payment details to the database, otherwise it
     * throws {@code IBusException}.
     *
     * @param bookingId  Id for get the booking to map the details.
     * @param paymentDto The payment details for validation.
     * @return A PaymentDto If the payment details are validated successfully, it
     *         returns paymentDto.
     */
    PaymentDto createPayment(int bookingId, PaymentDto paymentDto);

    /**
     * Get a payment by its payment ID. If the payment aren't found it throw
     * {@code IBusException}.
     *
     * @param paymentId The id of the payment details you want to get.
     * @return PaymentDto If the payment details are found, it returns paymentDto.
     */
    PaymentDto getById(int paymentId);

    /**
     * Get all payments for a booking. If the booking aren't found it throw
     * {@code IBusException}.
     *
     * @param bookingId The id of the booking you want to get the payment details.
     * @return List<PaymentDto> A list of PaymentDto objects.
     */
    List<PaymentDto> getAllByBookingId(int bookingId);

    /**
     * Delete all payments for particular booking id.
     *
     * @param bookingId The id of the booking to delete payments details.
     */
    void deleteAllByBookingId(int bookingId);

    /**
     * Delete all payments from the database.
     */
    void deleteAll();
}