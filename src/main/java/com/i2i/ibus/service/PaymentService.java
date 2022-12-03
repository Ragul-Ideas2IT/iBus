package com.i2i.ibus.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.ibus.dto.PaymentDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.model.Booking;
import com.i2i.ibus.model.Payment;
import com.i2i.ibus.repository.BookingRepository;
import com.i2i.ibus.repository.PaymentRepository;

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
    private ModelMapper mapper;

    @Autowired
    private PaymentService(PaymentRepository paymentRepositary, BookingRepository bookingRepository,
	    ModelMapper mapper) {
	this.paymentRepository = paymentRepositary;
	this.bookingRepository = bookingRepository;
	this.mapper = mapper;
    }

    public PaymentDto createPayment(int bookingId, PaymentDto paymentDto) throws IBusException {
	Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new IBusException("No booking id found..."));
	Payment payment = mapper.map(paymentDto, Payment.class);
	if (5 > ChronoUnit.MINUTES.between(booking.getDateTime(), LocalDateTime.now())) {
	    booking.setStatus("cancelled");
	    payment.setBooking(booking);
	    payment.setStatus("unpaid");
	    paymentDto = mapper.map(paymentRepository.save(payment), PaymentDto.class);
	    throw new IBusException("Booking time is over...");
	} else {
	    payment.setBooking(booking);
	    payment.setStatus("paid");
	    paymentDto = mapper.map(paymentRepository.save(payment), PaymentDto.class);
	}
	return paymentDto;
    }

}