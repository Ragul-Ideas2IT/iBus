package com.i2i.ibus.service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

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
	Booking booking = null;
	booking = bookingRepository.findById(bookingId).orElseThrow(() -> new IBusException("No booking id found..."));
	Payment payment = mapper.map(paymentDto, Payment.class);
	payment.setBooking(booking);
	payment.setTime(LocalDateTime.now());
	payment.setStatus("paid");
	return mapper.map(paymentRepository.save(payment), PaymentDto.class);
    }

    public void deletePayment(int bookingId) {
    }

}