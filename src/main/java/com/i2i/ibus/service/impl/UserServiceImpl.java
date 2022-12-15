/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.service.impl;

import com.i2i.ibus.dto.UserDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.User;
import com.i2i.ibus.repository.UserRepository;
import com.i2i.ibus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Ragul
 * @version 1.0
 * @since Nov 29 2022
 */
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User validateUser(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new IBusException("User Id doesn't exists");
        }
        return user.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateMailId(String mailId) {
        if (userRepository.findByMailId(mailId).isPresent()) {
            throw new IBusException("Mail Id already exists");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validatePhoneNumber(String phoneNumber) {
        if (userRepository.findByPhoneNumber(phoneNumber).isPresent()) {
            throw new IBusException("Phone number already exists");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateMailIdForUpdate(String mailId, int id) {
        Optional<User> user = userRepository.findByMailIdAndIdNot(mailId, id);
        if (user.isPresent()) {
            throw new IBusException("Mail Id already exists");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validatePhoneNoForUpdate(String phoneNumber, int id) {
        Optional<User> user = userRepository.findByPhoneNumberAndIdNot(phoneNumber, id);
        if (user.isPresent()) {
            throw new IBusException("Phone number already exists");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDto saveUser(UserDto userDto) {
        validateMailId(userDto.getMailId());
        validatePhoneNumber(userDto.getPhoneNumber());
        User user = Mapper.toUser(userDto);
        return Mapper.toUserDto(userRepository.save(user));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserDto> getAllUsersDto() {
        return Mapper.toUserDTOs(userRepository.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDto getUserDtoById(int id) {
        validateUser(id);
        return Mapper.toUserDto(userRepository.findById(id).get());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDto updateUserById(int id, UserDto userDto) {
        validateUser(id);
        validateMailIdForUpdate(userDto.getMailId(), id);
        validatePhoneNoForUpdate(userDto.getPhoneNumber(), id);
        userDto.setId(id);
        User user = Mapper.toUser(userDto);
        return Mapper.toUserDto(userRepository.save(user));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteUserById(int id) {
        User user = validateUser(id);
        user.setDeleted(true);
        userRepository.save(user);
    }
}
