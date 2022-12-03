package com.i2i.ibus.repository;

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

}