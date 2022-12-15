/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.repository;

import com.i2i.ibus.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Creating a repository for the Account model which extends JPA repository
 *
 * @author Ragul
 * @version 1.0
 * @since Dec 12 2022
 */
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
