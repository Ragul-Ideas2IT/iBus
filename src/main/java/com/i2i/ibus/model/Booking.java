package com.i2i.ibus.model;

import java.sql.Timestamp;


import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/*
 * Author ESAKKIRAJA 
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int numberOfSeats;
	private double totalFare;

	@Column(insertable = false)
	@ColumnDefault(value = "CURRENT_TIMESTAMP(6)")
	private Timestamp currentDateTime;
	private String pickUpPoint;
	private String dropPoint;
	private String status;
	private LocalDateTime travelDateAndTime;
	private Boolean isExpired;
	private Boolean isDeleted;
	// @OneToMany
	// @JoinTable(name = "booking_payment")
	// private List<Payment> payment;
	// private Notification notification;
	@OneToOne(cascade = CascadeType.PERSIST)
    private Cancellation cancellation;
}
