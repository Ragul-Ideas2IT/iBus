package com.i2i.ibus.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.ibus.dto.SeatDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.Seat;
import com.i2i.ibus.repository.BusRepository;
import com.i2i.ibus.repository.SeatRepository;

@Service
public class SeatService {

    private SeatRepository seatRepository;

    private BusRepository busRepository;

    @Autowired
    private SeatService(SeatRepository seatRepository, BusRepository busRepository) {
	this.seatRepository = seatRepository;
	this.busRepository = busRepository;
    }

    public SeatDto addSeat(SeatDto seatDto, int busId) throws IBusException {
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

    public SeatDto getBySeatId(int id) throws IBusException {
	Seat seat = null;

	try {
	    seat = seatRepository.findBySeatId(id).get();
	} catch (NoSuchElementException exception) {
	    throw new IBusException("Seat doesnot exixts");
	}
	return Mapper.toSeatDto(seat);
    }

    public SeatDto updateSeat(SeatDto seatDto, int seatId, int busId) throws IBusException {
	Seat seat = null;

	try {
	    if (!seatRepository.findBySeatNumberAndBusIdAndSeatId(seatDto.getSeatNumber(), busId, seatId).isPresent())
	    seatDto.setId(seatId);
	    seatDto.setBus(Mapper.toBusDto(busRepository.findById(busId).get()));
	    seat = seatRepository.save(Mapper.toSeat(seatDto));
	} catch (NoSuchElementException exception) {
	    throw new IBusException("Bus doesnot exists");
	}
	return Mapper.toSeatDto(seat);
    }

    public void deleteSeat(int seatId) {
	seatRepository.deleteById(seatId);
    }
}
