package com.i2i.ibus.repository;

import java.util.List;
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
public interface OperatorRepository extends JpaRepository<Operator, Integer>{
    @Query("from Operator where isDeleted = false and mailId = :mailId")
    Optional<Operator> findByMailId(String mailId);

    @Query("from Operator where isDeleted = false and phoneNumber = :phoneNumber")
    Optional<Operator> findByPhoneNumber(String phoneNumber);

    @Query("from Operator where isDeleted = false and gstNumber = :gstNumber")
    Optional<Operator> findByGstNumber(String gstNumber);
    
    @Query("from Operator where isDeleted = false")
    List<Operator> findAll();

    @Query("from Operator where isDeleted = false and id = :id")
    Optional<Operator> findById(int id);
}
