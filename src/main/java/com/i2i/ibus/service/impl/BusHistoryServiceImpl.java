package com.i2i.ibus.service.impl;

import com.i2i.ibus.dto.BusHistoryDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.BusHistory;
import com.i2i.ibus.repository.BusHistoryRepository;
import com.i2i.ibus.repository.BusRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BusHistoryService {

    private BusHistoryRepository busHistoryRepository;

    private BusRepository busRepository;

    public BusHistoryService(BusHistoryRepository busHistoryRepository, BusRepository busRepository) {
	this.busHistoryRepository = busHistoryRepository;
	this.busRepository = busRepository;
    }

    public BusHistoryDto addBusHistory(BusHistoryDto busHistoryDto, int busId) {
	BusHistory busHistory = null;

	try {
	    if (validateTime(busHistoryDto)) {
		busHistoryDto.setBus(Mapper.toBusDto(busRepository.findById(busId).get()));
		busHistory = busHistoryRepository.save(Mapper.toBusHistory(busHistoryDto));
	    } else {
		throw new IBusException("Departue and Arrival date and time not to be same");
	    }
	} catch (NoSuchElementException exception) {
	    throw new IBusException("Bus doesnot exists");
	}
	return Mapper.toBusHistoryDto(busHistory);
    }

    public List<BusHistoryDto> getAllBusHistories() {
	List<BusHistory> busHistories = busHistoryRepository.findAll();
	List<BusHistoryDto> busHistoriesDto = new ArrayList<BusHistoryDto>();

	for (BusHistory busHistory : busHistories) {
	    setStatus(busHistory);
	    BusHistoryDto busHistoryDto = null;
	    busHistoryDto = Mapper.toBusHistoryDto(busHistory);
	    busHistoriesDto.add(busHistoryDto);
	}
	return busHistoriesDto;
    }

    public List<BusHistoryDto> getBusHistories(int busId) {
	List<BusHistory> busHistories = busHistoryRepository.findByBusId(busId);
	List<BusHistoryDto> busHistoriesDto = new ArrayList<BusHistoryDto>();

	for (BusHistory busHistory : busHistories) {
	    setStatus(busHistory);
	    BusHistoryDto busHistoryDto = null;
	    busHistoryDto = Mapper.toBusHistoryDto(busHistory);
	    busHistoriesDto.add(busHistoryDto);
	}
	return busHistoriesDto;
    }

    public List<BusHistoryDto> getByDepartureDate(LocalDate departureDate, String source, String destination) {
	List<BusHistory> busHistories = busHistoryRepository.findByDepartureDate(departureDate, source, destination);
	List<BusHistoryDto> busHistoriesDto = new ArrayList<BusHistoryDto>();

	for (BusHistory busHistory : busHistories) {
	    setStatus(busHistory);
	    BusHistoryDto busHistoryDto = null;
	    busHistoryDto = Mapper.toBusHistoryDto(busHistory);
	    busHistoriesDto.add(busHistoryDto);
	}
	return busHistoriesDto;
    }

    public BusHistoryDto updateBusHistory(BusHistoryDto busHistoryDto, int busHistoryId, int busId) {
	BusHistory busHistory = null;

	try {
	    if (validateTime(busHistoryDto)) {
		busHistoryDto.setId(busHistoryId);
		busHistoryDto.setBus(Mapper.toBusDto(busRepository.findById(busId).get()));
		busHistory = busHistoryRepository.save(Mapper.toBusHistory(busHistoryDto));
	    } else {
		throw new IBusException("Departue and Arrival date and time not to be same");
	    }
	} catch (NoSuchElementException exception) {
	    throw new IBusException("Bus doesnot exception");
	}
	return Mapper.toBusHistoryDto(busHistory);
    }

    public void deleteBusHistory(int busHistoryId) {
	busHistoryRepository.deleteById(busHistoryId);
    }

    private boolean validateTime(BusHistoryDto busHistoryDto) {
	boolean isValid = false;
	LocalDateTime departureDateTime = LocalDateTime.of(busHistoryDto.getDepartureDate(),
		busHistoryDto.getDepartureTime());
	LocalDateTime arrivalDateTime = LocalDateTime.of(busHistoryDto.getArrivingDate(),
		busHistoryDto.getArrivingTime());

	if (((arrivalDateTime.compareTo(departureDateTime)) > 0)) {
	    isValid = true;
	}
	return isValid;
    }

    private void setStatus(BusHistory busHistory) {

	if (null != busHistory.getArrivingDate()) {
	    if ((LocalDate.now().compareTo(busHistory.getArrivingDate()) >= 0)
		    && (LocalTime.now().compareTo(busHistory.getArrivingTime()) >= 0)) {
		busHistory.setStatus("ended");
	    }
	}
    }
}
