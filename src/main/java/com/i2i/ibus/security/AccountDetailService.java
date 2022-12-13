package com.i2i.ibus.security;

import com.i2i.ibus.model.Account;
import com.i2i.ibus.repository.AccountRepository;
import com.i2i.ibus.model.User;
import com.i2i.ibus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

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
        Optional<Account> accountOptional = accountRepository.findByMailId(mailId);
        if (accountOptional.isEmpty()) {
            throw new UsernameNotFoundException(mailId.concat(" not found"));
        }
        return new AccountDetails(accountOptional.get());
    }
}
