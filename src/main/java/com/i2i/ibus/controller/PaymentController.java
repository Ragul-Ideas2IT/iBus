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

import com.i2i.ibus.dto.MessageDto;
import com.i2i.ibus.dto.PaymentDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.service.PaymentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/payments")
public class PaymentController {

    private PaymentService paymentService;

    @Autowired
    private PaymentController(PaymentService paymentService) {
	this.paymentService = paymentService;
    }

    @PostMapping("/bookings/{bookingId}")
    @ResponseStatus(code = HttpStatus.OK)
    private PaymentDto createPayment(@PathVariable int bookingId, @RequestBody @Valid PaymentDto paymentDto)
	    throws IBusException {
	return paymentService.createPayment(bookingId, paymentDto);
    }

    @GetMapping("/bookings/{bookingId}")
    @ResponseStatus(code = HttpStatus.OK)
    private List<PaymentDto> getAllPaymentsByBookingId(@PathVariable int bookingId) {
	return paymentService.getAllPaymentsByBookingId(bookingId);
    }

    @GetMapping("/{paymentId}")
    @ResponseStatus(code = HttpStatus.OK)
    private PaymentDto getPaymentByPaymentId(@PathVariable int paymentId) throws IBusException {
	return paymentService.getPaymentByPaymentId(paymentId);
    }

    @DeleteMapping("/bookings/{bookingId}")
    @ResponseStatus(code = HttpStatus.OK)
    private MessageDto deleteAllPaymentsByBookingId(@PathVariable int bookingId) {
	paymentService.deleteAllPaymentsByBookingId(bookingId);
	return new MessageDto("200", "Deleted Successfully.");
    }

    @DeleteMapping("/{paymentId}")
    @ResponseStatus(code = HttpStatus.OK)
    private MessageDto deleteByPaymentId(@PathVariable int paymentId) {
	paymentService.deleteByPaymentId(paymentId);
	return new MessageDto("200", "Deleted Successfully.");
    }

    @DeleteMapping
    @ResponseStatus(code = HttpStatus.OK)
    private MessageDto deleteAllPayments() {
	paymentService.deleteAllPayments();
	return new MessageDto("200", "Deleted Successfully.");
    }
}