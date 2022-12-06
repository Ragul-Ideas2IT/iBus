package com.i2i.ibus.model;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Ananth.
 * @version 1.0.
 * 
 * @created Nov 29 2022
 *
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class PickupPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String stopName;
    private String landMark;
    private LocalTime arrivingTime;
    private LocalTime departureTime;
    @ManyToOne(cascade = { jakarta.persistence.CascadeType.PERSIST })
    @JoinColumn(name = "bus_id")
    private Bus bus;
    private boolean isDeleted;
}
