package com.i2i.ibus.service.impl;

import com.i2i.ibus.dto.StopDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.Stop;
import com.i2i.ibus.repository.BusRepository;
import com.i2i.ibus.repository.StopRepository;
import com.i2i.ibus.service.StopService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * <h1>Bus Ticket Booking Application
 * <h1>
 * <p>
 * Used to manipulate the Bus stop details in the application. Operators are
 * manipulate the bus stop details.
 * <p>
 *
 * @author Ananth.
 * @version 1.0.
 * @created Nov 29 2022
 */
@Service
public class StopServiceImpl implements StopService {

    private StopRepository stopRepository;

    private BusRepository busRepository;

    @Autowired
    private StopServiceImpl(StopRepository stopRepository, BusRepository busRepository) {
	this.stopRepository = stopRepository;
	this.busRepository = busRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StopDto addStop(StopDto stopDto, int busId) {
	Stop stop = null;

	try {
	    if (stopRepository.findByBusIdAndCityAndLandMarkAndStopName(busId, stopDto.getCity(),
		    stopDto.getLandMark(), stopDto.getStopName()).isEmpty()) {
		stopDto.setBus(Mapper.toBusDto(busRepository.findById(busId).get()));
		stop = stopRepository.save(Mapper.toStop(stopDto));
	    } else {
		throw new IBusException("This stop is already exists.");
	    }
	} catch (NoSuchElementException exception) {
	    throw new IBusException("Bus doesnot exist");
	}
	return Mapper.toStopDto(stop);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<StopDto> getStopsByBusId(int busId) {
	List<Stop> stops = stopRepository.findAllByBusId(busId);
	List<StopDto> StopsDto = new ArrayList<StopDto>();

	for (Stop stop : stops) {
	    StopDto stopDto = null;
	    stopDto = Mapper.toStopDto(stop);
	    StopsDto.add(stopDto);
	}
	return StopsDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StopDto updateStop(StopDto stopDto, int StopId, int busId) {
	Stop stop = null;

	try {
	    if (stopRepository.findByBusIdAndCityAndLandmarkAndStopName(busId, stopDto.getCity(),
		    stopDto.getLandMark(), stopDto.getStopName(), StopId).isEmpty()) {
		stopDto.setId(StopId);
		stopDto.setBus(Mapper.toBusDto(busRepository.findById(busId).get()));
		stop = stopRepository.save(Mapper.toStop(stopDto));
	    } else {
		throw new IBusException("This stop is already exists.");
	    }
	} catch (NoSuchElementException exception) {
	    throw new IBusException("Bus doesnot exist");
	}
	return Mapper.toStopDto(stop);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteStop(int StopId) {
	stopRepository.deleteById(StopId);
    }
}
