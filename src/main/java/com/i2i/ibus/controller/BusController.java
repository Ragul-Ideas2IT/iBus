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
    @PostMapping("/{operatorid}")
    private ResponseEntity<BusDto> addBus(@RequestBody BusDto busDto, @PathVariable int operatorId) throws AlreadyExistException {

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
    
    /**
     * 
     * @return
     * @throws AlreadyExistException
     */
    @GetMapping
    private ResponseEntity<List<BusDto>> getBusByName(String busName) throws AlreadyExistException {

	return new ResponseEntity<List<BusDto>>(busService.getBusByName(busName), HttpStatus.OK);
    }
    
    @PutMapping
    private ResponseEntity<BusDto> updateBus(@RequestBody BusDto busDto) throws AlreadyExistException {

	return new ResponseEntity<BusDto>(busService.updateBus(busDto), HttpStatus.OK);
    }
    
    @DeleteMapping
    private ResponseEntity<String> deleteBus(@PathVariable int busId) throws AlreadyExistException {

   	return new ResponseEntity<String>("Deleted Sucessfully", HttpStatus.OK);
       }
}
