package com.i2i.ibus.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.i2i.ibus.model.Booking;
import com.i2i.ibus.model.User;

/**
 * @author Ragul
 * @version 1.0
 * 
 * @since 29 Nov 2022
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("from User where isDeleted = false")
    Optional<User> findByMailId(String mailId);

    @Query("from User where isDeleted = false")
    Optional<User> findByPhoneNumber(String phoneNumber);

    @Override
    @Query("from User where isDeleted = false")
    List<User> findAll();

    @Query("from User where isDeleted = false and id = :id")
    Optional<User> findById(int id);

    @Query("from User where id = :id and booking.isExpired = false")
    List<Booking> findAllUpcomingBookings(int id);

    @Query("from User where id = :id and booking.isExpired = true")
    List<Booking> findAllCompletedBookings(int id);

}
