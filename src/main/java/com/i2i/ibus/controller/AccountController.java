/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.i2i.ibus.controller;

import com.i2i.ibus.dto.AccountDto;
import com.i2i.ibus.dto.MessageDto;
import com.i2i.ibus.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Ragul V
 * @version 1.0
 * @since Dec 12 2022
 */
@RestController
public class AccountController {
    private final AccountService accountService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AccountController(AccountService accountService, AuthenticationManager authenticationManager) {
        this.accountService = accountService;
        this.authenticationManager = authenticationManager;
    }


    /**
     * This function takes in an AccountDto object, converts it to an Account object, and saves it to the database
     *
     * @param accountDto This is the object that will be passed in the request body.
     * @return Account
     */
    @PostMapping("api/v1/accounts")
    @ResponseStatus(value = HttpStatus.CREATED)
    public MessageDto addAccount(@RequestBody @Valid AccountDto accountDto) {
        accountService.addAccount(accountDto);
        return new MessageDto("201", "Account Created Successfully");
    }
}
