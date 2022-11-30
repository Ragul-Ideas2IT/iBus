package com.i2i.ibus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.i2i.ibus.dto.BusDto;
import com.i2i.ibus.exception.AlreadyExistException;
import com.i2i.ibus.service.BusService;

/**
 * @author Ananth.
 *
 */
@RestController
@RequestMapping("/api/v1/ibus/buses")
public class BusController {

	@Autowired
	private BusService busService;
	
	/**
	 * 
	 * @param busDto
	 * @return
	 * @throws AlreadyExistException
	 */
	@PostMapping
	private ResponseEntity<BusDto> addBus(BusDto busDto) throws AlreadyExistException {

		return new ResponseEntity<BusDto>(busService.addBus(busDto), HttpStatus.CREATED);
	}
	
	/**
	 * 
	 * @return
	 * @throws AlreadyExistException
	 */
	@GetMapping
	private ResponseEntity<List<BusDto>> getAllBuses() throws AlreadyExistException {

		return new ResponseEntity<List<BusDto>>(busService.getAllBuses(), HttpStatus.OK);
	}
}
