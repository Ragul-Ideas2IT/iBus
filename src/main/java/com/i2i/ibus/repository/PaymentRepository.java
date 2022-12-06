package com.i2i.ibus.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.i2i.ibus.model.Payment;
import com.i2i.ibus.model.User;

/**
 * @author Tamilmani
 * @version 1.0
 * 
 * @created Nov 30 2022
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer>{  
    
    @Query("from Payment where isDeleted = false and id = :id")
    Optional<Payment> findById(int id);

    @Query("from Payment p join p.booking pb where p.isDeleted = false and pb.id = :bookingId")
    List<Payment> getAllPaymentsByBookingId(int bookingId);

    @Query(value = "UPDATE payment SET is_deleted = true where booking_id = :bookingId and is_deleted = false", nativeQuery = true)
    void deleteAllByBookingId(int bookingId);

}