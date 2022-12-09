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
     * It takes a pickup point object and a bus id as input, and returns a pickup point object as output
     *
     * @param pickupPointDto This is the object that will be sent to the server.
     * @param busId The id of the bus to which the pickup point is to be added.
     * @return A ResponseEntity object is being returned.
     */
    @PostMapping("/buses/{busId}")
    private ResponseEntity<PickupPointDto> addPickupPoint(@RequestBody @Valid PickupPointDto pickupPointDto,
                                                   @PathVariable int busId) {

        return new ResponseEntity<PickupPointDto>(pickupPointService.addPickupPoint(pickupPointDto, busId),
                HttpStatus.CREATED);
    }


    /**
     * It returns a list of pickup points for a given bus id
     *
     * @param busId The id of the bus whose pickup points are to be fetched.
     * @return A list of pickup points for a given bus id.
     */
    @GetMapping("/buses/{busId}")
    private ResponseEntity<List<PickupPointDto>> getPickupPointsByBusId(@PathVariable int busId) {

        return new ResponseEntity<List<PickupPointDto>>(pickupPointService.getPickupPointsByBusId(busId),
                HttpStatus.OK);
    }

    /**
     * It takes a pickupPointDto, a pickupPointId and a busId as parameters, and returns a pickupPointDto
     *
     * @param pickupPointDto The object that contains the data that will be used to update the pickup point.
     * @param pickupPointId The id of the pickup point to be updated.
     * @param busId The id of the bus that the pickup point is associated with.
     * @return A ResponseEntity object is being returned.
     */
    @PutMapping("/{pickupPointId}/buses/{busId}")
    private ResponseEntity<PickupPointDto> updatePickupPoint(@RequestBody @Valid PickupPointDto pickupPointDto,
                                                             @PathVariable int pickupPointId, @PathVariable int busId) {

        return new ResponseEntity<PickupPointDto>(
                pickupPointService.updatePickupPoint(pickupPointDto, pickupPointId, busId), HttpStatus.OK);
    }


    /**
     * It deletes the pickup point with the given id.
     *
     * @param pickupPointId The id of the pickup point to be deleted.
     * @return MessageDto
     */
    @DeleteMapping("/{pickupPointId}")
    private MessageDto deletePickupPoint(@PathVariable int pickupPointId) {

        pickupPointService.deletePickupPoint(pickupPointId);
        return new MessageDto("200", "Deleted Sucessfully");
    }
}
