package com.i2i.ibus.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.i2i.ibus.model.BusHistory;

@Repository
public interface BusHistoryRepository extends JpaRepository<BusHistory, Integer> {

    @Query(value = "Select * from bus_history where bus_id = :busId and is_deleted = false", nativeQuery = true)
    public List<BusHistory> findByBusId(int busId);

    @Query("from BusHistory where departureDate = :departureDate and source = :source and destination = :destination and status = 'started' and isDeleted = false")
    public List<BusHistory> findByDepartureDate(LocalDate departureDate, String source, String destination);

    @Query(value = "Select * from bus_history where bus_id = :busId and is_deleted = false and departure_date = :departureDate", nativeQuery = true)
    public Optional<BusHistory> findByBusIdAndDepartureDate(int busId, LocalDate departureDate);    
}
