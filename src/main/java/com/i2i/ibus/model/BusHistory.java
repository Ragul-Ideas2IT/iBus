package com.i2i.ibus.model;

import java.time.LocalDate;
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
	private int busTiming;
	private LocalDate busDate;
	private int source;
	private int destination;

}
