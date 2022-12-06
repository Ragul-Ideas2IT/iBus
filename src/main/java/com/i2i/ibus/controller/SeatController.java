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
import com.i2i.ibus.dto.SeatDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.service.SeatService;

import jakarta.validation.Valid;

public class SeatController {

    private SeatService seatService;

    @Autowired
    private SeatController(SeatService seatService) {
	this.seatService = seatService;
    }

    /**
     * 
     * @param seatDto
     * @return
     * @throws IBusException
     */
    @PostMapping("/buses/{busId}")
    private ResponseEntity<SeatDto> addSeat(@RequestBody @Valid SeatDto seatDto, @PathVariable int busId)
	    throws IBusException {

	return new ResponseEntity<SeatDto>(seatService.addSeat(seatDto, busId), HttpStatus.CREATED);
    }

    /**
     * 
     * @return
     * @throws IBusExistException
     */
    @GetMapping
    private ResponseEntity<List<SeatDto>> getAllSeats(int busId) throws IBusException {

	return new ResponseEntity<List<SeatDto>>(seatService.getAllSeats(busId), HttpStatus.OK);
    }

    /**
     * 
     * @param seatDto
     * @return
     * @throws IBusException
     */
    @PutMapping("/{id}/buses/{busId}")
    private ResponseEntity<SeatDto> updateBus(@RequestBody @Valid SeatDto seatDto, @PathVariable int busId)
	    throws IBusException {

	return new ResponseEntity<SeatDto>(seatService.updateSeat(seatDto, busId), HttpStatus.OK);
    }

    /**
     * 
     * @param seatId
     * @return
     * @throws IBusException
     */
    @DeleteMapping("/{seatId}")
    private MessageDto deleteBus(@PathVariable int seatId) throws IBusException {

	seatService.deleteSeat(seatId);
	return new MessageDto("200", "Deleted Sucessfully");
    }
}
