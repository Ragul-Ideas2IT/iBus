package com.i2i.ibus.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.i2i.ibus.model.Booking;

/**
 * @author Esakkiraja E
 * @version 1.0
 *
 * @created Nov 29 2022
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer>{
    @Query("from Booking where isDeleted = false")
    List<Booking> findAll();

    @Query("from Booking where isDeleted = false and id = :id")
    Optional<Booking> findById(int id);
    
    @Query(value = "Select * from booking where user_id = :id and status = false", nativeQuery = true)
    List<Booking> findAllUpcomingBookings(@Param("id") int id);

    @Query(value = "Select * from booking where user_id = :id and status = true", nativeQuery = true)
    List<Booking> findAllCompletedBookings(@Param("id") int id);
}
