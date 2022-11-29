package com.i2i.ibus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.i2i.ibus.model.Booking;

/*
 * Author ESAKKIRAJA 
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer>{

}
