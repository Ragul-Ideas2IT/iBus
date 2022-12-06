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

import com.i2i.ibus.dto.MessageDto;
import com.i2i.ibus.dto.PickupPointDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.service.PickupPointService;
import jakarta.validation.Valid;

public class PickupPointControlller {

    private PickupPointService pickupPointService;

    @Autowired
    private PickupPointControlller(PickupPointService pickupPointService) {
	this.pickupPointService = pickupPointService;
    }

    /**
     * 
     * @param seatDto
     * @return
     * @throws IBusException
     */
    @PostMapping("/buses/{busId}")
    private ResponseEntity<PickupPointDto> addSeat(@RequestBody @Valid PickupPointDto pickupPointDto, @PathVariable int busId)
	    throws IBusException {

	return new ResponseEntity<PickupPointDto>(pickupPointService.addPickupPoint(pickupPointDto, busId), HttpStatus.CREATED);
    }

    /**
     * 
     * @return
     * @throws IBusExistException
     */
    @GetMapping
    private ResponseEntity<List<PickupPointDto>> getAllPickupPoints(int busId) throws IBusException {

	return new ResponseEntity<List<PickupPointDto>>(pickupPointService.getAllPickupPoints(busId), HttpStatus.OK);
    }

    /**
     * 
     * @param seatDto
     * @return
     * @throws IBusException
     */
    @PutMapping("/{id}/buses/{busId}")
    private ResponseEntity<PickupPointDto> updatePickupPoint(@RequestBody @Valid PickupPointDto pickupPointDto, @PathVariable int busId)
	    throws IBusException {

	return new ResponseEntity<PickupPointDto>(pickupPointService.updatePickupPoint(pickupPointDto, busId), HttpStatus.OK);
    }

    /**
     * 
     * @param seatId
     * @return
     * @throws IBusException
     */
    @DeleteMapping("/{pickupPointId}")
    private MessageDto deleteBus(@PathVariable int pickupPointId) throws IBusException {

	pickupPointService.deletePickupPoint(pickupPointId);
	return new MessageDto("200", "Deleted Sucessfully");
    }
}
