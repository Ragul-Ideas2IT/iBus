package com.i2i.ibus.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.ibus.dto.PickupPointDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.PickupPoint;
import com.i2i.ibus.repository.BusRepository;
import com.i2i.ibus.repository.PickupPointRepository;

 public interface PickupPointService {

	/**
	 * Add a pickup point to a bus
	 *
	 * @param pickupPointDto This is the object that contains the pickup point details.
	 * @param busId The id of the bus to which the pickup point is to be added.
	 * @return PickupPointDto
	 */
	PickupPointDto addPickupPoint(PickupPointDto pickupPointDto, int busId);

     /**
      * Get a list of pickup points for a bus.
      *
      * @param busId The id of the bus.
      * @return A list of pickup points for a bus.
      */
     List<PickupPointDto> getPickupPointsByBusId(int busId);

	/**
	 * Update a pickup point for a bus.
	 *
	 * @param pickupPointDto This is the object that contains the new pickup point details.
	 * @param pickupPointId The id of the pickup point you want to update.
	 * @param busId The id of the bus that the pickup point belongs to.
	 * @return PickupPointDto
	 */
	PickupPointDto updatePickupPoint(PickupPointDto pickupPointDto, int pickupPointId, int busId);

	/**
	 * Delete a pickup point
	 *
	 * @param pickupPointId The id of the pickup point to be deleted.
	 */
	void deletePickupPoint(int pickupPointId);
}
