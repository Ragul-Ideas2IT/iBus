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

    Optional<Bus> findByBusNumber(String busNumber);

    @Query("From Bus Where busNumber = :busNumber and id != :id")
    Optional<Bus> findByBusNumberForUpdate(String busNumber, int id);

    List<Bus> findByOperatorId(int operatorId);
}