package com.i2i.ibus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.i2i.ibus.model.Bus;

/**
 * @author Ananth.
 *
 */
public interface BusRepository extends JpaRepository<Bus, Integer> {

    @Query("From Bus Where busNumber = :busNumber")
    public Bus checkForDuplicates(String busNumber);
    
    @Query("From Bus Where busNumber = :busNumber and id != :id")
    public Bus checkForUpdateDuplicates(String busNumber, int id);
    
    @Query("from Bus where isDeleted = false")
    public List<Bus> findAll();

    @Query("from Bus where isDeleted = false and busName = :busName")
    public List<Bus> findByBusName(String busName);
	
    }
