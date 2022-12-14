package com.i2i.ibus.repository;

import com.i2i.ibus.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    /**
     * Find an account by mailId.
     *
     * @param mailId The mailId of the user whose account is to be found.
     * @return Account object
     */
    Account findByMailId(String mailId);
}
