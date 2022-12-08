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

    public void validateMailIdAndPhoneNo(String mailId, String phoneNumber) {
	Optional<User> user = userRepository.findByMailIdAndPhoneNo(mailId, phoneNumber);
	if (user.isPresent()) {
	    throw new IBusException("Mail Id and Phone No already exists");
	}
    }

    public void validateMailIdAndPhoneNoForUpdate(String mailId, String phoneNumber, int id) {
	Optional<User> user = userRepository.findByMailIdAndPhoneNoForUpdate(mailId, phoneNumber, id);
	if (user.isPresent()) {
	    throw new IBusException("Mail Id and Phone No already exists");
	}
    }

    public UserDto saveUser(UserDto userDto) {
	validateMailIdAndPhoneNo(userDto.getMailId(), userDto.getPhoneNumber());
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
	validateMailIdAndPhoneNoForUpdate(userDto.getMailId(), userDto.getPhoneNumber(), id);
	userDto.setId(id);
	User user = Mapper.toUser(userDto);
	return Mapper.toUserDto(userRepository.save(user));
    }

    public void deleteUserById(int id) {
	validateUser(id);
	userRepository.deleteById(id);
    }
}
