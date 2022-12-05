package com.i2i.ibus.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.i2i.ibus.model.BookingDetail;

/**
 * @author Tamilmani
 * @version 1.0
 * 
 * @created Nov 30 2022
 *
 */
@Repository
public interface BookingDetailRepository extends JpaRepository<BookingDetail, Integer> {
    
    @Query("from BookingDetail where isDeleted = false and id = :id")
    Optional<BookingDetail> findById(int id);

    @Query("from BookingDetail b join b.seat s where b.isDeleted = false and s.id = :seatId")
    BookingDetail findBySeatId(int seatId);

    @Query("from BookingDetail b join b.booking bb where b.isDeleted = false and bb.id = :bookingId")
    List<BookingDetail> findAllById(int bookingId);

    @Query(value = "UPDATE booking_detail SET is_deleted = true where booking_id = :bookingId and is_deleted = false", nativeQuery = true)
    void deleteAllByBookingId(int bookingId);

}