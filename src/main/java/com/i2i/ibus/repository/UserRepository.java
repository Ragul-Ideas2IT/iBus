package com.i2i.ibus.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.i2i.ibus.model.User;

/**
 * @author Ragul
 * @version 1.0
 * 
 * @created Nov 29 2022
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByMailIdAndPhoneNumber(String mailId, String phoneNumber);

    @Query("from User where mailId = :mailId and phoneNumber = :phoneNumber and id != :id")
    Optional<User> findByMailIdAndPhoneNoForUpdate(String mailId, String phoneNumber, int id);
}
