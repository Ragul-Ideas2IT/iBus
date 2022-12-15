/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.repository;

import com.i2i.ibus.model.Stop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Bus Ticket Booking Application
 * Used to save and retrieve the bus stop details.
 *
 * @author Ananth.
 * @version 1.0.
 * @since Nov 29 2022
 */
@Repository
public interface StopRepository extends JpaRepository<Stop, Integer> {

    /**
     * Used to get the bus stop details by the given bus id.
     *
     * @param busId given by the operator.
     * @return the bus details found or else nothing.
     */
    List<Stop> findAllByBusId(int busId);

    /**
     * Used to get the bus stop details by the given bus id and city name.
     *
     * @param busId given by the operator.
     * @param city  given by the operator.
     * @return the bus details found or else nothing.
     */
    List<Stop> findAllByBusIdAndCity(int busId, String city);

    /**
     * Used to get the bus stop details by the given bus id and city name and stop
     * name.
     *
     * @param busId    given by the operator.
     * @param city     given by the operator.
     * @param stopName given by the operator.
     * @return the bus details found or else nothing.
     */
    Optional<Stop> findAllByBusIdAndCityAndStopName(int busId, String city, String stopName);

    /**
     * Used to get the bus stop details by the given bus id and city name and stop
     * name and landmark.
     *
     * @param busId    given by the operator.
     * @param city     given by the operator.
     * @param landMark given by the operator.
     * @param stopName given by the operator.
     * @return the bus details if found or else nothing.
     */
    Optional<Stop> findByBusIdAndCityAndLandMarkAndStopName(int busId, String city, String landMark, String stopName);

    /**
     * Used to get the bus stop details by the given bus id and city name and stop
     * name and landmark but not for the given seat id.
     *
     * @param busId    given by the operator.
     * @param city     given by the operator.
     * @param landMark given by the operator.
     * @param stopName given by the operator.
     * @param id       given by the operator.
     * @return the bus details if found or else nothing.
     */
    Optional<Stop> findByBusIdAndCityAndLandMarkAndStopNameAndIdNot(int busId, String city, String landMark,
	    String stopName, int id);
}
