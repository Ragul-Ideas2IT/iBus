/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.controller;

import com.i2i.ibus.constants.Constants;
import com.i2i.ibus.dto.MessageDto;
import com.i2i.ibus.dto.ScheduleDto;
import com.i2i.ibus.service.ScheduleService;
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

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

/**
 * Bus Ticket Booking Application
 * Used to manipulate the Bus schedule details in the application. Operators are
 * manipulate the bus details.
 *
 * @author Ananth.
 * @version 1.0.
 * @since Nov 29 2022
 */
@RestController
@RequestMapping("api/v1/buses/schedules")
public class ScheduleController {

    private ScheduleService scheduleService;

    @Autowired
    private ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    /**
     * Used to add the bus schedule details from the operators.
     *
     * @param scheduleDto given by the operator.
     * @return the added bus schedule.
     */
    @PostMapping
    private ResponseEntity<ScheduleDto> addSchedule(@RequestBody @Valid ScheduleDto scheduleDto) {
        return new ResponseEntity<ScheduleDto>(scheduleService.addSchedule(scheduleDto), HttpStatus.CREATED);
    }

    /**
     * Used to get the all bus details.
     *
     * @return A list of Schedule details.
     */
    @GetMapping
    private ResponseEntity<List<ScheduleDto>> getAllSchedules() {

        return new ResponseEntity<List<ScheduleDto>>(scheduleService.getAllSchedules(), HttpStatus.OK);
    }

    /**
     * Returns a list of Schedules for a given bus id
     *
     * @param busId The id of the bus whose schedule you want to see.
     * @return A list of ScheduleDto objects.
     */
    @GetMapping("/{busId}")
    private ResponseEntity<List<ScheduleDto>> getSchedulesByBusId(@PathVariable int busId) {

        return new ResponseEntity<List<ScheduleDto>>(scheduleService.getSchedulesByBusId(busId), HttpStatus.OK);
    }

    /**
     * Takes in a departure date, source and destination and returns a list of bus
     * schedule DTOs
     *
     * @param departureDate The date on which the bus is scheduled to depart.
     * @param source        The source city of the bus
     * @param destination   The destination of the bus.
     * @return A list of ScheduleDto objects.
     */
    @GetMapping("{departureDate}/{source}/{destination}")
    private ResponseEntity<List<ScheduleDto>> getByDepartureDate(@PathVariable String departureDate,
                                                                 @PathVariable String source,
                                                                 @PathVariable String destination) {
        return new ResponseEntity<List<ScheduleDto>>(
                scheduleService.getByDepartureDate(LocalDate.parse(departureDate), source, destination), HttpStatus.OK);
    }

    /**
     * Used to update the bus schedule.
     *
     * @param scheduleDto The schedule that will be updated.
     * @param scheduleId  The id of the bus schedule you want to update.
     * @return the bus schedule.
     */
    @PutMapping("/{scheduleId}")
    private ResponseEntity<ScheduleDto> updateSchedule(@RequestBody @Valid ScheduleDto scheduleDto,
                                                       @PathVariable int scheduleId) {

        return new ResponseEntity<ScheduleDto>(scheduleService.updateSchedule(scheduleDto, scheduleId), HttpStatus.OK);
    }

    /**
     * Used to delete the bus schedule by id.
     *
     * @param scheduleId The id of the bus schedule to be deleted.
     * @return Deleted message when the bus schedule details are deleted.
     */
    @DeleteMapping("/{scheduleId}")
    private MessageDto deleteSchedule(@PathVariable int scheduleId) {

        scheduleService.deleteSchedule(scheduleId);
        return new MessageDto(Constants.EVERYTHING_IS_OK, Constants.DELETE_MESSAGE);
    }
}
