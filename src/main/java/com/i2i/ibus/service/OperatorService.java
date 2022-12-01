package com.i2i.ibus.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.i2i.ibus.dto.AddressDto;
import com.i2i.ibus.dto.OperatorDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.model.Address;
import com.i2i.ibus.model.Operator;
import com.i2i.ibus.repository.OperatorRepository;

import lombok.RequiredArgsConstructor;

/**
 * @author Ragul
 * @version 1.0
 * 
 * @created Nov 29 2022
 *
 */
@Service
@RequiredArgsConstructor
public class OperatorService {

    private final OperatorRepository operatorRepository;

    private final ModelMapper mapper;

    public void validatePhoneNo(String phoneNumber) throws IBusException {
	Operator operator = operatorRepository.findByPhoneNumber(phoneNumber).get();
	if (!operator.isDeleted() && (null != operator)) {
	    throw new IBusException(phoneNumber.concat(" Already exists"));
	}
    }

    public void validateMailId(String mailId) throws IBusException {
	Optional<Operator> operator = operatorRepository.findByMailId(mailId);
	if (operator.isPresent()) {
	    throw new IBusException(mailId.concat(" Already exists"));
	}
    }

    public OperatorDto saveOperator(OperatorDto operatorDto) {
	return toOperatorDto(operatorRepository.save(toOperator(operatorDto)));
    }

    public List<OperatorDto> getAllOperatorDtos() {
	return operatorRepository.findAll().stream().map(operator -> toOperatorDto(operator))
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
		.map(addressDto -> mapper.map(addressDto, Address.class)).toList());
	return operator;
    }

    public OperatorDto toOperatorDto(Operator operator) {
	OperatorDto operatorDto = mapper.map(operator, OperatorDto.class);
	operatorDto.setAddresses(
		operator.getAddresses().stream().map(address -> mapper.map(address, AddressDto.class)).toList());
	return operatorDto;
    }
}
