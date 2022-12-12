package com.i2i.ibus.service.impl;

import com.i2i.ibus.dto.BusDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.Bus;
import com.i2i.ibus.repository.BusRepository;
import com.i2i.ibus.repository.OperatorRepository;
import com.i2i.ibus.service.BusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * <h1>Bus Ticket Booking Application
 * <h1>
 * <p>
 * Used to manipulate the Bus details in the application. Operators are
 * manipulate the bus details.
 * <p>
 *
 * @author Ananth.
 * @version 1.0.
 * @created Nov 29 2022
 */
@Service
public class BusServiceImpl implements BusService {

    private BusRepository busRepository;

    private OperatorRepository operatorRepository;

    @Autowired
    public BusServiceImpl(BusRepository busRepository, OperatorRepository operatorRepository) {
        this.busRepository = busRepository;
        this.operatorRepository = operatorRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BusDto addBus(BusDto busDto, int operatorId) {
        BusDto busDTO = null;
        try {
            busDto.setOperator(Mapper.toOperatorDto(operatorRepository.findById(operatorId).get()));
            Bus bus = Mapper.toBus(busDto);

            if (!busRepository.findByBusNumber(busDto.getBusNumber()).isPresent()) {
                busDTO = Mapper.toBusDto(busRepository.save(bus));
            } else {
                throw new IBusException("Bus Number is Duplicate");
            }
        } catch (NoSuchElementException Exception) {
            throw new IBusException("Operator does not exist");
        }
        return busDTO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
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
     * {@inheritDoc}
     */
    @Override
    public List<BusDto> getAllByOperatorId(int OperatorId) {
        List<Bus> buses = busRepository.findByOperatorId(OperatorId);
        List<BusDto> busesDto = new ArrayList<BusDto>();

        for (Bus bus : buses) {
            BusDto busDto = null;
            busDto = Mapper.toBusDto(bus);
            busesDto.add(busDto);
        }
        return busesDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BusDto getById(int id) {
        Bus bus = null;
        try {
            bus = busRepository.findById(id).get();
        } catch (NoSuchElementException exception) {
            throw new IBusException("Bus doesnot exists");
        }
        return Mapper.toBusDto(bus);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BusDto updateBus(BusDto busDto, int busId) {

        try {
            busDto.setId(busId);
            busDto.setOperator(Mapper.toOperatorDto(operatorRepository.findById(busDto.getOperatorId()).get()));

            Bus bus = Mapper.toBus(busDto);

            if (!busRepository.findByBusNumberForUpdate(busDto.getBusNumber(), busDto.getId()).isPresent()) {
                busDto = Mapper.toBusDto(busRepository.save(bus));
            } else {
                throw new IBusException("Bus Number is Duplicate");
            }
        } catch (NoSuchElementException Exception) {
            throw new IBusException("Operator doesnot exist");
        }
        return busDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteBus(int busId) {
        busRepository.deleteById(busId);
    }
}