package com.i2i.ibus.service;

import java.util.ArrayList;
import java.util.List;

import com.i2i.ibus.dto.PickupPointDto;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.PickupPoint;
import com.i2i.ibus.repository.PickupPointRepository;

public class PickupPointService {

    private PickupPointRepository pickupPointRepository;

    public PickupPointService(PickupPointRepository pickupPointRepository) {
	this.pickupPointRepository = pickupPointRepository;
    }

    public PickupPointDto addPickupPoint(PickupPointDto pickupPointDto, int busId) {
	PickupPoint pickupPoint = pickupPointRepository.save(Mapper.toPickupPoint(pickupPointDto));
	return Mapper.toPickupPointDto(pickupPoint);
    }

    public List<PickupPointDto> getAllPickupPoints(int busId) {
	List<PickupPoint> pickupPoints = pickupPointRepository.findAll();
	List<PickupPointDto> pickupPointsDto = new ArrayList<PickupPointDto>();

	for (PickupPoint pickupPoint : pickupPoints) {
	    PickupPointDto pickupPointDto = null;
	    pickupPointDto = Mapper.toPickupPointDto(pickupPoint);
	    pickupPointsDto.add(pickupPointDto);
	}
	return pickupPointsDto;
    }
     
    public PickupPointDto updatePickupPoint(PickupPointDto pickupPointDto, int busId) {
	PickupPoint pickupPoint = pickupPointRepository.save(Mapper.toPickupPoint(pickupPointDto));
	return Mapper.toPickupPointDto(pickupPoint);
    }
    
    public void deletePickupPoint(int pickupPointId) {
	pickupPointRepository.deleteById(pickupPointId);
    }
}
