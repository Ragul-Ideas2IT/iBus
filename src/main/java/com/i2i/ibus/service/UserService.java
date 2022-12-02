package com.i2i.ibus.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.i2i.ibus.dto.UserDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.model.User;
import com.i2i.ibus.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * @author Ragul
 * @version 1.0
 * 
 * @created Nov 29 2022
 *
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    
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
	User user = mapper.map(userDto, User.class);
	return mapper.map(userRepository.save(user), UserDto.class);
    }

    public List<UserDto> getAllUserDtos() {
	return userRepository.findAll().stream().map(user -> mapper.map(user, UserDto.class))
		.collect(Collectors.toList());
    }

    public UserDto getUserDtoById(int id) throws IBusException {
	validateUser(id);
	return mapper.map(userRepository.findById(id), UserDto.class);
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
