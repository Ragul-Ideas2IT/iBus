package com.i2i.ibus.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.i2i.ibus.model.Booking;
import com.i2i.ibus.model.User;

/**
 * @author Ragul
 * @version 1.0
 * 
 * @created Nov 29 2022
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("from User where isDeleted = false")
    Optional<User> findByMailId(String mailId);

    @Query("from User where isDeleted = false")
    Optional<User> findByPhoneNumber(String phoneNumber);

    @Query("from User where isDeleted = false")
    List<User> findAll();

    @Query("from User where isDeleted = false and id = :id")
    Optional<User> findById(int id);

    @Query(value = "Select * from booking where user_id = :id and is_expired = false", nativeQuery = true)
    List<Booking> findAllUpcomingBookings(@Param("id") int id);

    @Query(value = "Select * from booking where user_id = :id and is_expired = true", nativeQuery = true)
    List<Booking> findAllCompletedBookings(@Param("id") int id);

}
