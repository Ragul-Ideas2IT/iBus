package com.i2i.ibus.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

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
public interface PaymentService {
     /**
      * Create a payment for a booking.
      *
      * @param bookingId The id of the booking that the payment is for.
      * @param paymentDto The payment details.
      * @return A PaymentDto object
      */
     PaymentDto createPayment(int bookingId, PaymentDto paymentDto);

	/**
	 * Get a payment by its payment ID.
	 *
	 * @param paymentId The id of the payment you want to get.
	 * @return PaymentDto
	 */
	PaymentDto getPaymentByPaymentId(int paymentId);

	/**
	 * Get all payments for a booking.
	 *
	 * @param bookingId The id of the booking you want to get the payments for.
	 * @return A list of PaymentDto objects.
	 */
	List<PaymentDto> getAllPaymentsByBookingId(int bookingId);

	/**
	 * Delete all payments for a booking
	 *
	 * @param bookingId The id of the booking to delete payments for.
	 */
	void deleteAllPaymentsByBookingId(int bookingId);

	/**
	 * Delete a payment by paymentId
	 *
	 * @param paymentId The id of the payment to be deleted.
	 */
	void deleteByPaymentId(int paymentId);

	/**
	 * Delete all payments from the database.
	 */
	void deleteAllPayments();
}