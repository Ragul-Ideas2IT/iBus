package com.i2i.ibus.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.i2i.ibus.model.Seat;

/**
 * Used to save and retrive the bus seat details.
 * 
 * @author Ananth.
 * @version 1.0.
 * 
 * @since Nov 29 2022
 *
 */
@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {

    /**
     * Used to get the bus seat details by the given bus id.
     * 
     * @param busId given by the operator.
     * @return the bus details found or else nothing.
     */
    List<Seat> findAllByBusId(int busId);

    /**
     * Used to get seat details bus id and seat number
     * 
     * @param seatNumber given by operator.
     * @param busId      given by operator.
     * @return the bus details found or else nothing.
     */
    Optional<Seat> findBySeatNumberAndBusId(String seatNumber, int busId);

    /**
     * Used to get seat details bus id and seat number and seat id.
     * 
     * @param seatNumber given by operator.
     * @param busId      given by operator.
     * @param seatId     given by operator.
     * @return the bus details found or else nothing.
     */
    Optional<Seat> findBySeatNumberAndBusIdAndId(String seatNumber, int busId, int seatId);
}
