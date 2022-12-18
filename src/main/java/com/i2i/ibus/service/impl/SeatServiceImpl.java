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
import com.i2i.ibus.dto.SeatDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.Seat;
import com.i2i.ibus.repository.SeatRepository;
import com.i2i.ibus.service.BusService;
import com.i2i.ibus.service.SeatService;

/**
 * Bus Ticket Booking Application
 * Used to manipulate the Bus seat details in the application. Operators are
 * manipulate the bus seat details.
 *
 * @author Ananth.
 * @version 1.0.
 * @since Nov 29 2022
 */
@Service
public class SeatServiceImpl implements SeatService {

    private SeatRepository seatRepository;

    private BusService busService;

    @Autowired
    private SeatServiceImpl(SeatRepository seatRepository, BusService busService) {
	this.seatRepository = seatRepository;
	this.busService = busService;
    }

    private Logger logger = LogManager.getLogger(SeatServiceImpl.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public SeatDto addSeat(SeatDto seatDto) {
	Seat seat = null;

	try {
	    if (!seatRepository.findBySeatNumberAndBusId(seatDto.getSeatNumber(), seatDto.getBusesId()).isPresent()) {
		seatDto.setBus(busService.getById(seatDto.getBusesId()));
		seat = seatRepository.save(Mapper.toSeat(seatDto));
		logger.info(Constants.CREATE_MESSAGE + Constants.SEAT_ID + seat.getId());
	    } else {
		logger.error(Constants.SEAT_NUMBER + seatDto.getSeatNumber() + Constants.ALREADY_EXIST);
		throw new IBusException(seatDto.getSeatNumber().concat(Constants.ALREADY_EXIST));
	    }
	} catch (NoSuchElementException exception) {
	    logger.error(Constants.BUS_ID + seatDto.getBusesId() + Constants.NOT_EXIST);
	    throw new IBusException(Constants.BUS_ID_NOT_EXIST);
	}
	return Mapper.toSeatDto(seat);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SeatDto> getAllByBusId(int busId) {
	List<Seat> seats = seatRepository.findAllByBusId(busId);
	List<SeatDto> seatsDto = new ArrayList<SeatDto>();

	for (Seat seat : seats) {
	    SeatDto seatDto = null;
	    seatDto = Mapper.toSeatDto(seat);
	    seatsDto.add(seatDto);
	}
	return seatsDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SeatDto getBySeatId(int id) {
	Seat seat = null;

	try {
	    seat = seatRepository.findById(id).get();
	} catch (NoSuchElementException exception) {
	    logger.error(Constants.SEAT_ID + id + Constants.NOT_EXIST);
	    throw new IBusException(Constants.SEAT_NOT_EXIST);
	}
	return Mapper.toSeatDto(seat);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SeatDto updateSeat(SeatDto seatDto, int seatId) {
	Seat seat = null;

	try {
	    if (!seatRepository.findBySeatNumberAndBusIdAndIdNot(seatDto.getSeatNumber(), seatDto.getBusesId(), seatId)
		    .isPresent()) {
		seatDto.setId(seatId);
		seatDto.setBus(busService.getById(seatDto.getBusesId()));
		seat = seatRepository.save(Mapper.toSeat(seatDto));
		logger.info(Constants.CREATE_MESSAGE + Constants.SEAT_ID + seat.getId());
	    } else {
		logger.error(Constants.SEAT_NUMBER + seatDto.getSeatNumber() + Constants.ALREADY_EXIST);
		throw new IBusException(seatDto.getSeatNumber().concat(Constants.ALREADY_EXIST));
	    }
	} catch (NoSuchElementException exception) {
	    logger.error(Constants.BUS_ID + seatDto.getBusesId() + Constants.ALREADY_EXIST);
	    throw new IBusException(Constants.BUS_ID_NOT_EXIST);
	}
	return Mapper.toSeatDto(seat);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteSeat(int seatId) {
	try {
	    Seat seat = seatRepository.findById(seatId).get();
	    seat.setDeleted(true);
	    seatRepository.save(seat);
	    logger.info(Constants.DELETE_MESSAGE + Constants.SEAT_ID + seatId);
	} catch (NoSuchElementException exception) {
	    logger.error(exception.getMessage());
	    throw new IBusException(Constants.SEAT_NOT_EXIST);
	}
    }
    
    @Override
    public Optional<Seat> findBySeatNumberAndBusId(String seatNumber, int busId) {
	return seatRepository.findBySeatNumberAndBusId(seatNumber, busId);
    }
}
