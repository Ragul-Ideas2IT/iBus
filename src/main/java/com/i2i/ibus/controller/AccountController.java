package com.i2i.ibus.controller;

import com.i2i.ibus.dto.*;
import com.i2i.ibus.exception.*;
import com.i2i.ibus.model.*;
import com.i2i.ibus.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AccountController {
    private final AccountService accountService;
    
    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    /**
     * This function takes in an AccountDto object, converts it to an Account object, and saves it to the database
     *
     * @param accountDto This is the object that will be passed in the request body.
     * @return Account
     */
    @PostMapping("api/v1/accounts")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Account addAccount(@RequestBody AccountDto accountDto) {
        return accountService.addAccount(accountDto);
    }

    /**
     * It takes in a JSON object, validates it, and then tries to authenticate the user
     *
     * @param accountDto This is the object that will be sent to the server.
     * @return MessageDto
     */
    @PostMapping("/login")
    private MessageDto signIn(@RequestBody @Valid AccountDto accountDto) {
        System.out.println("123"+ accountDto);
        String message = null;
        HttpStatus status = HttpStatus.NOT_FOUND;
        Authentication authentication;
        try {
            System.out.println("12345");
            AuthenticationManager authenticationManager = null;
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            accountDto.getId(), accountDto.getPassword()));
            System.out.println("fjkajdfkja");
            message = "SIGNED_IN_SUCCESSFULLY";
            status = HttpStatus.OK;
        } catch (BadCredentialsException badCredentialsException) {
            throw new IBusException(badCredentialsException.getMessage());
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new MessageDto("200", message);
    }

}
