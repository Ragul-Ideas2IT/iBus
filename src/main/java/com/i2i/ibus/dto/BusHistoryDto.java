package com.i2i.ibus.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Ananth.
 *
 */
@Getter
@Setter
public class BusHistoryDto {
	
	private int id;
	private int busTiming;
	private LocalDate busDate;
	private int source;
	private int destination;

}
