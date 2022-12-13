package com.i2i.ibus.service;

import com.i2i.ibus.dto.UserDto;

import java.util.List;

/**
 * @author Ragul
 * @version 1.0
 * @created Nov 29 2022
 */
public interface UserService {

    /**
     * If the user doesn't exist, throw an exception.
     *
     * @param id The id of the user to be validated.
     */
    void validateUser(int id);

    void validateMailId(String mailId);

    void validatePhoneNumber(String phoneNumber);

    /**
     * It checks if the mail id and phone number already exists in the database for a given id
     *
     * @param mailId The mailId to be validated.
     * @param phoneNumber The phone number to be validated.
     * @param id The id of the user that is being updated.
     */
    void validateMailIdAndPhoneNoForUpdate(String mailId, String phoneNumber, int id);

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
    List<UserDto> getAllUserDtos();

    /**
     * Get a user DTO by id.
     *
     * @param id The id of the user you want to get.
     * @return UserDto
     */
    UserDto getUserDtoById(int id);

    /**
     * It updates the user with the given id with the given userDto
     *
     * @param id The id of the user to be updated.
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
