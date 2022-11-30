package com.i2i.ibus.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.ibus.dto.PassengerDto;
import com.i2i.ibus.repository.BookingDetailRepository;

/**
 * @author Tamilmani
 * @version 1.0
 * 
 * @created Nov 30 2022
 *
 */
@Service
public class BookingDetailService {

    private BookingDetailRepository bookingDetailRepository;
    private ModelMapper modelMapper;

    @Autowired
    private BookingDetailService(BookingDetailRepository
	    bookingDetailRepository, ModelMapper modelMapper) {
	this.bookingDetailRepository = bookingDetailRepository;
	this.modelMapper = modelMapper;
    }

    public List<PassengerDto> createPassenger(int bookingId,
	    List<PassengerDto> passengerDtos) {
	return null;
    }

    public List<PassengerDto> findAllPassengers() {
	return null;
    }

    public void deleteAllPassenger() {
    }

}