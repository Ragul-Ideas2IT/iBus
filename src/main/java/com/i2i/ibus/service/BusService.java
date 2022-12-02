package com.i2i.ibus.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.ibus.dto.BusDto;
import com.i2i.ibus.dto.BusHistoryDto;
import com.i2i.ibus.dto.PickupPointDto;
import com.i2i.ibus.dto.SeatDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.model.Bus;
import com.i2i.ibus.model.BusHistory;
import com.i2i.ibus.model.PickupPoint;
import com.i2i.ibus.model.Seat;
import com.i2i.ibus.repository.BusRepository;

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

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private ModelMapper mapper;

    /**
     * 
     * @param busDto
     * @return
     * @throws IBusException
     */
    public BusDto addBus(BusDto busDto, int operatorId) throws IBusException {
	BusDto busDTO = null;
	List<BusHistory> busHistories = busDto.getBusHistories().stream()
		.map(busHistoryDto -> mapper.map(busHistoryDto, BusHistory.class)).toList();
	List<PickupPoint> pickupPoints = busDto.getPickupPoints().stream()
		.map(pickupPointDto -> mapper.map(pickupPointDto, PickupPoint.class)).toList();
	List<Seat> seats = busDto.getSeats().stream().map(seatDto -> mapper.map(seatDto, Seat.class)).toList();
	Bus bus = mapper.map(busDto, Bus.class);
	bus.setBusHistories(busHistories);
	bus.setPickupPoints(pickupPoints);
	bus.setSeats(seats);

	if (null == busRepository.checkForDuplicates(busDto.getBusNumber())) {
	    List<BusHistoryDto> busHistoriesDto = bus.getBusHistories().stream()
		    .map(busHistory -> mapper.map(busHistory, BusHistoryDto.class)).toList();
	    List<PickupPointDto> pickupPointsDto = bus.getPickupPoints().stream()
		    .map(pickupPoint -> mapper.map(pickupPoint, PickupPointDto.class)).toList();
	    List<SeatDto> seatsDto = bus.getSeats().stream().map(seat -> mapper.map(seat, SeatDto.class)).toList();
	    busDTO = mapper.map(busRepository.save(bus), BusDto.class);
	    busDTO.setBusHistories(busHistoriesDto);
	    busDTO.setPickupPoints(pickupPointsDto);
	    busDto.setSeats(seatsDto);
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
	    List<BusHistoryDto> busHistoriesDto = bus.getBusHistories().stream()
		    .map(busHistory -> mapper.map(busHistory, BusHistoryDto.class)).toList();
	    List<PickupPointDto> pickupPointsDto = bus.getPickupPoints().stream()
		    .map(pickupPoint -> mapper.map(pickupPoint, PickupPointDto.class)).toList();
	    List<SeatDto> seatsDto = bus.getSeats().stream().map(seat -> mapper.map(seat, SeatDto.class)).toList();
	    busDto = mapper.map(bus, BusDto.class);
	    busDto.setBusHistories(busHistoriesDto);
	    busDto.setPickupPoints(pickupPointsDto);
	    busDto.setSeats(seatsDto);
	    busesDto.add(busDto);
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
	List<BusHistory> busHistories = busDto.getBusHistories().stream()
		.map(busHistoryDto -> mapper.map(busHistoryDto, BusHistory.class)).toList();
	List<PickupPoint> pickupPoints = busDto.getPickupPoints().stream()
		.map(pickupPointDto -> mapper.map(pickupPointDto, PickupPoint.class)).toList();
	List<Seat> seats = busDto.getSeats().stream().map(seatDto -> mapper.map(seatDto, Seat.class)).toList();
	Bus bus = mapper.map(busDto, Bus.class);
	bus.setBusHistories(busHistories);
	bus.setPickupPoints(pickupPoints);
	bus.setSeats(seats);

	if (null == busRepository.checkForUpdateDuplicates(busDto.getBusNumber(), busDto.getId())) {
	    List<BusHistoryDto> busHistoriesDto = bus.getBusHistories().stream()
		    .map(busHistory -> mapper.map(busHistory, BusHistoryDto.class)).toList();
	    List<PickupPointDto> pickupPointsDto = bus.getPickupPoints().stream()
		    .map(pickupPoint -> mapper.map(pickupPoint, PickupPointDto.class)).toList();
	    List<SeatDto> seatsDto = bus.getSeats().stream().map(seat -> mapper.map(seat, SeatDto.class)).toList();
	    busDTO = mapper.map(busRepository.save(bus), BusDto.class);
	    busDTO.setBusHistories(busHistoriesDto);
	    busDTO.setPickupPoints(pickupPointsDto);
	    busDto.setSeats(seatsDto);
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