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

import com.i2i.ibus.dto.MessageDto;
import com.i2i.ibus.dto.PickupPointDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.service.PickupPointService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/pickuppoints")
public class PickupPointController {

    private PickupPointService pickupPointService;

    @Autowired
    private PickupPointController(PickupPointService pickupPointService) {
	this.pickupPointService = pickupPointService;
    }

    /**
     * 
     * @param seatDto
     * @return
     * @throws IBusException
     */
    @PostMapping("/buses/{busId}")
    private ResponseEntity<PickupPointDto> addSeat(@RequestBody @Valid PickupPointDto pickupPointDto,
	    @PathVariable int busId) {

	return new ResponseEntity<PickupPointDto>(pickupPointService.addPickupPoint(pickupPointDto, busId),
		HttpStatus.CREATED);
    }

    /**
     * 
     * @return
     * @throws IBusExistException
     */
    @GetMapping("/buses/{busId}")
    private ResponseEntity<List<PickupPointDto>> getPickupPointsByBusId(@PathVariable int busId) {

	return new ResponseEntity<List<PickupPointDto>>(pickupPointService.getPickupPointsByBusId(busId),
		HttpStatus.OK);
    }

    /**
     * 
     * @param seatDto
     * @return
     * @throws IBusException
     */
    @PutMapping("/{pickupPointId}/buses/{busId}")
    private ResponseEntity<PickupPointDto> updatePickupPoint(@RequestBody @Valid PickupPointDto pickupPointDto,
	    @PathVariable int pickupPointId, @PathVariable int busId) {

	return new ResponseEntity<PickupPointDto>(
		pickupPointService.updatePickupPoint(pickupPointDto, pickupPointId, busId), HttpStatus.OK);
    }

    /**
     * 
     * @param seatId
     * @return
     * @throws IBusException
     */
    @DeleteMapping("/{pickupPointId}")
    private MessageDto deleteBus(@PathVariable int pickupPointId) {

	pickupPointService.deletePickupPoint(pickupPointId);
	return new MessageDto("200", "Deleted Sucessfully");
    }
}
