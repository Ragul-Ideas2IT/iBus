/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.repository;

import com.i2i.ibus.model.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Creating a repository for the Operator model which extends JPA repository
 *
 * @author Ragul
 * @version 1.0
 * @since Nov 29 2022
 */
@Repository
public interface OperatorRepository extends JpaRepository<Operator, Integer> {

    /**
     * Find an Operator by phone number, if one exists.
     *
     * @param phoneNumber The phone number of the operator to find.
     * @return Optional<Operator>
     */
    Optional<Operator> findByPhoneNumber(String phoneNumber);

    /**
     * Find an operator by mail id.
     *
     * @param mailId The mailId of the operator to be searched.
     * @return Optional<Operator>
     */
    Optional<Operator> findByMailId(String mailId);

    /**
     * Find an operator by its GST number.
     *
     * @param gstNumber The GST number of the operator.
     * @return Optional<Operator>
     */
    Optional<Operator> findByGstNumber(String gstNumber);

    /**
     * Find an operator by phone number and id, but not the operator with the given id.
     *
     * @param phoneNumber The phone number to search for.
     * @param id The id of the operator to exclude from the search.
     * @return Optional<Operator>
     */
    Optional<Operator> findByPhoneNumberAndIdNot(String phoneNumber, int id);

    /**
     * Find an operator by mailId and id, but not the one with the given id.
     *
     * @param mailId The mailId of the operator to find.
     * @param id The id of the operator to be excluded from the search.
     * @return Optional<Operator>
     */
    Optional<Operator> findByMailIdAndIdNot(String mailId, int id);

    /**
     * Find an operator by gstNumber and id, but not the operator with the given id.
     *
     * @param gstNumber The gstNumber to search for.
     * @param id The id of the operator
     * @return Optional<Operator>
     */
    Optional<Operator> findByGstNumberAndIdNot(String gstNumber, int id);

}
