package com.i2i.ibus.repository;

import java.util.List;
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
    @Query("from User where isDeleted = false and mailId = :mailId")
    Optional<User> findByMailId(String mailId);

    @Query("from User where isDeleted = false and phoneNumber = :phoneNumber")
    Optional<User> findByPhoneNumber(String phoneNumber);

    @Query("from User where isDeleted = false")
    List<User> findAll();

    @Query("from User where isDeleted = false and id = :id")
    Optional<User> findById(int id);
}
