/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.security;

import com.i2i.ibus.model.Account;
import com.i2i.ibus.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implements the UserDetailsService interface and overrides the loadUserByUsername method
 *
 * @author Ragul
 * @version 1.0
 * @since Dec 12 2022
 */
@Service
public class AccountDetailService implements UserDetailsService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountDetailService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDetails loadUserByUsername(String mailId)
            throws UsernameNotFoundException {
        Account account = accountRepository.findByMailId(mailId);
        if (null == account) {
            throw new UsernameNotFoundException(mailId.concat(" not found"));
        }
        return new AccountDetails(account);
    }
}
