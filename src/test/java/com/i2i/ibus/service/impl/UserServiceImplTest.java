package com.i2i.ibus.service.impl;

import com.i2i.ibus.constants.Constants;
import com.i2i.ibus.dto.UserDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.Account;
import com.i2i.ibus.model.User;
import com.i2i.ibus.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AccountServiceImpl accountService;

    @InjectMocks
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepositoryDB;

    User user;
    UserDto userDto;
    List<User> users;
    List<UserDto> userDtos;

    int id;
    int notExistId;
    String password;

    @BeforeAll
    public void init() {
        id = 1;
        notExistId = 2;
    }

    @BeforeEach
    public void setup() {
        password = "Tamil@123";
        user = userRepositoryDB.findById(id).get();
        userDto = Mapper.toUserDto(user);
        userDto.setPassword(password);
    }

    @Test
    public void validateUser() {
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        User newUser = userService.validateUser(id);
        Assertions.assertEquals(user.getMailId(), newUser.getMailId());
        Assertions.assertThrows(IBusException.class, () -> userService.validateUser(notExistId));
    }

    @Test
    public void validateMailId() {
        when(userRepository.findByMailId(user.getMailId())).thenReturn(Optional.of(user));
        Assertions.assertThrows(IBusException.class, () -> userService.validateMailId(user.getMailId()));
    }

    @Test
    public void validatePhoneNumber() {
        when(userRepository.findByPhoneNumber(anyString())).thenReturn(Optional.of(user));
        Assertions.assertThrows(IBusException.class, () -> userService.validatePhoneNumber(user.getPhoneNumber()));
    }

    @Test
    public void validateMailIdForUpdate() {
        when(userRepository.findByMailIdAndIdNot(user.getMailId(), notExistId)).thenReturn(Optional.of(user));
        Assertions.assertThrows(IBusException.class, () -> userService.validateMailIdForUpdate(user.getMailId(), notExistId));
    }

    @Test
    public void validatePhoneNoForUpdate() {
        when(userRepository.findByPhoneNumberAndIdNot(user.getPhoneNumber(), notExistId)).thenReturn(Optional.of(user));
        Assertions.assertThrows(IBusException.class, () -> userService.validatePhoneNoForUpdate(user.getPhoneNumber(), notExistId));
    }

    @Test
    public void saveUser() {
        when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(user);
        when(accountService.addAccount(ArgumentMatchers.any(Account.class))).thenReturn(new Account(userDto.getMailId(), Constants.ROLE_USER, userDto.getPassword()));
        UserDto newUserDto = userService.saveUser(userDto);
        Assertions.assertEquals(user.getMailId(), newUserDto.getMailId());
        org.assertj.core.api.Assertions.assertThat(newUserDto).usingRecursiveComparison().ignoringFields("password").isEqualTo(userDto);
    }

    @Test
    public void getAllUsersDto() {
        users = userRepositoryDB.findAll();
        when(userRepository.findAll()).thenReturn(users);
        List<User> newUsers = userRepository.findAll();
        userService.getAllUsersDto();
        Assertions.assertEquals(users.size(), newUsers.size());
        for (int i = 0; i < newUsers.size(); i++) {
            org.assertj.core.api.Assertions.assertThat(newUsers.get(i)).usingRecursiveComparison().isEqualTo(users.get(i));
        }
    }

    @Test
    public void getUserDtoById() {
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        UserDto newUserDto = userService.getUserDtoById(user.getId());
        Assertions.assertNotNull(newUserDto);
        Assertions.assertEquals(user.getId(), newUserDto.getId());
        org.assertj.core.api.Assertions.assertThat(newUserDto).usingRecursiveComparison().ignoringFields("password").isEqualTo(userDto);
    }

    @Test
    public void updateUserById() {
        String emailId = "tamil@gmail.com";
        user.setMailId(emailId);
        when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(user);
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        UserDto newUserDto = userService.updateUserById(id, userDto);
        Assertions.assertNotEquals(userDto.getMailId(), newUserDto.getMailId());
    }

    @Test
    public void deleteUserById() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        userService.deleteUserById(id);
        Assertions.assertTrue(user.isDeleted());
        verify(userRepository, times(1)).save(user);
    }

}
