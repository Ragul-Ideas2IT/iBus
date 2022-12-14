package com.i2i.ibus.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.i2i.ibus.model.Bus;

/**
 * Used to save and retrive the bus details.
 * 
 * @author Ananth.
 * @version 1.0.
 * 
 * @since Nov 29 2022
 *
 */
@Repository
public interface BusRepository extends JpaRepository<Bus, Integer> {

    /**
     * Used to find the bus detail by bus number for operators.
     * 
     * @param busNumber given by the operator.
     * @return the bus details matches to given bus number or else nothing.
     */
    Optional<Bus> findByBusNumber(String busNumber);

    /**
     * Used to verify the given bus number is already exists in data.
     * 
     * @param busNumber from the bus details given by operator.
     * @param id from the operator used to check bus number except given id
     *           to avoid updating bus number is already exists.
     * @return the bus details found or else nothing.
     */
    Optional<Bus> findByBusNumberAndIdNot(String busNumber, int id);

    /**
     * used to get the bus details by given operator id.
     * 
     * @param operatorId given by the operator.
     * @return the list of bus details associated to that given operator id. 
     */
    List<Bus> findByOperatorId(int operatorId);
}