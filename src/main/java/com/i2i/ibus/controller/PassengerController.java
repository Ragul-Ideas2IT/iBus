package com.i2i.ibus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.i2i.ibus.dto.PassengerDto;
import com.i2i.ibus.service.PassengerService;

/**
 * CRUD operation for passenger.
 * 
 * @author Tamilmani
 *
 */
@RestController(value = "api/v1/users/{id}/passengers")
public class PassengerController {

    private PassengerService passengerService;

    @Autowired
    public PassengerController(PassengerService passengerService) {
    	this.passengerService = passengerService;
    }

    @PostMapping
    private PassengerDto createPassenger(@RequestBody PassengerDto passengerDto) {
    	return passengerService.createPassenger(passengerDto);
    }

}