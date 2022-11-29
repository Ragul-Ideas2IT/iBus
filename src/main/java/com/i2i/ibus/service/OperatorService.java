package com.i2i.ibus.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.i2i.ibus.dto.AddressDto;
import com.i2i.ibus.dto.OperatorDto;
import com.i2i.ibus.exception.AlreadyExistException;
import com.i2i.ibus.model.Address;
import com.i2i.ibus.model.Operator;
import com.i2i.ibus.repository.OperatorRepository;

import lombok.RequiredArgsConstructor;

/**
 * @author Ragul
 *
 */
@Service
@RequiredArgsConstructor
public class OperatorService {

    private final OperatorRepository operatorRepository;

    private final ModelMapper mapper;

    public void validatePhoneNo(String phoneNumber)
	    throws AlreadyExistException {
	Operator operator = operatorRepository.findByPhoneNumber(phoneNumber)
		.get();
	if (!operator.isDeleted() && (null != operator)) {
	    throw new AlreadyExistException(
		    phoneNumber.concat(" Already exists"));
	}
    }

    public void validateMailId(String mailId) throws AlreadyExistException {
	Operator operator = operatorRepository.findByMailId(mailId).get();
	if (!operator.isDeleted() && (null != operator)) {
	    throw new AlreadyExistException(mailId.concat(" Already exists"));
	}
    }

    public OperatorDto saveOperator(OperatorDto operatorDto) {
	return toOperatorDto(operatorRepository.save(toOperator(operatorDto)));
    }

    public List<OperatorDto> getAllOperatorDtos() {
	return operatorRepository.findAll().stream()
		.map(operator -> toOperatorDto(operator))
		.collect(Collectors.toList());
    }

    public OperatorDto getOperatorDtoById(int id) {
	return toOperatorDto(operatorRepository.findById(id).get());
    }

    public OperatorDto updateOperatorById(OperatorDto operatorDto) {
	return saveOperator(operatorDto);
    }

    public void deleteOperatorById(int id) {
	operatorRepository.deleteById(id);
    }

    public Operator toOperator(OperatorDto operatorDto) {
	Operator operator = mapper.map(operatorDto, Operator.class);
	operator.setAddresses(operatorDto.getAddresses().stream()
		.map(address -> mapper.map(address, Address.class))
		.collect(Collectors.toList()));
	return operator;
    }

    public OperatorDto toOperatorDto(Operator operator) {
	OperatorDto operatorDto = mapper.map(operator, OperatorDto.class);
	operatorDto.setAddresses(operator.getAddresses().stream()
		.map(address -> mapper.map(address, AddressDto.class))
		.collect(Collectors.toList()));
	return operatorDto;
    }
}
