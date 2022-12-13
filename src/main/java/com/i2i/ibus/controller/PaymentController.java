/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.controller;

import java.util.List;

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

import javax.validation.Valid;

/**
 * The payment details are passed to the payment service for validation to store
 * the details in the database, otherwise it throws {@code IBusException}.
 *
 * @author Tamilmani K
 * @version 1.0
 * @created Nov 29 2022
 *
 */
@RestController
@RequestMapping("api/v1/payments")
public class PaymentController {

    private PaymentService paymentService;

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
     * @param bookingId  Id for get the book id.
     * @param paymentDto Payment details for validating.
     * @return MessageDto If the payment created successfully it return
     *         {@code MessageDto}.
     */
    @PostMapping("/bookings/{bookingId}")
    private MessageDto createPayment(@PathVariable int bookingId, @RequestBody @Valid PaymentDto paymentDto) {
	paymentService.createPayment(bookingId, paymentDto);
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
    @GetMapping("/bookings/{bookingId}")
    @ResponseStatus(code = HttpStatus.OK)
    private List<PaymentDto> getAllByBookingId(@PathVariable int bookingId) {
	return paymentService.getAllByBookingId(bookingId);
    }

    /**
     * It get the payment by payment id. If the payment id aren't found it throws
     * {@code IBusException}.
     *
     * @param paymentId Id for get the payment details.
     * @return PaymentDto If the payment id is found it returns {@code PaymentDto}.
     */
    @GetMapping("/{paymentId}")
    @ResponseStatus(code = HttpStatus.OK)
    private PaymentDto getById(@PathVariable int paymentId) {
	return paymentService.getById(paymentId);
    }

    /**
     * It deletes all payments associated with a booking. If the booking id aren't
     * found it throws {@code IBusException}.
     *
     * @param bookingId The booking id of the booking whose payments you want to
     *                  delete.
     * @return MessageDto A MessageDto object with a status code and message.
     */
    @DeleteMapping("/bookings/{bookingId}")
    @ResponseStatus(code = HttpStatus.OK)
    private MessageDto deleteAllByBookingId(@PathVariable int bookingId) {
	paymentService.deleteAllByBookingId(bookingId);
	return new MessageDto(Constants.EVERYTHING_IS_OK, Constants.DELETE_MESSAGE);
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
	return new MessageDto(Constants.EVERYTHING_IS_OK, Constants.DELETE_MESSAGE);
    }

}