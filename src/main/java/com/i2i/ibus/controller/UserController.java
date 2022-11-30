package com.i2i.ibus.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.i2i.ibus.dto.MessageDto;
import com.i2i.ibus.dto.UserDto;
import com.i2i.ibus.service.UserService;

import lombok.RequiredArgsConstructor;

/**
 * @author Ragul
 * @version 1.0
 * 
 * @created Nov 29 2022
 *
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    private UserDto createUser(@RequestBody @Validated UserDto userDto) {
	return userService.saveUser(userDto);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    private List<UserDto> getAllUserDtos() {
	return userService.getAllUserDtos();
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    private UserDto getUserDtoById(@PathVariable int id) {
	return userService.getUserDtoById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    private UserDto updateUser(@PathVariable int id, @RequestBody @Validated UserDto userDto) {
	return userService.updateUserById(userDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    private MessageDto deleteUser(@PathVariable int id) {
	userService.deleteUserById(id);
	return new MessageDto("202", "Deleted Successfully");
    }

}
