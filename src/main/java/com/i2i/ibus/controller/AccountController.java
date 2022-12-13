package com.i2i.ibus.controller;

import com.i2i.ibus.dto.AccountDto;
import com.i2i.ibus.dto.MessageDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.model.Account;
import com.i2i.ibus.service.AccountService;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/accounts")
public class AccountController {

    private final AccountService accountService;

//    private final AuthenticationManager authenticationManager;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    private Account addAccount(@RequestBody @Valid AccountDto accountDto) {
        return accountService.addAccount(accountDto);
    }

//    @PostMapping("/login")
//    private MessageDto signIn(@RequestBody @Valid AccountDto accountDto) {
//        System.out.println("123"+ accountDto);
//        String message = null;
//        HttpStatus status = HttpStatus.NOT_FOUND;
//        Authentication authentication;
//        try {
//            System.out.println("12345");
//            authentication = authenticationManager
//                    .authenticate(new UsernamePasswordAuthenticationToken(
//                            accountDto.getId(), accountDto.getPassword()));
//            System.out.println("fjkajdfkja");
//            message = "SIGNED_IN_SUCCESSFULLY";
//            status = HttpStatus.OK;
//        } catch (BadCredentialsException badCredentialsException) {
//            throw new IBusException(badCredentialsException.getMessage());
//        }
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return new MessageDto("200", message);
//    }

}
