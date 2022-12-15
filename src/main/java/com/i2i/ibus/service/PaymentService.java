/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.i2i.ibus.service;

import com.i2i.ibus.dto.PaymentDto;

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