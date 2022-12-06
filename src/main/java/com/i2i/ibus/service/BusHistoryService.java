package com.i2i.ibus.service;

import java.util.ArrayList;
import java.util.List;

import com.i2i.ibus.dto.BusHistoryDto;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.BusHistory;
import com.i2i.ibus.repository.BusHistoryRepository;

public class BusHistoryService {

    private BusHistoryRepository busHistoryRepository;

    public BusHistoryService(BusHistoryRepository busHistoryRepository) {
	super();
	this.busHistoryRepository = busHistoryRepository;
    }

    public BusHistoryDto addBusHistory(BusHistoryDto busHistoryDto, int busId) {
	BusHistory busHistory = busHistoryRepository.save(Mapper.toBusHistory(busHistoryDto));
	return Mapper.toBusHistoryDto(busHistory);
    }

    public List<BusHistoryDto> getAllBusHistories(int busId) {
	List<BusHistory> busHistories = busHistoryRepository.findAll();
	List<BusHistoryDto> busHistoriesDto = new ArrayList<BusHistoryDto>();

	for (BusHistory busHistory : busHistories) {
	    BusHistoryDto busHistoryDto = null;
	    busHistoryDto = Mapper.toBusHistoryDto(busHistory);
	    busHistoriesDto.add(busHistoryDto);
	}
	return busHistoriesDto;
    }
     
    public BusHistoryDto updateBusHistory(BusHistoryDto busHistoryDto, int busId) {
	BusHistory busHistory = busHistoryRepository.save(Mapper.toBusHistory(busHistoryDto));
	return Mapper.toBusHistoryDto(busHistory);
    }
    
    public void deleteBusHistory(int busHistoryId) {
	busHistoryRepository.deleteById(busHistoryId);
    }
}
