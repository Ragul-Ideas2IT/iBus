/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.service;

import com.i2i.ibus.dto.OperatorDto;
import com.i2i.ibus.model.Operator;

import java.util.List;

/**
 * A service Interface contains all the business logic related to the Operator
 *
 * @author Ragul
 * @version 1.0
 * @since Nov 29 2022
 */
public interface OperatorService {


    /**
     * Validates the operator id and throws an exception if the operator id doesn't exist
     *
     * @param id The id of the operator to be validated.
     * @return Operator
     */
    Operator validateOperator(int id);

    /**
     * Validates the mail id
     *
     * @param mailId The mail id of the user
     */
    void validateMailId(String mailId);

    /**
     * Validates the Phone Number
     *
     * @param phoneNumber The phone number of the user.
     */
    void validatePhoneNumber(String phoneNumber);

    /**
     * Validates the Gst Number
     *
     * @param gstNumber   GST number of the user
     */
    void validateGstNumber(String gstNumber);

    /**
     * Validates the mailId for update.
     *
     * @param mailId      The mail id of the customer.
     * @param id          The id of the customer.
     */
    void validateMailIdForUpdate(String mailId, int id);

    /**
     * Validates the phoneNumber for update.
     *
     * @param phoneNumber The phone number of the customer.
     * @param id          The id of the customer.
     */
    void validatePhoneNumberForUpdate(String phoneNumber, int id);

    /**
     * Validates the gstNumber for update.
     *
     * @param gstNumber   GST number of the customer
     * @param id          The id of the customer.
     */
    void validateGstNumberForUpdate(String gstNumber, int id);

    /**
     * Save an operator.
     *
     * @param operatorDto The operator object to be saved.
     * @return The operatorDto object that was saved.
     */
    OperatorDto saveOperator(OperatorDto operatorDto);

    /**
     * Get all operator DTOs.
     *
     * @return A list of OperatorDTOs
     */
    List<OperatorDto> getAllOperatorDTOs();

    /**
     * Get an operator by id.
     *
     * @param id The id of the operator you want to get.
     * @return OperatorDto
     */
    OperatorDto getOperatorDtoById(int id);

    /**
     * Update an operator by id.
     *
     * @param id          The id of the operator you want to update.
     * @param operatorDto The operator object that you want to update.
     * @return OperatorDto
     */
    OperatorDto updateOperatorById(int id, OperatorDto operatorDto);

    /**
     * Deletes the operator with the given id.
     *
     * @param id The id of the operator to delete.
     */
    void deleteOperatorById(int id);

}
