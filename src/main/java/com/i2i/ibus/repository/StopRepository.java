package com.i2i.ibus.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.i2i.ibus.model.Stop;

/**
 * Used to save and retrive the bus stop details.
 * 
 * @author Ananth.
 * @version 1.0.
 * 
 * @created Nov 29 2022
 *
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
     * @param busId given by the operator.
     * @param city given by the operator.
     * @param landMark given by the operator.
     * @param stopName given by the operator.
     * @return the bus details found or else nothing.
     */
    Optional<Stop> findByBusIdAndCityAndLandMarkAndStopName(int busId, String city, String landMark, String stopName);

    @Query(value = "Select * from pickup_point where bus_id = :busId and city = :city and land_mark = :landMark and stop_name = :stopName and is_deleted = false and id != :id", nativeQuery = true)
    Optional<Stop> findByBusIdAndCityAndLandmarkAndStopName(int busId, String city, String landMark, String stopName,
	    int id);
}