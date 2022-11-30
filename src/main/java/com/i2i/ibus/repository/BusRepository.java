package com.i2i.ibus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.i2i.ibus.model.Bus;

/**
 * @author Ananth.
 *
 */
public interface BusRepository extends JpaRepository<Bus, Integer> {

	public Bus getByBusNumber(String busNumber);
	
}
