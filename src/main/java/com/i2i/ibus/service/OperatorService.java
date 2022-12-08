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
	if (!operator.isPresent()) {
	    throw new IBusException("Operator Id doesn't exists");
	}
    }

    public void validateMailIdPhoneNoAndGstNumber(String mailId, String phoneNumber, String gstNumber) {
	Optional<Operator> operator = operatorRepository.findByMailIdPhoneNoAndGstNumber(mailId, phoneNumber,
		gstNumber);
	if (operator.isPresent()) {
	    throw new IBusException("MailId, Phone no, GST number are already exist");
	}
    }

    public void validateMailIdPhoneNoAndGstNumberForUpdate(String mailId, String phoneNumber, String gstNumber,
	    int id) {
	Optional<Operator> operator = operatorRepository.findByMailIdPhoneNoAndGstNumberForUpdate(mailId, phoneNumber,
		gstNumber, id);
	if (operator.isPresent()) {
	    throw new IBusException("MailId, Phone no, GST number are already exist");
	}
    }

    public OperatorDto saveOperator(OperatorDto operatorDto) {
	validateMailIdPhoneNoAndGstNumber(operatorDto.getMailId(), operatorDto.getPhoneNumber(),
		operatorDto.getGstNumber());
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
	validateMailIdPhoneNoAndGstNumberForUpdate(operatorDto.getMailId(), operatorDto.getPhoneNumber(),
		operatorDto.getGstNumber(), id);
	operatorDto.setId(id);
	return Mapper.toOperatorDto(operatorRepository.save(Mapper.toOperator(operatorDto)));
    }

    public void deleteOperatorById(int id) {
	validateOperator(id);
	operatorRepository.deleteById(id);
    }

}
