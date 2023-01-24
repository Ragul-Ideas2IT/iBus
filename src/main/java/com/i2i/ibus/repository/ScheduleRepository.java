/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.repository;

import com.i2i.ibus.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

/**
 * Bus Ticket Booking Application
 * Used to save and retrieve the bus details.
 *
 * @author Ananth.
 * @version 1.0.
 * @since Nov 29 2022
 */
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    /**
     * Used to find the bus seat details by bud id.
     *
     * @param busId given by the operator.
     * @return bus details or else nothing.
     */
    List<Schedule> findByBusId(int busId);

    /**
     * Used to find the bus details by the departure date, source and destination.
     *
     * @param departureDate given by the operator or user.
     * @param source        given by the operator or user.
     * @param destination   given by the operator or user.
     * @return return the bus details from the given matched details.
     */
    List<Schedule> findByDepartureDateAndSourceAndDestination(LocalDate departureDate, String source,
                                                              String destination);

    /**
     * Find a schedule by departure date, departure time, source, destination and bus id
     *
     * @param departureDate The date on which the bus is scheduled to depart.
     * @param departureTime The time at which the bus is scheduled to depart.
     * @param source The source of the bus
     * @param destination The destination of the bus.
     * @param busId The id of the bus.
     * @return Optional<Schedule>
     */
    Optional<Schedule> findByDepartureDateAndDepartureTimeAndSourceAndDestinationAndBusId(LocalDate departureDate,
                                                                              LocalTime departureTime, String source,
                                                                              String destination, int busId);

    /**
     * Used to get the bus details from the given bus id and departure date.
     *
     * @param busId         given by the user.
     * @param departureDate given by the user.
     * @param departureTime given by the user.
     * @return the bus schedule details or else nothing.
     */
    Optional<Schedule> findByBusIdAndDepartureDateAndDepartureTime(int busId, LocalDate departureDate,
                                                            LocalTime departureTime);
}
