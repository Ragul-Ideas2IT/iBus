package com.i2i.ibus.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.ibus.dto.BusDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.Bus;
import com.i2i.ibus.repository.BusRepository;
import com.i2i.ibus.repository.OperatorRepository;

/**
 * 
 * @author Ananth.
 * @version 1.0.
 * 
 * @created nov 30 2022
 *
 */
 public interface BusService {

     /**
      * It adds a bus to the database.
      *
      * @param busDto This is the bus object that you want to add.
      * @param operatorId The id of the operator who owns the bus.
      * @return BusDto
      */
     BusDto addBus(BusDto busDto, int operatorId);

     /**
      * It returns a list of buses that belong to a specific operator.
      *
      * @param OperatorId The id of the operator.
      * @return A list of BusDto objects.
      */
     List<BusDto> getAllByOperatorId(int OperatorId);

     /**
      * Get a bus by its id.
      *
      * @param id The id of the bus you want to get.
      * @return A BusDto object
      */
     BusDto getById(int id);

     /**
      * Update a bus with the given busId and operatorId.
      *
      * @param busDto This is the object that contains the updated information of the bus.
      * @param busId The id of the bus you want to update.
      * @param operatorId The id of the operator who owns the bus.
      * @return BusDto
      */
     BusDto updateBus(BusDto busDto, int busId, int operatorId);

     /**
      * Deletes the bus with the given id.
      *
      * @param busId The id of the bus to be deleted.
      */
     void deleteBus(int busId);

    /**
     * Get all buses.
     *
     * @return A list of BusDto objects.
     */
    List<BusDto> getAllBuses();
}