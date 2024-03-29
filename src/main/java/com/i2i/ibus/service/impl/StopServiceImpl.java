/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.ibus.constants.Constants;
import com.i2i.ibus.dto.StopDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.Stop;
import com.i2i.ibus.repository.StopRepository;
import com.i2i.ibus.service.BusService;
import com.i2i.ibus.service.StopService;

/**
 * Bus Ticket Booking Application
 * Used to manipulate the Bus stop details in the application. Operators are
 * manipulate the bus stop details.
 *
 * @author Ananth.
 * @version 1.0.
 * @since Nov 29 2022
 */
@Service
public class StopServiceImpl implements StopService {

    private StopRepository stopRepository;

    private BusService busService;

    @Autowired
    private StopServiceImpl(StopRepository stopRepository, BusService busService) {
	this.stopRepository = stopRepository;
	this.busService = busService;
    }

    private Logger logger = LogManager.getLogger(StopServiceImpl.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public StopDto addStop(StopDto stopDto) {
	Stop stop = null;

	try {
	    if (stopRepository.findByBusIdAndCityAndLandMarkAndStopName(stopDto.getBusesId(), stopDto.getCity(),
		    stopDto.getLandMark(), stopDto.getStopName()).isEmpty()) {
		stopDto.setBus(busService.getById(stopDto.getBusesId()));
		stop = stopRepository.save(Mapper.toStop(stopDto));
		logger.info(Constants.CREATE_MESSAGE + Constants.STOP_ID + stop.getId());
	    } else {
		logger.error(Constants.SAME_SOURCE_AND_DESTINATION);
		throw new IBusException(Constants.SAME_SOURCE_AND_DESTINATION);
	    }
	} catch (NoSuchElementException exception) {
	    logger.error(Constants.BUS_ID + stopDto.getBusesId() + Constants.NOT_EXIST);
	    throw new IBusException(Constants.BUS_ID_NOT_EXIST);
	}
	return Mapper.toStopDto(stop);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<StopDto> getStopsByBusId(int busId) {
	List<Stop> stops = stopRepository.findAllByBusId(busId);
	List<StopDto> stopsDto = new ArrayList<StopDto>();

	for (Stop stop : stops) {
	    StopDto stopDto = null;
	    stopDto = Mapper.toStopDto(stop);
	    stopsDto.add(stopDto);
	}
	return stopsDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StopDto updateStop(StopDto stopDto, int stopId) {
	Stop stop = null;

	try {
	    if (stopRepository.findByBusIdAndCityAndLandMarkAndStopNameAndIdNot(stopDto.getBusesId(), stopDto.getCity(),
		    stopDto.getLandMark(), stopDto.getStopName(), stopId).isEmpty()) {
		stopDto.setId(stopId);
		stopDto.setBus(busService.getById(stopDto.getBusesId()));
		stop = stopRepository.save(Mapper.toStop(stopDto));
		logger.info(Constants.CREATE_MESSAGE + Constants.STOP_ID + stop.getId());
	    } else {
		logger.error(Constants.SAME_SOURCE_AND_DESTINATION);
		throw new IBusException(Constants.SAME_SOURCE_AND_DESTINATION);
	    }
	} catch (NoSuchElementException exception) {
	    logger.error(Constants.BUS_ID + stopDto.getBusesId() + Constants.NOT_EXIST);
	    throw new IBusException(Constants.BUS_ID_NOT_EXIST);
	}
	return Mapper.toStopDto(stop);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteStop(int stopId) {
	try {
	    Stop stop = stopRepository.findById(stopId).get();
	    stop.setDeleted(true);
	    stopRepository.save(stop);
	    logger.info(Constants.DELETE_MESSAGE + Constants.STOP_ID + stopId);
	} catch (NoSuchElementException exception) {
	    logger.error(exception.getMessage());
	    throw new IBusException(Constants.STOP_NOT_EXIST);
	}
    }

    /**
     * Used to get the stop details by the bus id and city and stopName. The stop
     * were given to the booking repository for validation process.
     * 
     * @param busId    from the booking user
     * @param city     from the booking user
     * @param stopName from the booking user
     * @return returns the stop or else nothing.
     */
    @Override
    public Optional<Stop> findAllByBusIdAndCityAndStopName(int busId, String city, String stopName) {
	return stopRepository.findAllByBusIdAndCityAndStopName(busId, city, stopName);
    }

}
