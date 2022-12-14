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
    /**
     * Find a user by mail id, if it exists.
     *
     * @param mailId The mailId of the user to be searched.
     * @return Optional<User>
     */
    Optional<User> findByMailId(String mailId);
    /**
     * Find a user by phone number, and return an Optional containing the user if found, or an empty Optional if not found.
     *
     * @param phoneNumber The phone number of the user to find.
     * @return Optional<User>
     */
    Optional<User> findByPhoneNumber(String phoneNumber);

    Optional<User> findByMailIdAndIdNot(String mailId, int id);

    Optional<User> findByPhoneNumberAndIdNot(String phoneNumber, int id);
}
