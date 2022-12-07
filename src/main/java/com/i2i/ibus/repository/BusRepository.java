package com.i2i.ibus.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.i2i.ibus.model.Bus;

/**
 * 
 * @author Ananth.
 * @version 1.0.
 * 
 * @created Nov 29 2022
 *
 */
@Repository
public interface BusRepository extends JpaRepository<Bus, Integer> {

    @Query("From Bus Where busNumber = :busNumber and isDeleted = false")
    public Optional<Bus> findByBusNumber(String busNumber);

    @Query("From Bus Where busNumber = :busNumber and id != :id")
    public Optional<Bus> findByBusNumberForUpdate(String busNumber, int id);

    @Query("from Bus where isDeleted = false")
    public List<Bus> findAll();
    
    @Query("from Bus where id = :id and isDeleted = false")
    public Optional<Bus> findById(int id);
}