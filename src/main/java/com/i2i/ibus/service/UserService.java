package com.i2i.ibus.service;

import org.springframework.stereotype.Service;

import com.i2i.ibus.exception.AlreadyExistException;
import com.i2i.ibus.model.User;
import com.i2i.ibus.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private UserRepository userRepository;
    
    public void validatePhoneNo(String phoneNumber) throws AlreadyExistException {
	User user = userRepository.findByPhoneNumber(phoneNumber).get();
	if (user.isActive() && (null != user)) {
	    throw new AlreadyExistException(phoneNumber.concat(" Already exists"));
	}
    }
    
    public void validateMailId(String mailId) throws AlreadyExistException {
	User user = userRepository.findByMailId(mailId).get();
	if (user.isActive() && (null != user)) {
	    throw new AlreadyExistException(mailId.concat(" Already exists"));
	}
    }
}
