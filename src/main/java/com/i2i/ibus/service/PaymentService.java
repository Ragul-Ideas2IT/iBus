package com.i2i.ibus.service;

import com.i2i.ibus.dto.PaymentDto;

import java.util.List;

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