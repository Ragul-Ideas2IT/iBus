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
import com.i2i.ibus.dto.SeatDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.service.SeatService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/seats")
public class SeatController {

    private SeatService seatService;

    @Autowired
    private SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    /**
     * It takes a seatDto and a busId, and returns a seatDto
     *
     * @param seatDto This is the object that will be sent to the server.
     * @param busId The id of the bus to which the seat is to be added.
     * @return A ResponseEntity object is being returned.
     */
    @PostMapping("/buses/{busId}")
    private ResponseEntity<SeatDto> addSeat(@RequestBody @Valid SeatDto seatDto, @PathVariable int busId) {

        return new ResponseEntity<SeatDto>(seatService.addSeat(seatDto, busId), HttpStatus.CREATED);
    }

    /**
     * It returns a list of all seats in a bus
     *
     * @param busId The id of the bus whose seats are to be fetched.
     * @return A list of seats for a specific bus.
     */
    @GetMapping("/buses/{busId}")
    private ResponseEntity<List<SeatDto>> getAllSeats(@PathVariable int busId) {

        return new ResponseEntity<List<SeatDto>>(seatService.getAllByBusId(busId), HttpStatus.OK);
    }

    /**
     * This function is a GET request that takes in a seatId and returns a SeatDto object
     *
     * @param seatId The id of the seat you want to get.
     * @return A SeatDto object is being returned.
     */
    @GetMapping("/{seatId}")
    private ResponseEntity<SeatDto> getBySeatId(@PathVariable int seatId) {

        return new ResponseEntity<SeatDto>(seatService.getBySeatId(seatId), HttpStatus.OK);
    }

    /**
     * It takes a seatDto, a seatId, and a busId, and returns a seatDto
     *
     * @param seatDto The object that will be updated.
     * @param seatId The id of the seat you want to update.
     * @param busId The id of the bus that the seat belongs to.
     * @return A response entity with the updated seat and a status of OK.
     */
    @PutMapping("/{seatId}/buses/{busId}")
    private ResponseEntity<SeatDto> updateSeat(@RequestBody @Valid SeatDto seatDto, @PathVariable int seatId,
                                              @PathVariable int busId) {

        return new ResponseEntity<SeatDto>(seatService.updateSeat(seatDto, seatId, busId), HttpStatus.OK);
    }

    /**
     * It deletes a seat from the database.
     *
     * @param seatId The id of the seat to be deleted.
     * @return MessageDto
     */
    @DeleteMapping("/{seatId}")
    private MessageDto deleteSeat(@PathVariable int seatId) {

        seatService.deleteSeat(seatId);
        return new MessageDto("200", "Deleted Sucessfully");
    }
}
