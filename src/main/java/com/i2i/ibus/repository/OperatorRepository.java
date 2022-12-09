package com.i2i.ibus.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.i2i.ibus.model.Operator;

/**
 * @author Ragul
 * @version 1.0
 * 
 * @created Nov 29 2022
 *
 */
@Repository
public interface OperatorRepository extends JpaRepository<Operator, Integer> {
    Optional<Operator> findByMailIdAndPhoneNumberAndGstNumber(String mailId, String phoneNumber, String gstNumber);
    Optional<Operator> findByMailIdAndPhoneNumber(String mailId, String phoneNumber);

    @Query("from Operator where mailId = :mailId and phoneNumber = :phoneNumber and gstNumber = :gstNumber and id != :id")
    Optional<Operator> findByMailIdPhoneNoAndGstNumberForUpdate(String mailId, String phoneNumber, String gstNumber,
	    int id);

}
