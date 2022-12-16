/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.i2i.ibus.constants.Constants;
import com.i2i.ibus.dto.MessageDto;
import com.i2i.ibus.dto.SeatDto;
import com.i2i.ibus.service.SeatService;

import javax.validation.Valid;

/**
 * Bus Ticket Booking Application
 * Used to manipulate the Bus seat details in the application. Operators are
 * manipulate the bus seat details.
 *
 * @author Ananth.
 * @version 1.0.
 * @since Nov 29 2022
 */
@RestController
@RequestMapping("api/v1/buses/seats")
public class SeatController {

    private SeatService seatService;

    @Autowired
    private SeatController(SeatService seatService) {
	this.seatService = seatService;
    }

    /**
     * Uses to add bus seat deatils given by the operators.
     *
     * @param seatDto seat details given by the operators.
     * @return the saved bus seat details will return.
     */
    @PostMapping
    private ResponseEntity<SeatDto> addSeat(@RequestBody @Valid SeatDto seatDto) {

	return new ResponseEntity<SeatDto>(seatService.addSeat(seatDto), HttpStatus.CREATED);
    }

    /**
     * Returns a list of all seats in a bus
     *
     * @param busId The id of the bus whose seats are to be fetched.
     * @return A list of seats for a specific bus.
     */
    @GetMapping
    private ResponseEntity<List<SeatDto>> getAllSeats(@RequestParam int busId) {

	return new ResponseEntity<List<SeatDto>>(seatService.getAllByBusId(busId), HttpStatus.OK);
    }

    /**
     * Used to get the seat details of the bus by given seat id.
     *
     * @param seatId The id of the seat you want to get.
     * @return A Seat details is being returned.
     */
    @GetMapping("/{seatId}")
    private ResponseEntity<SeatDto> getBySeatId(@PathVariable int seatId) {

	return new ResponseEntity<SeatDto>(seatService.getBySeatId(seatId), HttpStatus.OK);
    }

    /**
     * Used to update seat details.
     *
     * @param seatDto seat details given by operators will be updated.
     * @param seatId  The id of the seat you want to update.
     * @return the update seat details.
     */
    @PutMapping("/{seatId}")
    private ResponseEntity<SeatDto> updateSeat(@RequestBody @Valid SeatDto seatDto, @PathVariable int seatId) {

	return new ResponseEntity<SeatDto>(seatService.updateSeat(seatDto, seatId), HttpStatus.OK);
    }

    /**
     * Deletes a seat deatils.
     *
     * @param seatId The id of the seat to be deleted.
     * @return Deleted message when the bus seat details are deleted.
     */
    @DeleteMapping("/{seatId}")
    private MessageDto deleteSeat(@PathVariable int seatId) {

	seatService.deleteSeat(seatId);
	return new MessageDto(Constants.EVERYTHING_IS_OK, Constants.DELETE_MESSAGE);
    }
}
