/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.controller;

import com.i2i.ibus.constants.Constants;
import com.i2i.ibus.dto.BusDto;
import com.i2i.ibus.dto.MessageDto;
import com.i2i.ibus.service.BusService;
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
 * <h1>Bus Ticket Booking Application
 * <h1>
 * <p>
 * Used to manipulate the Bus details in the application. Operators are
 * manipulate the bus details.
 * <p>
 *
 * @author  Ananth.
 * @version 1.0.
 * @since   Nov 29 2022
 */
@RestController
@RequestMapping("api/v1/buses")
public class BusController {

    private BusService busService;

    @Autowired
    private BusController(BusService busService) {
        this.busService = busService;
    }

    /**
     * It is used to add the bus details from the operators.
     *
     * @param operatorId The id of the operator to which the bus belongs.
     * @return the saved bus details and httpstatus.
     */
    @PostMapping
    private ResponseEntity<BusDto> addBus(@RequestBody @Valid BusDto busDto) {

        return new ResponseEntity<BusDto>(busService.addBus(busDto), HttpStatus.CREATED);
    }

    /**
     * Used to get the all bus details.
     *
     * @return A list of Bus details.
     */
    @GetMapping
    private ResponseEntity<List<BusDto>> getAllBuses() {

        return new ResponseEntity<List<BusDto>>(busService.getAllBuses(), HttpStatus.OK);
    }

    /**
     * It gives a list of buses that are owned by a specific operator
     *
     * @param operatorId The id of the operator whose buses you want to find.
     * @return A list of buses that belong to the operator with the given id.
     */
    @GetMapping("/operators/{operatorId}")
    private ResponseEntity<List<BusDto>> getByOperatorId(@PathVariable int operatorId) {

        return new ResponseEntity<List<BusDto>>(busService.getAllByOperatorId(operatorId), HttpStatus.OK);
    }

    /**
     * It returns a bus details with the given id.
     *
     * @param busId The id of the bus you want to get.
     * @return the bus details of the given bus id.
     */
    @GetMapping("/{busId}")
    private ResponseEntity<BusDto> getById(@PathVariable int busId) {

        return new ResponseEntity<BusDto>(busService.getById(busId), HttpStatus.OK);
    }

    /**
     * Used to update the bus details.
     *
     * @param busDto The bus deatils that we want to update.
     * @param busId  The id of the bus to be updated.
     * @return the updated bus details.
     */
    @PutMapping("/{busId}")
    private ResponseEntity<BusDto> updateBus(@RequestBody @Valid BusDto busDto, @PathVariable int busId) {

        return new ResponseEntity<BusDto>(busService.updateBus(busDto, busId), HttpStatus.OK);
    }

    /**
     * Used to delete a bus by operators.
     *
     * @param busId The id of the bus to be deleted.
     * @return Deleted message when the bus details are deleted.
     */
    @DeleteMapping("/{busId}")
    private MessageDto deleteBus(@PathVariable int busId) {

        busService.deleteBus(busId);
        return new MessageDto(Constants.EVERYTHING_IS_OK, Constants.DELETE_MESSAGE);
    }
}
