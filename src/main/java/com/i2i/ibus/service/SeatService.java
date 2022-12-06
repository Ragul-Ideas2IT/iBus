package com.i2i.ibus.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.i2i.ibus.dto.SeatDto;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.Seat;
import com.i2i.ibus.repository.SeatRepository;

public class SeatService {

    private SeatRepository seatRepository;

    @Autowired
    private SeatService(SeatRepository seatRepository) {
	this.seatRepository = seatRepository;
    }

    public SeatDto addSeat(SeatDto seatDto, int busId) {
	Seat seat = seatRepository.save(Mapper.toSeat(seatDto));
	return Mapper.toSeatDto(seat);
    }

    public List<SeatDto> getAllSeats(int busId) {
	List<Seat> seats = seatRepository.findAll();
	List<SeatDto> seatsDto = new ArrayList<SeatDto>();

	for (Seat seat : seats) {
	    SeatDto seatDto = null;
	    seatDto = Mapper.toSeatDto(seat);
	    seatsDto.add(seatDto);
	}
	return seatsDto;
    }
     
    public SeatDto updateSeat(SeatDto seatDto, int busId) {
	Seat seat = seatRepository.save(Mapper.toSeat(seatDto));
	return Mapper.toSeatDto(seat);
    }
    
    public void deleteSeat(int seatId) {
	seatRepository.deleteById(seatId);
    }
}
