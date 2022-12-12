package com.i2i.ibus.service.impl;

import com.i2i.ibus.dto.UserDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.User;
import com.i2i.ibus.repository.OperatorRepository;
import com.i2i.ibus.repository.UserRepository;
import com.i2i.ibus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Ragul
 * @version 1.0
 * @created Nov 29 2022
 */
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private OperatorRepository operatorRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, OperatorRepository operatorRepository) {
        this.userRepository = userRepository;
        this.operatorRepository = operatorRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateUser(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            throw new IBusException("User Id doesn't exists");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateMailIdAndPhoneNo(String mailId, String phoneNumber) {
        if (userRepository.findByMailIdAndPhoneNumber(mailId, phoneNumber).isPresent()) {
            throw new IBusException("Mail Id and Phone No already exists");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateMailIdAndPhoneNoForUpdate(String mailId, String phoneNumber, int id) {
        Optional<User> user = userRepository.findByMailIdAndPhoneNoForUpdate(mailId, phoneNumber, id);
        if (user.isPresent()) {
            throw new IBusException("Mail Id and Phone No already exists");
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public UserDto saveUser(UserDto userDto) {
        validateMailIdAndPhoneNo(userDto.getMailId(), userDto.getPhoneNumber());
        User user = Mapper.toUser(userDto);
        return Mapper.toUserDto(userRepository.save(user));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserDto> getAllUserDtos() {
        return Mapper.toUserDtos(userRepository.findAll());
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
        validateMailIdAndPhoneNoForUpdate(userDto.getMailId(), userDto.getPhoneNumber(), id);
        userDto.setId(id);
        User user = Mapper.toUser(userDto);
        return Mapper.toUserDto(userRepository.save(user));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteUserById(int id) {
        validateUser(id);
        userRepository.deleteById(id);
    }
}
