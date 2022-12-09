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
     * It takes a bus history DTO and a bus ID, and returns a bus history DTO
     *
     * @param busHistoryDto This is the object that will be sent to the server.
     * @param busId The id of the bus that the history is being added to.
     * @return ResponseEntity<BusHistoryDto>
     */
    @PostMapping("/buses/{busId}")
    private ResponseEntity<BusHistoryDto> addBusHistory(@RequestBody @Valid BusHistoryDto busHistoryDto,
                                                        @PathVariable int busId) {
        return new ResponseEntity<BusHistoryDto>(busHistoryService.addBusHistory(busHistoryDto, busId),
                HttpStatus.CREATED);
    }

    /**
     * This function returns a list of all bus histories
     *
     * @return A list of BusHistoryDto objects.
     */
    @GetMapping
    private ResponseEntity<List<BusHistoryDto>> getAllBusHistories() {

        return new ResponseEntity<List<BusHistoryDto>>(busHistoryService.getAllBusHistories(), HttpStatus.OK);
    }


    /**
     * It returns a list of bus histories for a given bus id
     *
     * @param busId The id of the bus whose history you want to see.
     * @return A list of BusHistoryDto objects.
     */
    @GetMapping("/buses/{busId}")
    private ResponseEntity<List<BusHistoryDto>> getAllBusHistoriesByBusId(@PathVariable int busId) {

        return new ResponseEntity<List<BusHistoryDto>>(busHistoryService.getBusHistories(busId), HttpStatus.OK);
    }


    /**
     * It takes in a departure date, source and destination and returns a list of bus history dto's
     *
     * @param departureDate The date on which the bus is scheduled to depart.
     * @param source The source city of the bus
     * @param destination The destination of the bus.
     * @return A list of BusHistoryDto objects.
     */
    @GetMapping("/{departureDate}/{source}/{destination}")
    private ResponseEntity<List<BusHistoryDto>> getByDepartureDate(@PathVariable LocalDate departureDate,
                                                                   @PathVariable String source, @PathVariable String destination) {

        return new ResponseEntity<List<BusHistoryDto>>(
                busHistoryService.getByDepartureDate(departureDate, source, destination), HttpStatus.OK);
    }

    /**
     * It updates the bus history.
     *
     * @param busHistoryDto The object that will be updated.
     * @param busHistoryId The id of the bus history you want to update.
     * @param busId The id of the bus that you want to update.
     * @return A ResponseEntity object is being returned.
     */
    @PutMapping("/{busHistoryId}/buses/{busId}")
    private ResponseEntity<BusHistoryDto> updateBusHistory(@RequestBody @Valid BusHistoryDto busHistoryDto,
                                                    @PathVariable int busHistoryId, @PathVariable int busId) {

        return new ResponseEntity<BusHistoryDto>(busHistoryService.updateBusHistory(busHistoryDto, busHistoryId, busId),
                HttpStatus.OK);
    }

    /**
     * It deletes the bus history by id.
     *
     * @param busHistoryId The id of the bus history to be deleted.
     * @return MessageDto is being returned.
     */
    @DeleteMapping("/{busHistoryId}")
    private MessageDto deleteBusHistory(@PathVariable int busHistoryId) {

        busHistoryService.deleteBusHistory(busHistoryId);
        return new MessageDto("200", "Deleted Sucessfully");
    }

}
