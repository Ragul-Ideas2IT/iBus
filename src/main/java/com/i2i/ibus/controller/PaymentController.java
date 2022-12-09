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

    /**
     * Create a payment for a booking
     *
     * @param bookingId The id of the booking that the payment is for.
     * @param paymentDto This is the object that will be sent to the server.
     * @return PaymentDto
     */
    @PostMapping("/bookings/{bookingId}")
    @ResponseStatus(code = HttpStatus.OK)
    private PaymentDto createPayment(@PathVariable int bookingId, @RequestBody @Valid PaymentDto paymentDto) {
        return paymentService.createPayment(bookingId, paymentDto);
    }

    /**
     * This function returns a list of all payments for a given booking
     *
     * @param bookingId The booking id of the booking for which you want to get all the payments.
     * @return A list of PaymentDto objects.
     */
    @GetMapping("/bookings/{bookingId}")
    @ResponseStatus(code = HttpStatus.OK)
    private List<PaymentDto> getAllByBookingId(@PathVariable int bookingId) {
        return paymentService.getAllPaymentsByBookingId(bookingId);
    }

    /**
     * This function is a GET request that takes in a paymentId and returns a PaymentDto
     *
     * @param paymentId The paymentId is the unique identifier for the payment.
     * @return PaymentDto
     */
    @GetMapping("/{paymentId}")
    @ResponseStatus(code = HttpStatus.OK)
    private PaymentDto getByPaymentId(@PathVariable int paymentId) {
        return paymentService.getPaymentByPaymentId(paymentId);
    }

    /**
     * It deletes all payments associated with a booking
     *
     * @param bookingId The booking id of the booking whose payments you want to delete.
     * @return A MessageDto object with a status code and message.
     */
    @DeleteMapping("/bookings/{bookingId}")
    @ResponseStatus(code = HttpStatus.OK)
    private MessageDto deleteAllByBookingId(@PathVariable int bookingId) {
        paymentService.deleteAllPaymentsByBookingId(bookingId);
        return new MessageDto("200", "Deleted Successfully.");
    }

    /**
     * It deletes a payment by paymentId
     *
     * @param paymentId The paymentId of the payment you want to delete.
     * @return A MessageDto object with a status code and message.
     */
    @DeleteMapping("/{paymentId}")
    @ResponseStatus(code = HttpStatus.OK)
    private MessageDto deleteById(@PathVariable int paymentId) {
        paymentService.deleteByPaymentId(paymentId);
        return new MessageDto("200", "Deleted Successfully.");
    }

    /**
     * It deletes all the payments in the database
     *
     * @return A MessageDto object with a status code and a message.
     */
    @DeleteMapping
    @ResponseStatus(code = HttpStatus.OK)
    private MessageDto deleteAllPayments() {
        paymentService.deleteAllPayments();
        return new MessageDto("200", "Deleted Successfully.");
    }
}