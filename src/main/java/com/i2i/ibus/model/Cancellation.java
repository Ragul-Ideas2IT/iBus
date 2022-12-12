package com.i2i.ibus.model;

import java.time.LocalDateTime;


import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import  javax.persistence.Column;
import  javax.persistence.Entity;
import  javax.persistence.GeneratedValue;
import  javax.persistence.GenerationType;
import  javax.persistence.Id;
import  javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Esakkiraja E
 * @version 1.0
 *
 * @created Nov 29 2022s
 */
@Setter
@Getter
@Entity
@NoArgsConstructor
@Table
@SQLDelete(sql = "UPDATE cancellation SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted = false")
public class Cancellation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime dateTime;
    private double refundAmount;
    private String cancellationStatus;
    private String refundStatus;
    private boolean isDeleted;
}
