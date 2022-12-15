/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.service.impl;

import com.i2i.ibus.dto.AccountDto;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.Account;
import com.i2i.ibus.repository.AccountRepository;
import com.i2i.ibus.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Ragul
 * @version 1.0
 * @since Dec 12 2022
 */
@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Account addAccount(AccountDto accountDto) {
        accountDto.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        Account account = Mapper.toAccount(accountDto);
        return accountRepository.save(account);
    }
}
