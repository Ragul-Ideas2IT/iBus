package com.i2i.ibus.service.impl;

import com.i2i.ibus.dto.BusDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.Bus;
import com.i2i.ibus.repository.BusRepository;
import com.i2i.ibus.repository.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * 
 * @author Ananth.
 * @version 1.0.
 * 
 * @created nov 30 2022
 *
 */
@Service
public class BusService {

    private BusRepository busRepository;

    private OperatorRepository operatorRepository;

    @Autowired
    public BusService(BusRepository busRepository, OperatorRepository operatorRepository) {
	this.busRepository = busRepository;
	this.operatorRepository = operatorRepository;
    }

    /**
     * 
     * @param busDto
     * @return
     * @throws IBusException
     */
    public BusDto addBus(BusDto busDto, int operatorId) {
	BusDto busDTO = null;
	try {
	    busDto.setOperator(Mapper.toOperatorDto(operatorRepository.findById(operatorId).get()));

	    Bus bus = Mapper.toBus(busDto);

	    if (!busRepository.findByBusNumber(busDto.getBusNumber()).isPresent()) {
		busDTO = Mapper.toBusDto(busRepository.save(bus));
	    } else {
		throw new IBusException("Bus Number is Duplicate");
	    }
	} catch (NoSuchElementException Exception) {
	    throw new IBusException("Operator doesnot exist");
	}
	return busDTO;
    }

    /**
     * 
     * @return
     */
    public List<BusDto> getAllBuses() {
	List<Bus> buses = busRepository.findAll();
	List<BusDto> busesDto = new ArrayList<BusDto>();

	for (Bus bus : buses) {
	    BusDto busDto = null;
	    busDto = Mapper.toBusDto(bus);
	    busesDto.add(busDto);
	}
	return busesDto;
    }

    /**
     * 
     * @return
     */
    public List<BusDto> getAllByOperatorId(int OperatorId) {
	List<Bus> buses = busRepository.findByOperatorId(OperatorId);
	List<BusDto> busesDto = new ArrayList<BusDto>();

	for (Bus bus : buses) {
	    BusDto busDto = null;
	    busDto = Mapper.toBusDto(bus);
	    busesDto.add(busDto);
	}
	return busesDto;
    }

    /**
     * 
     * @return
     * @throws IBusException
     */
    public BusDto getById(int id) {
	Bus bus = null;
	try {
	    bus = busRepository.findById(id).get();
	} catch (NoSuchElementException exception) {
	    throw new IBusException("Bus doesnot exists");
	}
	return Mapper.toBusDto(bus);
    }

    /**
     * 
     * @param busDto
     * @return
     * @throws IBusException
     */
    public BusDto updateBus(BusDto busDto, int busId, int operatorId) {

	try {
	    busDto.setId(busId);
	    busDto.setOperator(Mapper.toOperatorDto(operatorRepository.findById(operatorId).get()));

	    Bus bus = Mapper.toBus(busDto);

	    if (!busRepository.findByBusNumberForUpdate(busDto.getBusNumber(), busDto.getId()).isPresent()) {
		busDto = Mapper.toBusDto(busRepository.save(bus));
	    } else {
		throw new IBusException("Bus Number is Duplicate");
	    }
	} catch (NoSuchElementException Exception) {
	    throw new IBusException("Operator doesnot exist");
	}
	return busDto;
    }

    /**
     * 
     * @param busId
     */
    public void deleteBus(int busId) {
	busRepository.deleteById(busId);
    }
}