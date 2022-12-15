/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.service.impl;

import com.i2i.ibus.constants.Constants;
import com.i2i.ibus.model.Account;
import com.i2i.ibus.repository.AccountRepository;
import com.i2i.ibus.service.AccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *  A service class that implements the AccountService interface and uses the AccountRepository to save an Account
 *  object. It provides the implementation for all the methods.
 *
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
    private Logger logger = LogManager.getLogger(AccountServiceImpl.class);


    @Override
    public Account addAccount(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        logger.info(Constants.CREATE_MESSAGE.concat(account.getMailId()));
        return accountRepository.save(account);
    }
}
