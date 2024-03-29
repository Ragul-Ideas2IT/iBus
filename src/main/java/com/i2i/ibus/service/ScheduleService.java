/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.i2i.ibus.service;

import com.i2i.ibus.dto.ScheduleDto;
import com.i2i.ibus.model.Schedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

/**
 * Bus Ticket Booking Application
 * Used to manipulate the Bus schedule details in the application. Operators are
 * manipulate the bus details.
 *
 * @author Ananth.
 * @version 1.0.
 * @since Nov 29 2022
 */
public interface ScheduleService {

    /**
     * Used to add the bus schedule details from the operators.
     *
     * @param scheduleDto given by the operator.
     * @return the added bus schedule.
     */
    ScheduleDto addSchedule(ScheduleDto scheduleDto);

    /**
     * Used to get the all bus details.
     *
     * @return A list of Schedule details.
     */
    List<ScheduleDto> getAllSchedules();

    /**
     * It returns a list of bus histories for a given bus id
     *
     * @param busId The id of the bus whose schedule you want to see.
     * @return A list of ScheduleDto objects.
     */
    List<ScheduleDto> getSchedulesByBusId(int busId);

    /**
     * Takes in a departure date, source and destination and returns a list of bus
     * schedule dto's
     *
     * @param departureDate The date on which the bus is scheduled to depart.
     * @param source        The source city of the bus
     * @param destination   The destination of the bus.
     * @return A list of ScheduleDto objects.
     */
    List<ScheduleDto> getByDepartureDate(LocalDate departureDate, String source, String destination);

    /**
     * Used to updates the bus schedule.
     *
     * @param scheduleDto The schedule that will be updated.
     * @param ScheduleId  The id of the bus schedule you want to update.
     * @return the bus schedule.
     */
    ScheduleDto updateSchedule(ScheduleDto scheduleDto, int ScheduleId);

    /**
     * Used to deletes the bus schedule by id.
     *
     * @param ScheduleId The id of the bus schedule to be deleted.
     */
    void deleteSchedule(int ScheduleId);

    /**
     * Used to validates the time of the bus schedule
     *
     * @param scheduleDto shedule details that contains the data to be validated.
     * @return boolean if is correct date and time returns or else false.
     */
    boolean validateDateAndTime(ScheduleDto scheduleDto);

    /**
     * Used sets the status of the Schedule details to be ended.
     *
     * @param schedule The bus schedule object that you want to set the status of.
     */
    void setStatus(Schedule schedule);

    /**
     * It checks if a schedule already exists in the database with the same departure date, departure time, source,
     * destination and bus id. If it does, it throws an exception
     *
     * @param scheduleDto The object that contains the data that is to be validated.
     */
    void validateSchedule(ScheduleDto scheduleDto);

    /**
     * Used to get the schedule details by the bus id and departure date. The
     * schedule were given to the booking repository for validation process.
     *
     * @param busId         from the booking user
     * @param departureDate from the booking user
     * @return returns the schedule or else nothing.
     */
    Optional<Schedule> findByBusIdAndDepartureDateAndDepartureTime(int busId, LocalDate departureDate,
                                                                   LocalTime departureTime);
}
