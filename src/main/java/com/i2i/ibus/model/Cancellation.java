package com.i2i.ibus.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.SQLDelete;

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
public class Cancellation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(insertable = false)
    private LocalDateTime dateAndTime;
    private double refundAmount;
    private boolean isCancelled;
    private boolean isDeleted;
}
