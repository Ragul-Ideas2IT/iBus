package com.i2i.ibus.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.i2i.ibus.dto.UserDto;
import com.i2i.ibus.exception.AlreadyExistException;
import com.i2i.ibus.model.User;
import com.i2i.ibus.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * @author Ragul
 *
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private ModelMapper mapper = new ModelMapper();

    public void validatePhoneNo(String phoneNumber)
	    throws AlreadyExistException {
	User user = userRepository.findByPhoneNumber(phoneNumber).get();
	if (!user.isDeleted() && (null != user)) {
	    throw new AlreadyExistException(
		    phoneNumber.concat(" Already exists"));
	}
    }

    public void validateMailId(String mailId) throws AlreadyExistException {
	User user = userRepository.findByMailId(mailId).get();
	if (!user.isDeleted() && (null != user)) {
	    throw new AlreadyExistException(mailId.concat(" Already exists"));
	}
    }

    public UserDto saveUser(UserDto userDto) {
	User user = mapper.map(userDto, User.class);
	return mapper.map(userRepository.save(user), UserDto.class);
    }

    public List<UserDto> getAllUserDtos() {
	return userRepository.findAll().stream()
		.map(user -> mapper.map(user, UserDto.class))
		.collect(Collectors.toList());
    }

    public UserDto getUserDtoById(int id) {
	return mapper.map(userRepository.findById(id), UserDto.class);
    }
    
    public UserDto updateUserById(UserDto userDto) {
	return saveUser(userDto);
    }
    
    public void deleteUserById(int id) {
	userRepository.deleteById(id);
    }
}
