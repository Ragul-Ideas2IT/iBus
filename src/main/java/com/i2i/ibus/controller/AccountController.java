package com.i2i.ibus.controller;

import com.i2i.ibus.dto.AccountDto;
import com.i2i.ibus.dto.UserDto;
import com.i2i.ibus.model.Account;
import com.i2i.ibus.service.AccountService;
import com.i2i.ibus.service.UserService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class AccountController {
    private AccountService accountService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    private Account addAccount(@RequestBody @Valid AccountDto accountDto) {
        return accountService.addAccount(accountDto);
    }
}
