package com.i2i.ibus.service.impl;

import com.i2i.ibus.dto.UserDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.Account;
import com.i2i.ibus.model.User;
import com.i2i.ibus.repository.AccountRepository;
import com.i2i.ibus.repository.UserRepository;
import com.i2i.ibus.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserRepository userRepositoryDB;
    
    @Autowired
    private AccountRepository accountRepositoryDB;
    
    @Mock
    private UserRepository userRepository;
    
    @Mock
    private AccountService accountService;
    
    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private UserDto userDto;
    
    private Account account;

    private List<User> users;

    private List<UserDto> userDtos;
    
    @BeforeEach
    void setUp() {
        int id = 1;
        user = userRepositoryDB.findById(id).get();
        users = userRepositoryDB.findAll();
        userDto = Mapper.toUserDto(user);
        userDtos = Mapper.toUserDTOs(users);
        account = accountRepositoryDB.findByMailId(user.getMailId()).get();
        userDto.setPassword(account.getPassword());
    }

    @Test
    void validateUser() {
        when(userRepository.findById(userDto.getId())).thenReturn(Optional.of(user));
        assertThrows(IBusException.class, () -> userService.validateUser(2));
    }

    @Test
    void validateMailId() {
        when(userRepository.findByMailId(user.getMailId())).thenReturn(Optional.of(user));
        assertThrows(IBusException.class, () -> userService.validateMailId(user.getMailId()));
    }

    @Test
    void validatePhoneNumber() {
        when(userRepository.findByPhoneNumber(user.getPhoneNumber())).thenReturn(Optional.of(user));
        assertThrows(IBusException.class, () -> userService.validatePhoneNumber(user.getPhoneNumber()));
    }

    @Test
    void validateMailIdForUpdate() {
        when(userRepository.findByMailIdAndIdNot(user.getMailId(), user.getId())).thenReturn(Optional.of(user));
        assertThrows(IBusException.class, () -> userService.validateMailIdForUpdate(user.getMailId(),
                user.getId()));
    }

    @Test
    void validatePhoneNoForUpdate() {
        when(userRepository.findByPhoneNumberAndIdNot(user.getPhoneNumber(), user.getId())).thenReturn(Optional.of(user));
        assertThrows(IBusException.class,
                () -> userService.validatePhoneNoForUpdate(user.getPhoneNumber(), user.getId()));
    }

    @Test
    void saveUser() {
        when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(user);
        when(accountService.addAccount(account)).thenReturn(account);
        UserDto newUser = userService.saveUser(userDto);
        assertNotNull(newUser);
        assertEquals(user.getName(), newUser.getName());
    }

    @Test
    void getAllUsersDto() {
        when(userRepository.findAll()).thenReturn(userRepositoryDB.findAll());
        List<UserDto> userDtosService = userService.getAllUsersDto();
        assertEquals(users.size(), userDtosService.size());
        for (int i = 0; i < userDtos.size(); i++) {
            assertEquals(userDtos.get(i).getMailId(), userDtosService.get(i).getMailId());
            System.out.println(userDtos.get(i).getMailId());
            System.out.println(userDtosService.get(i).getMailId());
        }
        assertNotNull(userDtosService);
    }

    @Test
    void getUserDtoById() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        UserDto newUser = userService.getUserDtoById(user.getId());
        assertNotNull(newUser);
        assertEquals(user.getId(), newUser.getId());
    }

    @Test
    void updateUserById() {
        userDto.setName("Jackson");
        user.setName("Jackson");
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(user);
        UserDto newUser = userService.updateUserById(user.getId(), userDto);
        assertNotNull(newUser);
        assertEquals("Jackson", newUser.getName());
    }

    @Test
    void deleteUserById() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        userService.deleteUserById(user.getId());
        assertTrue(user.isDeleted());
    }
}