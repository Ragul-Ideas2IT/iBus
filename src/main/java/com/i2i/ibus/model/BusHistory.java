package com.i2i.ibus.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class BusHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalDate arrivingDate;
    private LocalTime arrivingTime;
    private String source;
    private String destination;
    private boolean isDeleted;

}
