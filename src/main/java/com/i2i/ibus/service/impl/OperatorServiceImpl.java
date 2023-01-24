/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.service.impl;

import com.i2i.ibus.constants.Constants;
import com.i2i.ibus.dto.OperatorDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.Account;
import com.i2i.ibus.model.Operator;
import com.i2i.ibus.repository.OperatorRepository;
import com.i2i.ibus.service.AccountService;
import com.i2i.ibus.service.OperatorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * A service class that implements the OperatorService interface and uses the OperatorRepository to save an Operator
 * object. It provides the implementation for all the methods. It contains all the business logic related to the
 * Operator
 *
 * @author Ragul
 * @version 1.0
 * @since Nov 29 2022
 */
@Service
public class OperatorServiceImpl implements OperatorService {

    private OperatorRepository operatorRepository;
    private AccountService accountService;

    @Autowired
    public OperatorServiceImpl(OperatorRepository operatorRepository, AccountService accountService) {
        this.operatorRepository = operatorRepository;
        this.accountService = accountService;
    }
    private Logger logger = LogManager.getLogger(OperatorServiceImpl.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public Operator validateOperator(int id) {
        Optional<Operator> operator = operatorRepository.findById(id);
        if (operator.isEmpty()) {
            logger.error(id + Constants.OPERATOR_ID_NOT_EXIST);
            throw new IBusException(Constants.OPERATOR_ID_NOT_EXIST);
        }
        return operator.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateMailId(String mailId) {
        Optional<Operator> operator = operatorRepository.findByMailId(mailId);
        if (operator.isPresent()) {
            logger.error(Constants.MAIL_ID_ALREADY_EXISTS);
            throw new IBusException(Constants.MAIL_ID_ALREADY_EXISTS);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validatePhoneNumber(String phoneNumber) {
        Optional<Operator> operator = operatorRepository.findByPhoneNumber(phoneNumber);
        if (operator.isPresent()) {
            logger.error(Constants.PHONE_NUMBER_ALREADY_EXISTS);
            throw new IBusException(Constants.PHONE_NUMBER_ALREADY_EXISTS);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateGstNumber(String gstNumber) {
        Optional<Operator> operator = operatorRepository.findByGstNumber(gstNumber);
        if (operator.isPresent()) {
            logger.error(Constants.GST_NUMBER_ALREADY_EXISTS);
            throw new IBusException(Constants.GST_NUMBER_ALREADY_EXISTS);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateMailIdForUpdate(String mailId, int id) {
        Optional<Operator> operator = operatorRepository.findByMailIdAndIdNot(mailId, id);
        if (operator.isPresent()) {
            logger.error(Constants.MAIL_ID_ALREADY_EXISTS);
            throw new IBusException(Constants.MAIL_ID_ALREADY_EXISTS);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validatePhoneNumberForUpdate(String phoneNumber, int id) {
        Optional<Operator> operator = operatorRepository.findByPhoneNumberAndIdNot(phoneNumber, id);
        if (operator.isPresent()) {
            logger.error(Constants.PHONE_NUMBER_ALREADY_EXISTS);
            throw new IBusException(Constants.PHONE_NUMBER_ALREADY_EXISTS);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateGstNumberForUpdate(String gstNumber, int id) {
        Optional<Operator> operator = operatorRepository.findByGstNumberAndIdNot(gstNumber, id);
        if (operator.isPresent()) {
            logger.error(Constants.GST_NUMBER_ALREADY_EXISTS);
            throw new IBusException(Constants.GST_NUMBER_ALREADY_EXISTS);
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public OperatorDto saveOperator(OperatorDto operatorDto) {
        validateMailId(operatorDto.getMailId());
        validatePhoneNumber(operatorDto.getPhoneNumber());
        validateGstNumber(operatorDto.getGstNumber());
        accountService.addAccount(new Account(operatorDto.getMailId(), Constants.ROLE_OPERATOR, operatorDto.getPassword()));
        logger.info(Constants.CREATE_MESSAGE + operatorDto.getId());
        return Mapper.toOperatorDto(operatorRepository.save(Mapper.toOperator(operatorDto)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OperatorDto> getAllOperatorDTOs() {
        return Mapper.toOperatorDTOs(operatorRepository.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OperatorDto getOperatorDtoById(int id) {
        validateOperator(id);
        return Mapper.toOperatorDto(operatorRepository.findById(id).get());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OperatorDto updateOperatorById(int id, OperatorDto operatorDto) {
        validateOperator(id);
        validateMailIdForUpdate(operatorDto.getMailId(), id);
        validatePhoneNumberForUpdate(operatorDto.getPhoneNumber(), id);
        validateGstNumberForUpdate(operatorDto.getGstNumber(), id);
        operatorDto.setId(id);
        logger.info(Constants.UPDATE_MESSAGE + operatorDto.getId());
        return Mapper.toOperatorDto(operatorRepository.save(Mapper.toOperator(operatorDto)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteOperatorById(int id) {
        Operator operator = validateOperator(id);
        operator.setDeleted(true);
        logger.info(Constants.DELETE_MESSAGE + id);
        operatorRepository.save(operator);
    }

}
