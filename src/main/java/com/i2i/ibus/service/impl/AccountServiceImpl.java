package com.i2i.ibus.service.impl;

import com.i2i.ibus.dto.AccountDto;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.Account;
import com.i2i.ibus.repository.AccountRepository;
import com.i2i.ibus.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account addAccount(AccountDto accountDto) {
        return accountRepository.save(Mapper.toAccount(accountDto));
    }
}
