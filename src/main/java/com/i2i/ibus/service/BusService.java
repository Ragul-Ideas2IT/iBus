package com.i2i.ibus.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.ibus.dto.BusDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.Bus;
import com.i2i.ibus.model.BusHistory;
import com.i2i.ibus.repository.BusRepository;
import com.i2i.ibus.repository.OperatorRepository;

/**
 * 
 * @author Ananth.
 * @version 1.0.
 * 
 * @created nov 30 2022
 *
 */
@Service
public class BusService {

    private BusRepository busRepository;

    private OperatorRepository operatorRepository;

    @Autowired
    public BusService(BusRepository busRepository, OperatorRepository operatorRepository) {
	super();
	this.busRepository = busRepository;
	this.operatorRepository = operatorRepository;
    }

    /**
     * 
     * @param busDto
     * @return
     * @throws IBusException
     */
    public BusDto addBus(BusDto busDto, int operatorId) throws IBusException {
	BusDto busDTO = null;
	busDto.setOperatorDto(Mapper.toOperatorDto(operatorRepository.findById(operatorId).get()));
	Bus bus = Mapper.toBus(busDto);
	
	
	if (null == busRepository.findByBusNumber(busDto.getBusNumber())) {
	    busDTO = Mapper.toBusDto(busRepository.save(bus));
	} else {
	    throw new IBusException("Bus Number is Duplicate");
	}
	return busDTO;
    }

    /**
     * 
     * @return
     */
    public List<BusDto> getAllBuses() {
	List<Bus> buses = busRepository.findAll();
	List<BusDto> busesDto = new ArrayList<BusDto>();
	
	for (Bus bus : buses) {
	    BusDto busDto = null;
	    busDto = Mapper.toBusDto(bus);
	    busesDto.add(busDto);
	}
	return busesDto;
    }

    /**
     * 
     * @return
     */
    public List<BusDto> getBusesByRoute(String source, String destination, LocalDate departureDate) {
	List<Bus> buses = busRepository.findAll();
	List<BusDto> busesDto = new ArrayList<BusDto>();

	for (Bus bus : buses) {
	    List<BusHistory> busHistories = bus.getBusHistories();

	    for (BusHistory busHistory1 : busHistories) {
		if (busHistory1.getSource().equalsIgnoreCase(source)
			& busHistory1.getDestination().equalsIgnoreCase(destination)
			& busHistory1.getDepartureDate().equals(departureDate)) {
		    BusDto busDto = null;
		    busDto = Mapper.toBusDto(bus);
		    busesDto.add(busDto);
		}
	    }
	}
	return busesDto;
    }

    /**
     * 
     * @param busDto
     * @return
     * @throws IBusException
     */
    public BusDto updateBus(BusDto busDto, int operatorsId) throws IBusException {
	BusDto busDTO = null;
	Bus bus = Mapper.toBus(busDto);
	bus.setOperator(operatorRepository.findById(operatorsId).get());
	
	if (null == busRepository.findByBusNumberForUpdate(busDto.getBusNumber(), busDto.getId())) {
	    busDto = Mapper.toBusDto(busRepository.save(bus));
	} else {
	    throw new IBusException("Bus Number is Duplicate");
	}
	return busDTO;
    }

    /**
     * 
     * @param busId
     */
    public void deleteBus(int busId) {
	busRepository.deleteById(busId);
    }
}