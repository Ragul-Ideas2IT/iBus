package com.i2i.ibus.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * Author ESAKKIRAJA 
 */
@Setter @Getter
@Entity
@NoArgsConstructor
@Table(name = "cancellation")
public class Cancellation {
	
	@Id
	private int id;
	private LocalDateTime dateAndTime;
	private double refund;
	//private Notification notification;
}
