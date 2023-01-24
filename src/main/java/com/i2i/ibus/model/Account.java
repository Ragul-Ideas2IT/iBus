/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * JPA entity class that represents an account with getters and setters
 *
 * @author Ragul
 * @version 1.0
 * @since Dec 12 2022
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Account {
    @Id
    @NonNull
    private String mailId;
    @NonNull
    private String role;
    @NonNull
    private String password;
}
