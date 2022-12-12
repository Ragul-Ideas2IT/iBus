package com.i2i.ibus.service;

import com.i2i.ibus.dto.AccountDto;
import com.i2i.ibus.model.Account;

public interface AccountService {
    Account addAccount(AccountDto accountDto);
}
