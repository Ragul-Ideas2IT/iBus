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
 * 
 * @created Nov 29 2022
 *
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
     * <p>
     * 
     * @param busDto
     * @return
     * @throws IBusException
     */
    @PostMapping("/operators/{operatorId}")
    private ResponseEntity<BusDto> addBus(@RequestBody @Valid BusDto busDto, @PathVariable int operatorId) {

	return new ResponseEntity<BusDto>(busService.addBus(busDto, operatorId), HttpStatus.CREATED);
    }


    @GetMapping
    private ResponseEntity<List<BusDto>> getAllBuses() {

	return new ResponseEntity<List<BusDto>>(busService.getAllBuses(), HttpStatus.OK);
    }

    /**
     * 
     * @return
     * @throws IBusExistException
     */
    @GetMapping("/operators/{operatorId}")
    private ResponseEntity<List<BusDto>> findByOperatorId(@PathVariable int operatorId) {

	return new ResponseEntity<List<BusDto>>(busService.getAllByOperatorId(operatorId), HttpStatus.OK);
    }

    /**
     * 
     * @return
     * @throws IBusExistException
     */
    @GetMapping("/{busId}")
    private ResponseEntity<BusDto> findById(@PathVariable int busId) {

	return new ResponseEntity<BusDto>(busService.getById(busId), HttpStatus.OK);
    }

    /**
     * 
     * @param busDto
     * @return
     * @throws IBusException
     */
    @PutMapping("/{busId}/operators/{operatorId}")
    private ResponseEntity<BusDto> updateBus(@RequestBody @Valid BusDto busDto, @PathVariable int busId,
	    @PathVariable int operatorId) {

	return new ResponseEntity<BusDto>(busService.updateBus(busDto, busId, operatorId), HttpStatus.OK);
    }

    /**
     * 
     * @param busId
     * @return
     * @throws IBusException
     */
    @DeleteMapping("/{busId}")
    private MessageDto deleteBus(@PathVariable int busId) {

	busService.deleteBus(busId);
	return new MessageDto("200", "Deleted Sucessfully");
    }
}
