package com.i2i.ibus.model;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
@Data
@Entity
@Table
@SQLDelete(sql = "UPDATE schedule SET is_deleted = true WHERE id=?")
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
