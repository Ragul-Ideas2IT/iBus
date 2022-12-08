package com.i2i.ibus.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.SQLDelete;

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
@SQLDelete(sql = "UPDATE payment SET is_deleted = true WHERE id=?")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double amount;
    private String status;
    private String modeOfPayment;
    private int cvvNumber;
    private long cardNumber;
    private String cardHolderName;
    private LocalDateTime time;
    @ManyToOne
    private Booking booking;
    private boolean isDeleted;

}