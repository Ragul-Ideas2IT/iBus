package com.i2i.ibus.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
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
    public UserService(UserRepository userRepository, ModelMapper mapper) {
	this.userRepository = userRepository;
    }

    public void validateUser(int id) throws IBusException {
	Optional<User> user = userRepository.findById(id);
	if (user.isPresent() && user.get().isDeleted()) {
	    throw new IBusException("User id doesn't exists because User details deleted");
	} else if(!user.isPresent()) {
	    throw new IBusException("User Id doesn't exists");
	}
    }    

    public void validatePhoneNo(String phoneNumber) throws IBusException {
	Optional<User> user = userRepository.findByPhoneNumber(phoneNumber);
	if (user.isPresent()) {
	    throw new IBusException(phoneNumber.concat(" Already exists"));
	}
    }

    public void validateMailId(String mailId) throws IBusException {
	Optional<User> user = userRepository.findByMailId(mailId);
	if (user.isPresent()) {
	    throw new IBusException(mailId.concat(" Already exists"));
	}
    }

    public UserDto saveUser(UserDto userDto) {
	User user = Mapper.toUser(userDto);
	return Mapper.toUserDto(userRepository.save(user));
    }

    public List<UserDto> getAllUserDtos() {
	return Mapper.toUserDtos(userRepository.findAll());
    }

    public UserDto getUserDtoById(int id) throws IBusException {
	validateUser(id);
	return Mapper.toUserDto(userRepository.findById(id).get());
    }

    public UserDto updateUserById(int id, UserDto userDto) throws IBusException {
	validateUser(id);
	userDto.setId(id);
	return saveUser(userDto);
    }

    public void deleteUserById(int id) throws IBusException {
	validateUser(id);
	userRepository.deleteById(id);
    }
}
