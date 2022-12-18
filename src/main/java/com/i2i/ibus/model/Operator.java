/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * JPA entity class that represents an operator with getters and setters
 *
 * @author Ragul
 * @version 1.0
 * @since Nov 28 2022
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Where(clause = "is_deleted = false")
public class Operator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String travelsName;
    private String ownerName;
    private String phoneNumber;
    private String mailId;
    private String panNumber;
    private String gstNumber;
    private boolean isDeleted;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "operator_id")
    private List<Address> addresses;
}
