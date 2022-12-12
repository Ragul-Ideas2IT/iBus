package com.i2i.ibus.service.impl;

import com.i2i.ibus.dto.SeatDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.Seat;
import com.i2i.ibus.repository.BusRepository;
import com.i2i.ibus.repository.SeatRepository;
import com.i2i.ibus.service.SeatService;

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
 * @created Nov 29 2022
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

    /**
     * {@inheritDoc}
     */
    public SeatDto addSeat(SeatDto seatDto, int busId) {
	Seat seat = null;

	try {
	    if (!seatRepository.findBySeatNumberAndBusId(seatDto.getSeatNumber(), busId).isPresent()) {
		seatDto.setBus(Mapper.toBusDto(busRepository.findById(busId).get()));
		seat = seatRepository.save(Mapper.toSeat(seatDto));
	    } else {
		throw new IBusException(seatDto.getSeatNumber().concat(" already exists"));
	    }
	} catch (NoSuchElementException exception) {
	    throw new IBusException("Bus doesnot exists");
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
	    throw new IBusException("Seat doesnot exixts");
	}
	return Mapper.toSeatDto(seat);
    }

    /**
     * {@inheritDoc}
     */
    public SeatDto updateSeat(SeatDto seatDto, int seatId, int busId) {
	Seat seat = null;

	try {
	    if (!seatRepository.findBySeatNumberAndBusIdAndId(seatDto.getSeatNumber(), busId, seatId).isPresent()) {
		seatDto.setId(seatId);
		seatDto.setBus(Mapper.toBusDto(busRepository.findById(busId).get()));
		seat = seatRepository.save(Mapper.toSeat(seatDto));
	    } else {
		throw new IBusException(seatDto.getSeatNumber().concat(" already exists"));
	    }
	} catch (NoSuchElementException exception) {
	    throw new IBusException("Bus doesnot exists");
	}
	return Mapper.toSeatDto(seat);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteSeat(int seatId) {
	seatRepository.deleteById(seatId);
    }
}
