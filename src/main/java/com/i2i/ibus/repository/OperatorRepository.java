package com.i2i.ibus.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.i2i.ibus.model.Operator;
import com.i2i.ibus.model.User;

/**
 * @author Ragul
 * @version 1.0
 * 
 * @created Nov 29 2022
 *
 */
@Repository
public interface OperatorRepository extends JpaRepository<Operator, Integer> {
    @Query("from Operator where isDeleted = false and mailId = :mailId and phoneNumber = :phoneNumber and gstNumber = :gstNumber")
    Optional<Operator> findByMailIdPhoneNoAndGstNumber(String mailId, String phoneNumber, String gstNumber);

    @Query("from Operator where isDeleted = false and mailId = :mailId and phoneNumber = :phoneNumber and gstNumber = :gstNumber and id != :id")
    Optional<Operator> findByMailIdPhoneNoAndGstNumberForUpdate(String mailId, String phoneNumber, String gstNumber,
	    int id);

    @Query("from Operator where isDeleted = false")
    List<Operator> findAll();

    @Query("from Operator where isDeleted = false and id = :id")
    Optional<Operator> findById(int id);
}
