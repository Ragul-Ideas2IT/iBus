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
 * @since Nov 29 2022
 *
 */
@Repository
public interface OperatorRepository extends JpaRepository<Operator, Integer> {

    Optional<Operator> findByPhoneNumber(String phoneNumber);
    Optional<Operator> findByMailId(String mailId);
    Optional<Operator> findByGstNumber(String gstNumber);

    Optional<Operator> findByPhoneNumberAndIdNot(String phoneNumber, int id);
    Optional<Operator> findByMailIdAndIdNot(String mailId, int id);
    Optional<Operator> findByGstNumberAndIdNot(String gstNumber, int id);

}
