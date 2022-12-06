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

import com.i2i.ibus.dto.BusHistoryDto;
import com.i2i.ibus.dto.MessageDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.service.BusHistoryService;
import jakarta.validation.Valid;

public class BusHistoryController {

    private BusHistoryService busHistoryService;

    @Autowired
    private BusHistoryController(BusHistoryService busHistoryService) {
	this.busHistoryService = busHistoryService;
    }

    /**
     * 
     * @param BusHistoryDto
     * @return
     * @throws IBusException
     */
    @PostMapping("/buses/{busId}")
    private ResponseEntity<BusHistoryDto> addBusHistory(@RequestBody @Valid BusHistoryDto busHistoryDto,
	    @PathVariable int busId) throws IBusException {

	return new ResponseEntity<BusHistoryDto>(busHistoryService.addBusHistory(busHistoryDto, busId),
		HttpStatus.CREATED);
    }

    /**
     * 
     * @return
     * @throws IBusExistException
     */
    @GetMapping
    private ResponseEntity<List<BusHistoryDto>> getAllBusHistories(int busId) throws IBusException {

	return new ResponseEntity<List<BusHistoryDto>>(busHistoryService.getAllBusHistories(busId), HttpStatus.OK);
    }

    /**
     * 
     * @param Dto
     * @return
     * @throws IBusException
     */
    @PutMapping("/{id}/buses/{busId}")
    private ResponseEntity<BusHistoryDto> updateBus(@RequestBody @Valid BusHistoryDto busHistoryDto,
	    @PathVariable int busId) throws IBusException {

	return new ResponseEntity<BusHistoryDto>(busHistoryService.updateBusHistory(busHistoryDto, busId),
		HttpStatus.OK);
    }

    /**
     * 
     * @param busId
     * @return
     * @throws IBusException
     */
    @DeleteMapping("/{busId}")
    private MessageDto deleteBus(@PathVariable int busHistoryId) throws IBusException {

	busHistoryService.deleteBusHistory(busHistoryId);
	return new MessageDto("200", "Deleted Sucessfully");
    }
}
