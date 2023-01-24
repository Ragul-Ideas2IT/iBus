/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.i2i.ibus.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.i2i.ibus.model.Payment;

/**
 * Interfaces for Payment repository to extends jpa repositories for connecting
 * with databases declaring custom methods.
 * 
 * @author Tamilmani K
 * @version 1.0
 * @since Nov 29 2022
 *
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    /**
     * Get the all payments details from the particular date and time.
     * 
     * @param start Provided date and time is a type of {@link LocalDateTime},
     *                    to get list of paymentDto objects.
     * @param end 
     * @return List<Payment> returns the list of payment details.
     */
    List<Payment> findByTimeBetween(LocalDateTime start, LocalDateTime end);

    /**
     * Find all payments by booking id.
     * 
     * @param bookingId Id for getting the all payments for the particular booking
     *                  id.
     * @return List<Payment> returns the list of payment details.
     */
    List<Payment> findAllByBookingId(int bookingId);

    /**
     * Delete the all payments by booking id.
     * 
     * @param bookingId Id for delete the all payments for the particular booking
     *                  id.
     */
    void deleteAllByBookingId(int bookingId);

}