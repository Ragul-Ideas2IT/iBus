package com.i2i.ibus.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.i2i.ibus.model.BookingDetail;
import com.i2i.ibus.model.User;

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

}