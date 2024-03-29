/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.service;

import com.i2i.ibus.model.Account;

/**
 * A service Interface contains required methods for service class of Account
 *
 * @author Ragul
 * @version 1.0
 * @since Dec 12 2022
 */
public interface AccountService {
    /**
     * Add an account to the database.
     *
     * @param account The account object that you want to add to the database.
     * @return Account
     */
    Account addAccount(Account account);
}
