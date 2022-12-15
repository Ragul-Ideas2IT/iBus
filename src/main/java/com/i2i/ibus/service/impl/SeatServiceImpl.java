/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.service.impl;

import com.i2i.ibus.constants.Constants;
import com.i2i.ibus.dto.SeatDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.Seat;
import com.i2i.ibus.repository.BusRepository;
import com.i2i.ibus.repository.SeatRepository;
import com.i2i.ibus.service.SeatService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * <h1>Bus Ticket Booking Application
 * <h1>
 * <p>
 * Used to manipulate the Bus seat details in the application. Operators are
 * manipulate the bus seat details.
 * <p>
 *
 * @author Ananth.
 * @version 1.0.
 * @since Nov 29 2022
 */
@Service
public class SeatServiceImpl implements SeatService {

    private SeatRepository seatRepository;

    private BusRepository busRepository;

    @Autowired
    private SeatServiceImpl(SeatRepository seatRepository, BusRepository busRepository) {
        this.seatRepository = seatRepository;
        this.busRepository = busRepository;
    }

    private Logger logger = LogManager.getLogger(SeatServiceImpl.class);

    /**
     * {@inheritDoc}
     */
    public SeatDto addSeat(SeatDto seatDto) {
        Seat seat = null;

        try {
            if (!seatRepository.findBySeatNumberAndBusId(seatDto.getSeatNumber(), seatDto.getBusId()).isPresent()) {
                seatDto.setBus(Mapper.toBusDto(busRepository.findById(seatDto.getBusId()).get()));
                seat = seatRepository.save(Mapper.toSeat(seatDto));
                logger.info(Constants.CREATE_MESSAGE + " seatId: " + seat.getId());
            } else {
                logger.error("SeatID " + seatDto.getSeatNumber() + Constants.ALREADY_EXIST);
                throw new IBusException(seatDto.getSeatNumber().concat(Constants.ALREADY_EXIST));
            }
        } catch (NoSuchElementException exception) {
            logger.error("BusID " + seatDto.getBusId() + Constants.NOT_EXIST);
            throw new IBusException(Constants.BUSID_NOT_EXIST);
        }
        return Mapper.toSeatDto(seat);
    }

    /**
     * {@inheritDoc}
     */
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
    public SeatDto getBySeatId(int id) {
        Seat seat = null;

        try {
            seat = seatRepository.findById(id).get();
        } catch (NoSuchElementException exception) {
            logger.error("SeatID " + id + Constants.NOT_EXIST);
            throw new IBusException(Constants.SEAT_NOT_EXIST);
        }
        return Mapper.toSeatDto(seat);
    }

    /**
     * {@inheritDoc}
     */
    public SeatDto updateSeat(SeatDto seatDto, int seatId) {
        Seat seat = null;

        try {
            if (!seatRepository.findBySeatNumberAndBusIdAndId(seatDto.getSeatNumber(), seatDto.getBusId(), seatId)
                    .isPresent()) {
                seatDto.setId(seatId);
                seatDto.setBus(Mapper.toBusDto(busRepository.findById(seatDto.getBusId()).get()));
                seat = seatRepository.save(Mapper.toSeat(seatDto));
                logger.info(Constants.CREATE_MESSAGE + " seatId: " + seat.getId());
            } else {
                logger.error("SeatID " + seatDto.getSeatNumber() + Constants.ALREADY_EXIST);
                throw new IBusException(seatDto.getSeatNumber().concat(Constants.ALREADY_EXIST));
            }
        } catch (NoSuchElementException exception) {
            logger.error("BusID " + seatDto.getBusId() + Constants.ALREADY_EXIST);
            throw new IBusException(Constants.BUSID_NOT_EXIST);
        }
        return Mapper.toSeatDto(seat);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteSeat(int seatId) {
        try {
            Seat seat = seatRepository.findById(seatId).get();
            seat.setDeleted(true);
            seatRepository.save(seat);
            logger.info(Constants.DELETE_MESSAGE + " seatId: " + seatId);
        } catch (NoSuchElementException exception) {
            logger.error(exception.getMessage());
            throw new IBusException(Constants.SEAT_NOT_EXIST);
        }
    }
}
