package com.i2i.ibus.service.impl;

import com.i2i.ibus.dto.OperatorDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.Operator;
import com.i2i.ibus.service.OperatorService;
import com.i2i.ibus.repository.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Ragul
 * @version 1.0
 * @created Nov 29 2022
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
    public void validateOperator(int id) {
        Optional<Operator> operator = operatorRepository.findById(id);
        if (!operator.isPresent()) {
            throw new IBusException("Operator Id doesn't exists");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateMailIdPhoneNoAndGstNumber(String mailId, String phoneNumber, String gstNumber) {
        Optional<Operator> operator = operatorRepository.findByMailIdAndPhoneNumberAndGstNumber(mailId, phoneNumber,
                gstNumber);
        if (operator.isPresent()) {
            throw new IBusException("MailId, Phone no, GST number are already exist");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateMailIdPhoneNoAndGstNumberForUpdate(String mailId, String phoneNumber, String gstNumber,
                                                           int id) {
        Optional<Operator> operator = operatorRepository.findByMailIdPhoneNoAndGstNumberForUpdate(mailId, phoneNumber,
                gstNumber, id);
        if (operator.isPresent()) {
            throw new IBusException("MailId, Phone no, GST number are already exist");
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
    public List<OperatorDto> getAllOperatorDtos() {
        return Mapper.toOperatorDtos(operatorRepository.findAll());
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
        validateOperator(id);
        operatorRepository.deleteById(id);
    }

}
