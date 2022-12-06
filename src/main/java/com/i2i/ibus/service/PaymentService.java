package com.i2i.ibus.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.ibus.dto.PaymentDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
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
	if (5 > ChronoUnit.MINUTES.between(booking.getDateTime(), LocalDateTime.now())) {
	    booking.setPaymentStatus("declined");
	    payment.setStatus("unpaid");
	    paymentDto = Mapper.toPaymentDto(paymentRepository.save(payment));
	    throw new IBusException("Booking time is over...");
	} else if (booking.getTotalFare() != paymentDto.getAmount()) {
	    throw new IBusException("Payment amount is invalid. The amount is " + booking.getTotalFare());
	} else if (booking.getNumberOfSeats() == paymentRepository.getAllPaymentsByBookingId(bookingId).size()) {
	    throw new IBusException("Booking seat limit is reached..");
	} else if (booking.getPaymentStatus() == null) {
	    payment.setStatus("paid");
	    booking.setPaymentStatus("successful");
	    paymentDto = Mapper.toPaymentDto(paymentRepository.save(payment));
	} else {
	    throw new IBusException("Payment already succeeded");
	}
	return paymentDto;
    }

    public List<PaymentDto> getAllPaymentsByBookingId(int bookingId) {
	return Mapper.toPaymentDtos(paymentRepository.getAllPaymentsByBookingId(bookingId));
    }

    public void deleteAllByBookingId(int bookingId) {
	paymentRepository.deleteAllByBookingId(bookingId);
    }

}