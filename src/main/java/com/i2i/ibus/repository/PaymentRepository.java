package com.i2i.ibus.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.i2i.ibus.model.Payment;

import jakarta.transaction.Transactional;

/**
 * @author Tamilmani
 * @version 1.0
 * 
 * @created Nov 30 2022
 */
@Repository
@Transactional
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    @Query("from Payment where isDeleted = false and id = :id")
    Optional<Payment> findById(int id);

    @Query("from Payment payment join payment.booking payment_booking where payment.isDeleted = false and payment_booking.id = :bookingId")
    List<Payment> getAllPaymentsByBookingId(int bookingId);

    @Modifying
    @Query(value = "update payment set is_deleted = true where booking_id=:bookingId and is_deleted = false", nativeQuery = true)
    void deleteAllPaymentsByBookingId(int bookingId);

}