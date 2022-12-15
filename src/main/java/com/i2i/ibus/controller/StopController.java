/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.controller;

import com.i2i.ibus.constants.Constants;
import com.i2i.ibus.dto.MessageDto;
import com.i2i.ibus.dto.StopDto;
import com.i2i.ibus.service.StopService;
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
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Bus Ticket Booking Application
 * Used to manipulate the Bus stop details in the application. Operators are
 * manipulate the bus stop details.
 *
 * @author Ananth.
 * @version 1.0.
 * @since Nov 29 2022
 */
@RestController
@RequestMapping("api/v1/buses/stops")
public class StopController {

    private StopService stopService;

    @Autowired
    private StopController(StopService stopService) {
	this.stopService = stopService;
    }

    /**
     * Used to add bus stop details given by operators.
     *
     * @param stopDto stop details given by operators to add.
     * @param busId   The id of the bus to which the stop is to be added.
     * @return returns the added bus stop details.
     */
    @PostMapping
    private ResponseEntity<StopDto> addStop(@RequestBody @Valid StopDto stopDto) {

	return new ResponseEntity<StopDto>(stopService.addStop(stopDto), HttpStatus.CREATED);
    }

    /**
     * Returns a list of stops for a given bus id
     *
     * @param busId The id of the bus whose stops are to be fetched.
     * @return A list of stops for a given bus id.
     */
    @GetMapping("/{busId}")
    private ResponseEntity<List<StopDto>> getStopsByBusId(@PathVariable int busId) {

	return new ResponseEntity<List<StopDto>>(stopService.getStopsByBusId(busId), HttpStatus.OK);
    }

    /**
     * Update the stop details
     *
     * @param stopDto given by operators will be used to update the stop.
     * @param stopId  The id of the stop to be updated.
     * @return returns the updated stop details.
     */
    @PutMapping("/{stopId}")
    private ResponseEntity<StopDto> updateStop(@RequestBody @Valid StopDto stopDto, @PathVariable int stopId) {

	return new ResponseEntity<StopDto>(stopService.updateStop(stopDto, stopId), HttpStatus.OK);
    }

    /**
     * Deletes the stop with the given id.
     *
     * @param stopId The id of the stop to be deleted.
     * @return MessageDto
     */
    @DeleteMapping("/{stopId}")
    private MessageDto deleteStop(@PathVariable int stopId) {

	stopService.deleteStop(stopId);
	return new MessageDto(Constants.EVERYTHING_IS_OK, Constants.DELETE_MESSAGE);
    }
}
