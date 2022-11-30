package com.i2i.ibus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.i2i.ibus.model.Payment;

/**
 * @author Tamilmani
 * @version 1.0
 * 
 * @created Nov 30 2022
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer>{   
}