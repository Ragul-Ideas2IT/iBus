/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.ibus.constants.Constants;
import com.i2i.ibus.dto.BusDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.Bus;
import com.i2i.ibus.repository.BusRepository;
import com.i2i.ibus.service.BusService;
import com.i2i.ibus.service.OperatorService;

/**
 * Bus Ticket Booking Application
 * Used to manipulate the Bus details in the application. Operators are
 * manipulate the bus details.
 *
 * @author Ananth.
 * @version 1.0.
 * @since Nov 29 2022
 */
@Service
public class BusServiceImpl implements BusService {

    private BusRepository busRepository;

    private OperatorService operatorService;

    @Autowired
    public BusServiceImpl(BusRepository busRepository, OperatorService operatorService) {
	this.busRepository = busRepository;
	this.operatorService = operatorService;
    }

    private Logger logger = LogManager.getLogger(BusServiceImpl.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public BusDto addBus(BusDto busDto) {
	BusDto busDTO = null;

	try {
	    busDto.setOperator(operatorService.getOperatorDtoById (busDto.getOperatorsId()));
	    Bus bus = Mapper.toBus(busDto);

	    if (!busRepository.findByBusNumber(busDto.getBusNumber()).isPresent()) {
		busDTO = Mapper.toBusDto(busRepository.save(bus));
		logger.info(Constants.CREATE_MESSAGE + Constants.BUS_ID + busDto.getId());
	    } else {
		logger.error(Constants.BUS_NUMBER + busDto.getBusNumber() + Constants.ALREADY_EXIST);
		throw new IBusException(Constants.DUPLICATE_BUS_NUMBER_FOUND);
	    }
	} catch (NoSuchElementException exception) {
	    logger.error(exception.getMessage());
	    throw new IBusException(Constants.OPERATOR_ID_NOT_EXIST);
	}
	return busDTO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
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
     * {@inheritDoc}
     */
    @Override
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
     * {@inheritDoc}
     */
    @Override
    public BusDto getById(int id) {
	Bus bus = null;
	try {
	    bus = busRepository.findById(id).get();
	} catch (NoSuchElementException exception) {
	    logger.error(Constants.BUS_ID + id + Constants.NOT_EXIST);
	    throw new IBusException(Constants.BUS_ID_NOT_EXIST);
	}
	return Mapper.toBusDto(bus);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BusDto updateBus(BusDto busDto, int busId) {

	try {
	    busDto.setId(busId);
	    busDto.setOperator(operatorService.getOperatorDtoById (busDto.getOperatorsId()));
	    Bus bus = Mapper.toBus(busDto);

	    if (!busRepository.findByBusNumberAndIdNot(busDto.getBusNumber(), busDto.getId()).isPresent()) {
		busDto = Mapper.toBusDto(busRepository.save(bus));
		logger.info(Constants.CREATE_MESSAGE + Constants.BUS_ID + busDto.getId());
	    } else {
		logger.error(Constants.BUS_NUMBER + busDto.getBusNumber() + Constants.ALREADY_EXIST);
		throw new IBusException(Constants.DUPLICATE_BUS_NUMBER_FOUND);
	    }
	} catch (NoSuchElementException exception) {
	    logger.error(exception.getMessage());
	    throw new IBusException(Constants.OPERATOR_ID_NOT_EXIST);
	}
	return busDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteBus(int busId) {
	try {
	    Bus bus = busRepository.findById(busId).get();
	    bus.setDeleted(true);
	    busRepository.save(bus);
	    logger.info(Constants.DELETE_MESSAGE + Constants.BUS_ID + busId);
	} catch (NoSuchElementException exception) {
	    logger.error(exception.getMessage());
	    throw new IBusException(Constants.BUS_ID_NOT_EXIST);
	}
    }
}