package com.i2i.ibus.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
public class Bus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String busNumber;
	private int numberOfSeats;
	private int type;
	@OneToMany(cascade = {jakarta.persistence.CascadeType.PERSIST})
	@JoinColumn(name = "bus_id")
	private List<BusHistory> busHistories;
	@OneToMany(cascade = {jakarta.persistence.CascadeType.PERSIST})
	@JoinColumn(name = "bus_id")
	private List<PickupPoint> pickupPoints;
	
}
