package com.i2i.ibus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.i2i.ibus.model.Booking;

/**
 * Used to save and retirve the booking Details.
 * 
 * @author Esakkiraja E
 * @version 1.0
 *
 * @since Nov 29 2022
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    
    /** 
     * method used to find all user by userId
     * 
     * @param userId 
     * @return a list of  Bookings.
     */
    List<Booking> findAllByUserId(int userId);
}
