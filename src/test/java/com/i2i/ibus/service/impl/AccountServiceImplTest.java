package com.i2i.ibus.service.impl;

import com.i2i.ibus.model.Account;
import com.i2i.ibus.repository.AccountRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.when;

@SpringBootTest
@Log4j2
class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private PasswordEncoder passwordEncoderMock;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepositoryDB;
    String mailId;
    Account account;
    String password;

    @BeforeEach
    void setUp() {
        mailId = "tamil@gmail.com";
        account = accountRepositoryDB.findByMailId(mailId).get();
        password = passwordEncoder.encode(account.getPassword());
    }

    @Test
    void addAccount() {
        when(accountRepository.save(account)).thenReturn(account);
        when(passwordEncoderMock.encode(account.getPassword())).thenReturn(password);
        Account newAccount = accountService.addAccount(account);
        Assertions.assertEquals(account.getMailId(), newAccount.getMailId());
        log.info(account.getMailId());
        log.info(newAccount.getMailId());
    }
}