package com.i2i.ibus.service.impl;

import com.i2i.ibus.dto.PickupPointDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.PickupPoint;
import com.i2i.ibus.repository.BusRepository;
import com.i2i.ibus.repository.PickupPointRepository;
import com.i2i.ibus.service.PickupPointService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PickupPointServiceImpl implements PickupPointService {

    private PickupPointRepository pickupPointRepository;

    private BusRepository busRepository;

    @Autowired
    private PickupPointServiceImpl(PickupPointRepository pickupPointRepository, BusRepository busRepository) {
	this.pickupPointRepository = pickupPointRepository;
	this.busRepository = busRepository;
    }

    public PickupPointDto addPickupPoint(PickupPointDto pickupPointDto, int busId) {
	PickupPoint pickupPoint = null;

	try {
	    if (pickupPointRepository.findByBusIdAndCityAndLandmarkAndStopName(busId, pickupPointDto.getCity(),
		    pickupPointDto.getLandMark(), pickupPointDto.getStopName()).isEmpty()) {
		pickupPointDto.setBus(Mapper.toBusDto(busRepository.findById(busId).get()));
		pickupPoint = pickupPointRepository.save(Mapper.toPickupPoint(pickupPointDto));
	    } else {
		throw new IBusException("This pickup point is already exists.");
	    }
	} catch (NoSuchElementException exception) {
	    throw new IBusException("Bus doesnot exist");
	}
	return Mapper.toPickupPointDto(pickupPoint);
    }

    public List<PickupPointDto> getPickupPointsByBusId(int busId) {
	List<PickupPoint> pickupPoints = pickupPointRepository.findAllByBusId(busId);
	List<PickupPointDto> pickupPointsDto = new ArrayList<PickupPointDto>();

	for (PickupPoint pickupPoint : pickupPoints) {
	    PickupPointDto pickupPointDto = null;
	    pickupPointDto = Mapper.toPickupPointDto(pickupPoint);
	    pickupPointsDto.add(pickupPointDto);
	}
	return pickupPointsDto;
    }

    public PickupPointDto updatePickupPoint(PickupPointDto pickupPointDto, int pickupPointId, int busId) {
	PickupPoint pickupPoint = null;

	try {
	    if (pickupPointRepository.findByBusIdAndCityAndLandmarkAndStopName(busId, pickupPointDto.getCity(),
		    pickupPointDto.getLandMark(), pickupPointDto.getStopName(), pickupPointId).isEmpty()) {
		pickupPointDto.setId(pickupPointId);
		pickupPointDto.setBus(Mapper.toBusDto(busRepository.findById(busId).get()));
		pickupPoint = pickupPointRepository.save(Mapper.toPickupPoint(pickupPointDto));
	    } else {
		throw new IBusException("This pickup point is already exists.");
	    }
	} catch (NoSuchElementException exception) {
	    throw new IBusException("Bus doesnot exist");
	}
	return Mapper.toPickupPointDto(pickupPoint);
    }

    public void deletePickupPoint(int pickupPointId) {
	pickupPointRepository.deleteById(pickupPointId);
    }
}
