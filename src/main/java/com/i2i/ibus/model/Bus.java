package com.i2i.ibus.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
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
@Entity
@Table
@SQLDelete(sql = "UPDATE Bus SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted = false")
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String busNumber;
    private int numberOfSeats;
    private String type;
    @ManyToOne
    @JoinColumn(name = "operator_id")
    private Operator operator;
    private boolean isDeleted;
}
