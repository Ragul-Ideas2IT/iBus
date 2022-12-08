package com.i2i.ibus.model;

import java.time.LocalDate;
import java.time.LocalTime;

import org.hibernate.annotations.SQLDelete;

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
@SQLDelete(sql = "UPDATE bus_history SET is_deleted = true WHERE id=?")
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
    private LocalTime actualDepartureTime;
    private LocalTime actualArrivingTime;
    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;
    private String status;
    private boolean isDeleted;

}
