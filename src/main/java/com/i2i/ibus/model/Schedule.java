package com.i2i.ibus.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

/**
 * Used to get the bus schedule from the operators.
 * 
 * @author Ananth.
 * @version 1.0.
 * 
 * @since Nov 29 2022
 *
 */
@Getter
@Setter
@Entity
@Where(clause = "is_deleted = false")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalDate arrivingDate;
    private LocalTime arrivingTime;
    private String source;
    private String destination;
    private LocalTime actualDepartureTime;
    private LocalTime actualArrivingTime;
    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;
    private String status;
    private boolean isDeleted;

}
