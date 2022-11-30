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

   // @Query("from User where id = :id and booking.isExpired = false")
    @Query(value = "Select * from booking where user_id = :id and is_expired = false", nativeQuery = true)
    List<Booking> findAllUpcomingBookings(@Param("id") int id);

    //@Query("from User where id = :id and booking.isExpired = true")
    @Query(value = "Select * from booking where user_id = :id and is_expired = true", nativeQuery = true)
    List<Booking> findAllCompletedBookings(@Param("id") int id);

}
