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
import org.springframework.web.bind.annotation.RestController;

import com.i2i.ibus.dto.BusDto;
import com.i2i.ibus.dto.MessageDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.service.BusService;

import jakarta.validation.Valid;

/**
 * <h1>Bus Ticket Booking Application
 * <h1>
 * <p>
 * Used to manipulate the Bus details in the application. Operators are
 * manipulate the bus details.
 * <p>
 *
 * @author Ananth.
 * @version 1.0.
 * @created Nov 29 2022
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
     * It takes a busDto object and an operatorId, and returns a busDto object
     *
     * @param busDto This is the object that will be sent to the server.
     * @param operatorId The id of the operator to which the bus belongs.
     * @return ResponseEntity<BusDto>
     */
    @PostMapping("/operators/{operatorId}")
    private ResponseEntity<BusDto> addBus(@RequestBody @Valid BusDto busDto, @PathVariable int operatorId) {

        return new ResponseEntity<BusDto>(busService.addBus(busDto, operatorId), HttpStatus.CREATED);
    }


    /**
     * It returns a list of all the buses in the database
     *
     * @return A list of BusDto objects.
     */
    @GetMapping
    private ResponseEntity<List<BusDto>> getAllBuses() {

        return new ResponseEntity<List<BusDto>>(busService.getAllBuses(), HttpStatus.OK);
    }


    /**
     * It returns a list of buses that are owned by a specific operator
     *
     * @param operatorId The id of the operator whose buses you want to find.
     * @return A list of buses that belong to the operator with the given id.
     */
    @GetMapping("/operators/{operatorId}")
    private ResponseEntity<List<BusDto>> getByOperatorId(@PathVariable int operatorId) {

        return new ResponseEntity<List<BusDto>>(busService.getAllByOperatorId(operatorId), HttpStatus.OK);
    }


    /**
     * It returns a bus object with the given id.
     *
     * @param busId The id of the bus you want to get.
     * @return A ResponseEntity object is being returned.
     */
    @GetMapping("/{busId}")
    private ResponseEntity<BusDto> getById(@PathVariable int busId) {

        return new ResponseEntity<BusDto>(busService.getById(busId), HttpStatus.OK);
    }


    /**
     * It updates the bus details.
     *
     * @param busDto The bus object that we want to update.
     * @param busId The id of the bus to be updated.
     * @param operatorId The id of the operator who owns the bus.
     * @return ResponseEntity<BusDto>
     */
    @PutMapping("/{busId}/operators/{operatorId}")
    private ResponseEntity<BusDto> updateBus(@RequestBody @Valid BusDto busDto, @PathVariable int busId,
                                             @PathVariable int operatorId) {

        return new ResponseEntity<BusDto>(busService.updateBus(busDto, busId, operatorId), HttpStatus.OK);
    }


    /**
     * It deletes a bus from the database.
     *
     * @param busId The id of the bus to be deleted.
     * @return MessageDto
     */
    @DeleteMapping("/{busId}")
    private MessageDto deleteBus(@PathVariable int busId) {

        busService.deleteBus(busId);
        return new MessageDto("200", "Deleted Sucessfully");
    }
}
