package com.i2i.ibus.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.i2i.ibus.model.Operator;
/**
 * @author Ragul
 *
 */
@Repository
public interface OperatorRepository extends JpaRepository<Operator, Integer>{
    Optional<Operator> findByMailId(String mailId);

    Optional<Operator> findByPhoneNumber(String phoneNumber);
}
