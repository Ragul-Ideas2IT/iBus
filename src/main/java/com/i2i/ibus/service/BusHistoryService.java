package com.i2i.ibus.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.i2i.ibus.dto.BusHistoryDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.BusHistory;
import com.i2i.ibus.repository.BusHistoryRepository;
import com.i2i.ibus.repository.BusRepository;

public interface BusHistoryService {

    /**
     * Adds a new bus history to the database
     *
     * @param busHistoryDto This is the object that contains the information that will be added to the database.
     * @param busId The id of the bus that the history is being added to.
     * @return BusHistoryDto
     */
    BusHistoryDto addBusHistory(BusHistoryDto busHistoryDto, int busId);

	/**
	 * > Get all bus histories
	 *
	 * @return A list of BusHistoryDto objects.
	 */
	List<BusHistoryDto> getAllBusHistories();

	/**
	 * Get a list of bus histories for a given bus.
	 *
	 * @param busId The id of the bus you want to get the history for.
	 * @return A list of BusHistoryDto objects.
	 */
	List<BusHistoryDto> getBusHistories(int busId);

	/**
	 * "Get all bus history records that have a departure date of `departureDate` and a source of `source` and a destination
	 * of `destination`."
	 *
	 * The function is named `getByDepartureDate` because that's the most important thing we're looking for. The other two
	 * parameters are just there to narrow down the results
	 *
	 * @param departureDate The date on which the bus is scheduled to depart.
	 * @param source The source city of the bus
	 * @param destination The destination of the bus.
	 * @return List of BusHistoryDto
	 */
	List<BusHistoryDto> getByDepartureDate(LocalDate departureDate, String source, String destination);

	/**
	 * Update a bus history by bus history id and bus id
	 *
	 * @param busHistoryDto This is the object that contains the data that you want to update.
	 * @param busHistoryId The id of the bus history you want to update.
	 * @param busId The id of the bus that the history is being added to.
	 * @return BusHistoryDto
	 */
	BusHistoryDto updateBusHistory(BusHistoryDto busHistoryDto, int busHistoryId, int busId);

	/**
	 * Delete a bus history
	 *
	 * @param busHistoryId The id of the bus history to be deleted.
	 */
	void deleteBusHistory(int busHistoryId);

	/**
	 * > This function validates the time of the bus history
	 *
	 * @param busHistoryDto The object that contains the data to be validated.
	 * @return boolean
	 */
	boolean validateTime(BusHistoryDto busHistoryDto);

	/**
	 * It sets the status of the busHistory object.
	 *
	 * @param busHistory The bus history object that you want to set the status of.
	 */
	void setStatus(BusHistory busHistory);
}
