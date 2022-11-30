package com.i2i.ibus.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Ananth.
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
    private LocalTime busTiming;
    private LocalDate busDate;
    private String source;
    private String destination;
    @Column(columnDefinition = "bit default 0")
    private boolean isDeleted;

}
