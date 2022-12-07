package com.i2i.ibus.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.ibus.dto.UserDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.User;
import com.i2i.ibus.repository.UserRepository;

/**
 * @author Ragul
 * @version 1.0
 * 
 * @created Nov 29 2022
 *
 */
@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
	this.userRepository = userRepository;
    }

    public void validateUser(int id) {
	Optional<User> user = userRepository.findById(id);
	if (!user.isPresent()) {
	    throw new IBusException("User Id doesn't exists");
	}
    }

    public void validatePhoneNo(String phoneNumber) {
	Optional<User> user = userRepository.findByPhoneNumber(phoneNumber);
	if (user.isPresent()) {
	    throw new IBusException(phoneNumber.concat(" Already exists"));
	}
    }

    public void validateMailId(String mailId) {
	Optional<User> user = userRepository.findByMailId(mailId);
	if (user.isPresent()) {
	    throw new IBusException(mailId.concat(" Already exists"));
	}
    }

    public UserDto saveUser(UserDto userDto) {
	validateMailId(userDto.getMailId());
	validatePhoneNo(userDto.getPhoneNumber());
	User user = Mapper.toUser(userDto);
	return Mapper.toUserDto(userRepository.save(user));
    }

    public List<UserDto> getAllUserDtos() {
	return Mapper.toUserDtos(userRepository.findAll());
    }

    public UserDto getUserDtoById(int id) {
	validateUser(id);
	return Mapper.toUserDto(userRepository.findById(id).get());
    }

    public UserDto updateUserById(int id, UserDto userDto) {
	validateUser(id);
	userDto.setId(id);
	return saveUser(userDto);
    }

    public void deleteUserById(int id) {
	validateUser(id);
	userRepository.deleteById(id);
    }
}
