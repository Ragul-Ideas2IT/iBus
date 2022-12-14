package com.i2i.ibus.service.impl;

import com.i2i.ibus.constants.Constants;
import com.i2i.ibus.dto.StopDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.Stop;
import com.i2i.ibus.repository.BusRepository;
import com.i2i.ibus.repository.StopRepository;
import com.i2i.ibus.service.StopService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private Logger logger = LogManager.getLogger(StopServiceImpl.class);
    
    /**
     * {@inheritDoc}
     */
    @Override
    public StopDto addStop(StopDto stopDto) {
	Stop stop = null;

	try {
	    if (stopRepository.findByBusIdAndCityAndLandMarkAndStopName(stopDto.getBusId(), stopDto.getCity(),
		    stopDto.getLandMark(), stopDto.getStopName()).isEmpty()) {
		stopDto.setBus(Mapper.toBusDto(busRepository.findById(stopDto.getBusId()).get()));
		stop = stopRepository.save(Mapper.toStop(stopDto));
	    } else {
		logger.error(Constants.SAME_SOURCE_AND_DESTINATION);
		throw new IBusException(Constants.SAME_SOURCE_AND_DESTINATION);
	    }
	} catch (NoSuchElementException exception) {
	    logger.error("BusID " + stopDto.getBusId() + Constants.NOT_EXIST);
	    throw new IBusException(Constants.BUSID_NOT_EXIST);
	}
	return Mapper.toStopDto(stop);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<StopDto> getStopsByBusId(int busId) {
	List<Stop> stops = stopRepository.findAllByBusId(busId);
	List<StopDto> stopsDto = new ArrayList<StopDto>();

	for (Stop stop : stops) {
	    StopDto stopDto = null;
	    stopDto = Mapper.toStopDto(stop);
	    stopsDto.add(stopDto);
	}
	return stopsDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StopDto updateStop(StopDto stopDto, int StopId) {
	Stop stop = null;

	try {
	    if (stopRepository.findByBusIdAndCityAndLandmarkAndStopName(stopDto.getBusId(), stopDto.getCity(),
		    stopDto.getLandMark(), stopDto.getStopName(), StopId).isEmpty()) {
		stopDto.setId(StopId);
		stopDto.setBus(Mapper.toBusDto(busRepository.findById(stopDto.getBusId()).get()));
		stop = stopRepository.save(Mapper.toStop(stopDto));
	    } else {
		logger.error(Constants.SAME_SOURCE_AND_DESTINATION);
		throw new IBusException(Constants.SAME_SOURCE_AND_DESTINATION);
	    }
	} catch (NoSuchElementException exception) {
	    logger.error("BusID " + stopDto.getBusId() + Constants.NOT_EXIST);
	    throw new IBusException(Constants.BUSID_NOT_EXIST);
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
