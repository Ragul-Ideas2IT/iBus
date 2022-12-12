package com.i2i.ibus.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Tamilmani
 * @version 1.0
 * 
 * @created Nov 30 2022
 *
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
@SQLDelete(sql = "UPDATE booking_detail SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted = false")
public class BookingDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int age;
    private String name;
    private String gender;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Booking booking;
    private String seatNumber;
    private boolean isDeleted;

}