package com.i2i.ibus.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.ibus.dto.OperatorDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.Operator;
import com.i2i.ibus.repository.OperatorRepository;

/**
 * @author Ragul
 * @version 1.0
 * 
 * @created Nov 29 2022
 *
 */
@Service
public class OperatorService {

    private OperatorRepository operatorRepository;

    @Autowired
    public OperatorService(OperatorRepository operatorRepository) {
	this.operatorRepository = operatorRepository;
    }

    public void validateOperator(int id) {
	Optional<Operator> operator = operatorRepository.findById(id);
	if (operator.isPresent() && operator.get().isDeleted()) {
	    throw new IBusException("Operator id doesn't exists because Operator details deleted");
	} else if (!operator.isPresent()) {
	    throw new IBusException("Operator Id doesn't exists");
	}
    }

    public void validatePhoneNo(String phoneNumber) {
	Optional<Operator> operator = operatorRepository.findByPhoneNumber(phoneNumber);
	if (operator.isPresent()) {
	    throw new IBusException(phoneNumber.concat(" Already exists"));
	}
    }

    public void validateGstNumber(String gstNumber) {
	Optional<Operator> operator = operatorRepository.findByGstNumber(gstNumber);
	if (operator.isPresent()) {
	    throw new IBusException(gstNumber.concat(" Already exists"));
	}
    }

    public void validateMailId(String mailId) {
	Optional<Operator> operator = operatorRepository.findByMailId(mailId);
	if (operator.isPresent()) {
	    throw new IBusException(mailId.concat(" Already exists"));
	}
    }

    public OperatorDto saveOperator(OperatorDto operatorDto) {
	validateGstNumber(operatorDto.getGstNumber());
	validateMailId(operatorDto.getMailId());
	validatePhoneNo(operatorDto.getPhoneNumber());
	return Mapper.toOperatorDto(operatorRepository.save(Mapper.toOperator(operatorDto)));
    }

    public List<OperatorDto> getAllOperatorDtos() {
	return Mapper.toOperatorDtos(operatorRepository.findAll());
    }

    public OperatorDto getOperatorDtoById(int id) {
	validateOperator(id);
	return Mapper.toOperatorDto(operatorRepository.findById(id).get());
    }

    public OperatorDto updateOperatorById(int id, OperatorDto operatorDto) {
	validateOperator(id);
	operatorDto.setId(id);
	return saveOperator(operatorDto);
    }

    public void deleteOperatorById(int id) {
	validateOperator(id);
	operatorRepository.deleteById(id);
    }

}
