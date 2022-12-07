package com.i2i.ibus.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.i2i.ibus.dto.BusHistoryDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.BusHistory;
import com.i2i.ibus.repository.BusHistoryRepository;
import com.i2i.ibus.repository.BusRepository;

public class BusHistoryService {

    private BusHistoryRepository busHistoryRepository;

    private BusRepository busRepository;

    public BusHistoryService(BusHistoryRepository busHistoryRepository, BusRepository busRepository) {
	this.busHistoryRepository = busHistoryRepository;
	this.busRepository = busRepository;
    }

    public BusHistoryDto addBusHistory(BusHistoryDto busHistoryDto, int busId) throws IBusException {
	BusHistory busHistory = null;

	try {
	    busHistoryDto.setBusDto(Mapper.toBusDto(busRepository.findById(busId).get()));
	    busHistory = busHistoryRepository.save(Mapper.toBusHistory(busHistoryDto));
	} catch (NoSuchElementException exception) {
	    throw new IBusException("Bus doesnot exists");
	}
	return Mapper.toBusHistoryDto(busHistory);
    }

    public List<BusHistoryDto> getAllBusHistories() {
	List<BusHistory> busHistories = busHistoryRepository.findAll();
	List<BusHistoryDto> busHistoriesDto = new ArrayList<BusHistoryDto>();

	for (BusHistory busHistory : busHistories) {
	    BusHistoryDto busHistoryDto = null;
	    busHistoryDto = Mapper.toBusHistoryDto(busHistory);
	    busHistoriesDto.add(busHistoryDto);
	}
	return busHistoriesDto;
    }

    public List<BusHistoryDto> getBusHistories(int busId) {
	List<BusHistory> busHistories = busHistoryRepository.findAll();
	List<BusHistoryDto> busHistoriesDto = new ArrayList<BusHistoryDto>();

	for (BusHistory busHistory : busHistories) {
	    BusHistoryDto busHistoryDto = null;
	    busHistoryDto = Mapper.toBusHistoryDto(busHistory);
	    busHistoriesDto.add(busHistoryDto);
	}
	return busHistoriesDto;
    }
    
    public List<BusHistoryDto> getByDepartureDate(LocalDate departureDate, String status) {
	List<BusHistory> busHistories = busHistoryRepository.findByDepartureDate(departureDate, status);
	List<BusHistoryDto> busHistoriesDto = new ArrayList<BusHistoryDto>();

	for (BusHistory busHistory : busHistories) {
	    BusHistoryDto busHistoryDto = null;
	    busHistoryDto = Mapper.toBusHistoryDto(busHistory);
	    busHistoriesDto.add(busHistoryDto);
	}
	return busHistoriesDto;
    }

    public BusHistoryDto updateBusHistory(BusHistoryDto busHistoryDto, int busHistoryId, int busId) throws IBusException {
	BusHistory busHistory = null;

	try {
	    busHistoryDto.setId(busHistoryId);
	    busHistoryDto.setBusDto(Mapper.toBusDto(busRepository.findById(busId).get()));
	    busHistory = busHistoryRepository.save(Mapper.toBusHistory(busHistoryDto));
	} catch (NoSuchElementException exception) {
	    throw new IBusException("Bus doesnot exception");
	}
	return Mapper.toBusHistoryDto(busHistory);
    }

    public void deleteBusHistory(int busHistoryId) {
	busHistoryRepository.deleteById(busHistoryId);
    }
}
