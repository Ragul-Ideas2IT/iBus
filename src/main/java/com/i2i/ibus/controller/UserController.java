/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.controller;

import com.i2i.ibus.constants.Constants;
import com.i2i.ibus.dto.MessageDto;
import com.i2i.ibus.dto.UserDto;
import com.i2i.ibus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller class that handles the requests that are related to the users that are sent to the
 * endpoint and passes the DTO to the {@code UserService} and receive the response from the {@code UserService}
 *
 * @author Ragul
 * @version 1.0
 * @since Nov 29 2022
 */
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    private UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * The function is a POST request that returns a UserDto object
     *
     * @param userDto The object that will be created.
     * @return UserDto
     */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    private UserDto createUser(@RequestBody @Valid UserDto userDto) {
        return userService.saveUser(userDto);
    }


    /**
     * This function returns a list of all the users in the database
     *
     * @return A list of UserDto objects.
     */
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    private List<UserDto> getAllUsers() {
        return userService.getAllUsersDto();
    }

    /**
     * This function returns a UserDto object with the id specified in the URL
     *
     * @param id The id of the user to be retrieved.
     * @return UserDto
     */
    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    private UserDto getUserDtoById(@PathVariable int id) {
        return userService.getUserDtoById(id);
    }

    /**
     * It updates the user with the given id with the given userDto.
     *
     * @param id      The id of the user to be updated.
     * @param userDto The user object that will be updated.
     * @return UserDto
     */
    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    private UserDto updateUser(@PathVariable int id, @RequestBody @Valid UserDto userDto) {
        return userService.updateUserById(id, userDto);
    }

    /**
     * It deletes a user by id.
     *
     * @param id The id of the user to be deleted.
     * @return A MessageDto object with a status code and a message.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    private MessageDto deleteUser(@PathVariable int id) {
        userService.deleteUserById(id);
        return new MessageDto(Constants.EVERYTHING_IS_OK, Constants.DELETE_MESSAGE);
    }
}
