package com.i2i.ibus.service;

import com.i2i.ibus.dto.AccountDto;
import com.i2i.ibus.model.Account;

public interface AccountService {
    /**
     * Add an account to the database.
     *
     * @param accountDto The account object that you want to add to the database.
     * @return Account
     */
    Account addAccount(AccountDto accountDto);
}
