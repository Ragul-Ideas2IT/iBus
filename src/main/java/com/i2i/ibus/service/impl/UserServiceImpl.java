/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.service.impl;

import com.i2i.ibus.constants.Constants;
import com.i2i.ibus.dto.UserDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.Account;
import com.i2i.ibus.model.User;
import com.i2i.ibus.repository.UserRepository;
import com.i2i.ibus.service.AccountService;
import com.i2i.ibus.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * A service class that implements the UserService interface and uses the UserRepository to save a User
 * object. It provides the implementation for all the methods. It contains all the business logic related to the
 * User
 *
 * @author Ragul
 * @version 1.0
 * @since Nov 29 2022
 */
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private AccountService accountService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AccountService accountService) {
        this.userRepository = userRepository;
        this.accountService = accountService;
    }

    private Logger logger = LogManager.getLogger(UserServiceImpl.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public User validateUser(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            logger.error(Constants.USER_NOT_EXIST);
            throw new IBusException(Constants.USER_NOT_EXIST);
        }
        return user.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateMailId(String mailId) {
        if (userRepository.findByMailId(mailId).isPresent()) {
            logger.error(Constants.MAIL_ID_ALREADY_EXISTS);
            throw new IBusException(Constants.MAIL_ID_ALREADY_EXISTS);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validatePhoneNumber(String phoneNumber) {
        if (userRepository.findByPhoneNumber(phoneNumber).isPresent()) {
            logger.error(Constants.PHONE_NUMBER_ALREADY_EXISTS);
            throw new IBusException(Constants.PHONE_NUMBER_ALREADY_EXISTS);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateMailIdForUpdate(String mailId, int id) {
        Optional<User> user = userRepository.findByMailIdAndIdNot(mailId, id);
        if (user.isPresent()) {
            logger.error(Constants.MAIL_ID_ALREADY_EXISTS);
            throw new IBusException(Constants.MAIL_ID_ALREADY_EXISTS);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validatePhoneNoForUpdate(String phoneNumber, int id) {
        Optional<User> user = userRepository.findByPhoneNumberAndIdNot(phoneNumber, id);
        if (user.isPresent()) {
            logger.error(Constants.PHONE_NUMBER_ALREADY_EXISTS);
            throw new IBusException(Constants.PHONE_NUMBER_ALREADY_EXISTS);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDto saveUser(UserDto userDto) {
        validateMailId(userDto.getMailId());
        validatePhoneNumber(userDto.getPhoneNumber());
        logger.info(Constants.CREATE_MESSAGE + userDto.getId());
        accountService.addAccount(new Account(userDto.getMailId(), "ROLE_USER", userDto.getPassword()));
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
        logger.info(Constants.UPDATE_MESSAGE + userDto.getId());
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
        logger.info(Constants.DELETE_MESSAGE + id);
        userRepository.save(user);
    }
}
