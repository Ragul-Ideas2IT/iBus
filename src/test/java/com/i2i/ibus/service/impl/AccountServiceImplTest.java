package com.i2i.ibus.service.impl;

import com.i2i.ibus.model.Account;
import com.i2i.ibus.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AccountServiceImplTest {

    @Autowired
    private AccountRepository accountRepositoryDB;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AccountServiceImpl accountService;

    private Account accountDB;

    @BeforeEach
    void setUp() {
        String mailId = "ragulOperator@gmail.com";
        accountDB = accountRepositoryDB.findByMailId(mailId).get();
    }

    @Test
    void addAccount() {
        when(accountRepository.save(accountDB)).thenReturn(accountDB);
        when(passwordEncoder.encode(accountDB.getPassword())).thenReturn(accountDB.getPassword());
        Account account = accountService.addAccount(accountDB);
        assertNotNull(account);
        assertEquals(account.getMailId(), accountDB.getMailId());
        assertEquals(account.getRole(), accountDB.getRole());
    }
}