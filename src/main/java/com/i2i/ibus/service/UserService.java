/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.i2i.ibus.service;

import com.i2i.ibus.dto.UserDto;
import com.i2i.ibus.model.User;

import java.util.List;

/**
 * A service Interface contains all the business logic related to the User
 *
 * @author Ragul
 * @version 1.0
 * @since Nov 29 2022
 */
public interface UserService {

    /**
     * Validate the user. If the user is not present, it will throw an exception.
     *
     * @param id The id of the user to validate.
     * @return A User object.
     */
    User validateUser(int id);

    /**
     * Validate the mail id. If the mail id is already present, it will throw an exception.
     *
     * @param mailId The mail id to be validated.
     */
    void validateMailId(String mailId);

    /**
     * Validate the phone number. If the phone number is already present, it will throw an
     * exception.
     *
     * @param phoneNumber A string representing a phone number.
     */
    void validatePhoneNumber(String phoneNumber);


    /**
     * Validate the mail id. If the mail id is already present except particular object mailId, it will throw an
     * exception.
     *
     * @param mailId The mail id to be validated.
     * @param id The id of the user whose mail id is to not be used for validation.
     */
    void validateMailIdForUpdate(String mailId, int id);

    /**
     * Validate the phone number except particular object phone number. If the phone number is
     * already present, it will throw an exception.
     *
     * @param phoneNumber The phone number to be validated.
     * @param id The id of the user whose phone number is being not validated.
     */
    void validatePhoneNoForUpdate(String phoneNumber, int id);

    /**
     * Save a user and return the saved user.
     *
     * @param userDto This is the object that we want to save.
     * @return UserDto
     */
    UserDto saveUser(UserDto userDto);

    /**
     * Get all user DTOs.
     *
     * @return A list of UserDto objects.
     */
    List<UserDto> getAllUsersDto();

    /**
     * Get a user DTO by id.
     *
     * @param id The id of the user you want to get.
     * @return UserDto
     */
    UserDto getUserDtoById(int id);

    /**
     * Updates the user with the given id with the given userDto
     *
     * @param id      The id of the user to be updated.
     * @param userDto The userDto object that contains the updated user details.
     * @return UserDto
     */
    UserDto updateUserById(int id, UserDto userDto);

    /**
     * Delete a user by id, but first validate that the user exists.
     *
     * @param id The id of the user to be deleted.
     */
    void deleteUserById(int id);
}
