package com.i2i.ibus.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.ibus.dto.PassengerDto;
import com.i2i.ibus.model.Passenger;
import com.i2i.ibus.repository.PassengerRepository;

/**
 * 
 * @author Tamilmani
 *
 */
@Service
public class PassengerService {

	private PassengerRepository passengerRepository;
    private ModelMapper modelMapper;

	@Autowired
	private PassengerService(PassengerRepository passengerRepository) {
		this.passengerRepository = passengerRepository;
		//this.modelMapper = modelMapper;
	}

	public PassengerDto createPassenger(PassengerDto passengerDto) {
		Passenger passenger = modelMapper.map(passengerDto, Passenger.class);
		return modelMapper.map(passengerRepository.save(passenger),
				PassengerDto.class);
	}

}