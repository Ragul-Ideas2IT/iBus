package com.i2i.ibus.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.i2i.ibus.model.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {

    List<Seat> findAllByBusId(int busId);

    Optional<Seat> findBySeatNumberAndBusId(String seatNumber, int busId);

    Optional<Seat> findBySeatNumberAndBusIdAndId(String seatNumber, int busId, int seatId);
}
