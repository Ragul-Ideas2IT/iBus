/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.i2i.ibus.constants.Constants;
import com.i2i.ibus.dto.MessageDto;
import com.i2i.ibus.dto.PaymentDto;
import com.i2i.ibus.service.PaymentService;

/**
 * The payment details are passed to the payment service for validation to store
 * the details in the database, read and delete processes are passed.
 *
 * @author Tamilmani K
 * @version 1.0
 * @since Nov 29 2022
 *
 */
@RestController
@RequestMapping("api/v1/bookings/payments")
public class PaymentController {

    private PaymentService paymentService;
    private Logger logger = LogManager.getLogger(PaymentController.class);

    /**
     * Create a new payment service to initialing the specified targets for
     * validating the payment details.
     *
     * @param paymentService to validating the payment details.
     */
    @Autowired
    private PaymentController(PaymentService paymentService) {
	this.paymentService = paymentService;
    }

    /**
     * Create a payment for a booking. If the payment is validated successfully it
     * returns {@code MessageDto}, otherwise it throws {@code IBusException}.
     *
     * @param paymentDto Payment details for validating.
     * @return MessageDto If the payment created successfully it return
     *         {@code MessageDto}.
     */
    @PostMapping
    @ResponseStatus(code = HttpStatus.OK)
    private MessageDto createPayment(@RequestBody @Valid PaymentDto paymentDto) {
	paymentService.createPayment(paymentDto);
	logger.info(Constants.PAID_MESSAGE + Constants.BOOKING_ID + paymentDto.getBookingId());
	return new MessageDto(Constants.EVERYTHING_IS_OK, Constants.PAID_MESSAGE);
    }

    /**
     * This function returns a list of all payments for a given booking id. If the
     * booking id aren't found it throws {@code IBusException}.
     *
     * @param bookingId The booking id of the booking for which you want to get all
     *                  the payments.
     * @return List<PaymentDto> A list of PaymentDto objects.
     */
    @GetMapping("/{bookingId}")
    @ResponseStatus(code = HttpStatus.OK)
    private List<PaymentDto> getByBookingId(@PathVariable int bookingId) {
	return paymentService.getByBookingId(bookingId);
    }

    /**
     * This function returns a list of all payments for a last 20 days.
     *
     * @return List<PaymentDto> A list of PaymentDto objects.
     */
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    private List<PaymentDto> getAll() {
	return paymentService.getAll();
    }

    /**
     * It deletes all the payments in the database.
     *
     * @return MessageDto A MessageDto object with a status code and a message.
     */
    @DeleteMapping
    @ResponseStatus(code = HttpStatus.OK)
    private MessageDto deleteAll() {
	paymentService.deleteAll();
	logger.info(Constants.DELETE_MESSAGE);
	return new MessageDto(Constants.EVERYTHING_IS_OK, Constants.DELETE_MESSAGE);
    }

}