package com.i2i.ibus.controller;

import java.time.LocalDate;
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

import com.i2i.ibus.dto.BusHistoryDto;
import com.i2i.ibus.dto.MessageDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.service.BusHistoryService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/bushistories")
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
    private ResponseEntity<List<BusHistoryDto>> getAllBusHistories() throws IBusException {

	return new ResponseEntity<List<BusHistoryDto>>(busHistoryService.getAllBusHistories(), HttpStatus.OK);
    }

    /**
     * 
     * @return
     * @throws IBusExistException
     */
    @GetMapping("/buses/{busId}")
    private ResponseEntity<List<BusHistoryDto>> getBusHistories(@PathVariable int busId) throws IBusException {

	return new ResponseEntity<List<BusHistoryDto>>(busHistoryService.getBusHistories(busId), HttpStatus.OK);
    }

    /**
     * 
     * @return
     * @throws IBusExistException
     */
    @GetMapping("/{departureDate}/{status}")
    private ResponseEntity<List<BusHistoryDto>> getByDepartureDate(LocalDate departureDate, String status)
	    throws IBusException {

	return new ResponseEntity<List<BusHistoryDto>>(busHistoryService.getByDepartureDate(departureDate, status),
		HttpStatus.OK);
    }

    /**
     * 
     * @param Dto
     * @return
     * @throws IBusException
     */
    @PutMapping("/{busHistoryId}/buses/{busId}")
    private ResponseEntity<BusHistoryDto> updateBus(@RequestBody @Valid BusHistoryDto busHistoryDto,
	    @PathVariable int busHistoryId, @PathVariable int busId) throws IBusException {

	return new ResponseEntity<BusHistoryDto>(busHistoryService.updateBusHistory(busHistoryDto, busHistoryId, busId),
		HttpStatus.OK);
    }

    /**
     * 
     * @param busId
     * @return
     * @throws IBusException
     */
    @DeleteMapping("/{busHistoryId}")
    private MessageDto deleteBus(@PathVariable int busHistoryId) throws IBusException {

	busHistoryService.deleteBusHistory(busHistoryId);
	return new MessageDto("200", "Deleted Sucessfully");
    }
}
