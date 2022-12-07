package com.i2i.ibus.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.i2i.ibus.model.Seat;

public interface SeatRepository extends JpaRepository<Seat, Integer> {

    @Query("from Seat where isDeleted = false and id = :id")
    public Optional<Seat> findBySeatId(int id);

    @Query(value = "Select * from seat where bus_id = :busId and is_deleted = false", nativeQuery = true)
    public List<Seat> findAllByBusId(int busId);

    @Query(value = "Select * from seat where bus_id = :busId, seat_number = :seatNumber and is_deleted = false", nativeQuery = true)
    public Optional<Seat> findBySeatNumberAndBusId(String seatNumber, int busId);
}
