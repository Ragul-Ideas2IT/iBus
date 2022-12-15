/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.service.impl;

import com.i2i.ibus.dto.OperatorDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.Operator;
import com.i2i.ibus.repository.OperatorRepository;
import com.i2i.ibus.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Ragul
 * @version 1.0
 * @since Nov 29 2022
 */
@Service
public class OperatorServiceImpl implements OperatorService {

    private OperatorRepository operatorRepository;

    @Autowired
    public OperatorServiceImpl(OperatorRepository operatorRepository) {
        this.operatorRepository = operatorRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Operator validateOperator(int id) {
        Optional<Operator> operator = operatorRepository.findById(id);
        if (operator.isEmpty()) {
            throw new IBusException("Operator Id doesn't exists");
        }
        return operator.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateMailIdPhoneNoAndGstNumber(String mailId, String phoneNumber, String gstNumber) {
        if (operatorRepository.findByMailId(mailId).isPresent()) {
            throw new IBusException("Mail Id already exists");
        }
        if (operatorRepository.findByPhoneNumber(phoneNumber).isPresent()) {
            throw new IBusException("Phone Number already exists");
        }
        if (operatorRepository.findByGstNumber(gstNumber).isPresent()) {
            throw new IBusException("GST number already exists");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateMailIdPhoneNoAndGstNumberForUpdate(String mailId, String phoneNumber, String gstNumber,
                                                           int id) {
        if (operatorRepository.findByMailIdAndIdNot(mailId, id).isPresent()) {
            throw new IBusException("Mail Id already exists");
        }
        if (operatorRepository.findByPhoneNumberAndIdNot(phoneNumber, id).isPresent()) {
            throw new IBusException("Phone Number already exists");
        }
        if (operatorRepository.findByGstNumberAndIdNot(gstNumber, id).isPresent()) {
            throw new IBusException("GST number already exists");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OperatorDto saveOperator(OperatorDto operatorDto) {
        validateMailIdPhoneNoAndGstNumber(operatorDto.getMailId(), operatorDto.getPhoneNumber(),
                operatorDto.getGstNumber());
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
        validateMailIdPhoneNoAndGstNumberForUpdate(operatorDto.getMailId(), operatorDto.getPhoneNumber(),
                operatorDto.getGstNumber(), id);
        operatorDto.setId(id);
        return Mapper.toOperatorDto(operatorRepository.save(Mapper.toOperator(operatorDto)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteOperatorById(int id) {
        Operator operator = validateOperator(id);
        operator.setDeleted(true);
        operatorRepository.save(operator);
    }

}
