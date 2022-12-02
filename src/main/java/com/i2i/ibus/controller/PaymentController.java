package com.i2i.ibus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.i2i.ibus.dto.PaymentDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.service.PaymentService;

/**
 * @author Tamilmani
 * @version 1.0
 * 
 * @created Nov 30 2022
 *
 */
@RestController
@RequestMapping("api/v1/payments")
public class PaymentController {

    private PaymentService paymentService;

    @Autowired
    private PaymentController(PaymentService paymentService) {
	this.paymentService = paymentService;
    }

    @PostMapping("/bookings/{bookingId}")
    private PaymentDto createPayment(@PathVariable int bookingId, @RequestBody PaymentDto paymentDto)
	    throws IBusException {
	return paymentService.createPayment(bookingId, paymentDto);
    }

}