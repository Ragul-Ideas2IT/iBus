package com.i2i.ibus.controller;

import com.i2i.ibus.dto.AccountDto;
import com.i2i.ibus.dto.MessageDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.model.Account;
import com.i2i.ibus.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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

//    /**
//     * It takes in a JSON object, validates it, and then tries to authenticate the user
//     *
//     * @param accountDto This is the object that will be sent to the server.
//     * @return MessageDto
//     */
//    @PostMapping("/login")
//    private MessageDto signIn(@RequestBody @Valid AccountDto accountDto) {
//        String message = null;
//        HttpStatus status = HttpStatus.NOT_FOUND;
//        Authentication authentication;
//        try {
//            message = "SIGNED_IN_SUCCESSFULLY";
//            status = HttpStatus.OK;
//            System.out.println("12345");
//            authentication = authenticationManager
//                    .authenticate(new UsernamePasswordAuthenticationToken(
//                            accountDto.getMailId(), accountDto.getPassword()));
//        } catch (BadCredentialsException badCredentialsException) {
//            badCredentialsException.printStackTrace();
//            throw new IBusException(badCredentialsException.getMessage());
//        }
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return new MessageDto("200", message);
//    }

}
