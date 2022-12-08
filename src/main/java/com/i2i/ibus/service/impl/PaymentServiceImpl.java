package com.i2i.ibus.service.impl;

import com.i2i.ibus.dto.PaymentDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.Booking;
import com.i2i.ibus.model.Payment;
import com.i2i.ibus.repository.BookingRepository;
import com.i2i.ibus.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Tamilmani
 * @version 1.0
 * 
 * @created Nov 30 2022
 *
 */
@Service
public class PaymentService {

    private PaymentRepository paymentRepository;
    private BookingRepository bookingRepository;

    @Autowired
    private PaymentService(PaymentRepository paymentRepositary, BookingRepository bookingRepository) {
	this.paymentRepository = paymentRepositary;
	this.bookingRepository = bookingRepository;
    }

    public PaymentDto createPayment(int bookingId, PaymentDto paymentDto) throws IBusException {
	Booking booking = bookingRepository.findById(bookingId)
		.orElseThrow(() -> new IBusException("No booking id found..."));
	Payment payment = Mapper.toPayment(paymentDto);
	payment.setTime(LocalDateTime.now());
	payment.setBooking(booking);
	if (0 == paymentDto.getCvvNumber()) {
	    throw new IBusException("Cvv number must be mandatory.");
	}
	if (5 < Duration.between(booking.getDateTime(), LocalDateTime.now()).toMinutes()) {
	    booking.setPaymentStatus("declined");
	    payment.setStatus("unpaid");
	    paymentDto = Mapper.toPaymentDto(paymentRepository.save(payment));
	    throw new IBusException("Booking time is over...");
	} else if (booking.getPaymentStatus().equalsIgnoreCase("success")) {
	    throw new IBusException("Payment already succeeded");
	} else if (booking.getTotalFare() != paymentDto.getAmount()) {
	    booking.setPaymentStatus("declined");
	    payment.setStatus("unpaid");
	    paymentDto = Mapper.toPaymentDto(paymentRepository.save(payment));
	    throw new IBusException(
		    "Payment is cancelled due to invalid amount. The total fare is " + booking.getTotalFare());
	} else {
	    payment.setStatus("paid");
	    booking.setPaymentStatus("success");
	    paymentDto = Mapper.toPaymentDto(paymentRepository.save(payment));
	}
	return paymentDto;
    }

    public PaymentDto getPaymentByPaymentId(int paymentId) throws IBusException {
	Payment payment = paymentRepository.findById(paymentId)
		.orElseThrow(() -> new IBusException("Payment id doesn't exist"));
	return Mapper.toPaymentDto(payment);
    }

    public List<PaymentDto> getAllPaymentsByBookingId(int bookingId) {
	bookingRepository.findById(bookingId).orElseThrow(() -> new IBusException("No booking id found..."));
	return Mapper.toPaymentDtos(paymentRepository.getAllPaymentsByBookingId(bookingId));
    }

    public void deleteAllPaymentsByBookingId(int bookingId) {
	bookingRepository.findById(bookingId).orElseThrow(() -> new IBusException("No booking id found..."));
	paymentRepository.deleteAllPaymentsByBookingId(bookingId);
    }

    public void deleteByPaymentId(int paymentId) {
	paymentRepository.findById(paymentId).orElseThrow(() -> new IBusException("Payment id doesn't exist"));
	paymentRepository.deleteById(paymentId);
    }

    public void deleteAllPayments() {
	paymentRepository.deleteAll();
    }
}