package com.i2i.ibus.model;

import org.springframework.context.annotation.Bean;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Passenger {

	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Integer id;	
	private Integer age;
	private String name;
	private String gender;
	private String seatNumber;
	private String busNumber;
	private Boolean isDeleted;

}