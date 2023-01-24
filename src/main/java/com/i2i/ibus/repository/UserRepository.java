/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.repository;

import com.i2i.ibus.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Creating a repository for the User model which extends JPA repository
 *
 * @author Ragul
 * @version 1.0
 * @since Nov 29 2022
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
     * Find a user by phone number, and return an Optional containing the user if found, or an empty Optional
     * if not found.
     *
     * @param phoneNumber The phone number of the user to find.
     * @return Optional<User>
     */
    Optional<User> findByPhoneNumber(String phoneNumber);

    /**
     * Find a user by mail id and id not equal to the given id.
     *
     * @param mailId The mailId to search for.
     * @param id The id of the user to be excluded from the search.
     * @return Optional<User>
     */
    Optional<User> findByMailIdAndIdNot(String mailId, int id);

    /**
     * Find a user by phone number and id, but not the user with the given id.
     *
     * @param phoneNumber The phone number to search for.
     * @param id The id of the user to exclude from the search.
     * @return Optional<User>
     */
    Optional<User> findByPhoneNumberAndIdNot(String phoneNumber, int id);
}
