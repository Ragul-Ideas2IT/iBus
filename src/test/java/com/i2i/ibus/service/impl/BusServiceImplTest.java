package com.i2i.ibus.service.impl;

import com.i2i.ibus.dto.BusDto;
import com.i2i.ibus.dto.OperatorDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.Bus;
import com.i2i.ibus.model.Operator;
import com.i2i.ibus.repository.BusRepository;
import com.i2i.ibus.service.OperatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class BusServiceImplTest {

    @Autowired
    private BusRepository busRepositoryDB;

    @Mock
    private OperatorService operatorService;
    @Mock
    private BusRepository busRepository;

    @InjectMocks
    private BusServiceImpl busService;

    private Bus bus;

    private BusDto busDto;

    private List<Bus> buses;

    private List<Bus> busesByOperator;

    private Operator operator;

    private OperatorDto operatorDto;

    @BeforeEach
    void setUp() {
        int id = 1;
        bus = busRepositoryDB.findById(id).get();
        buses = busRepositoryDB.findAll();
        busesByOperator = busRepositoryDB.findByOperatorId(bus.getOperator().getId());
        busDto = Mapper.toBusDto(bus);
        operator = bus.getOperator();
        operatorDto = Mapper.toOperatorDto(operator);
    }

    @Test
    @Transactional
    void addBus() {
        when(busRepository.save(ArgumentMatchers.any(Bus.class))).thenReturn(bus);
        when(operatorService.getOperatorDtoById(busDto.getOperatorsId())).thenReturn(operatorDto);
        when(busRepository.findByBusNumber(bus.getBusNumber())).thenReturn(Optional.of(bus));
        assertThrows(IBusException.class, () -> busService.addBus(busDto));
        bus.setBusNumber("TN01AB1234");
        busDto.setBusNumber(bus.getBusNumber());
        BusDto newBus = busService.addBus(busDto);
        assertNotNull(newBus);
        assertEquals(bus.getBusNumber(), newBus.getBusNumber());
        assertEquals(bus.getId(), newBus.getId());
    }

    @Test
    @Transactional
    void getAllBuses() {
        when(busRepository.findAll()).thenReturn(busRepositoryDB.findAll());
        List<BusDto> newBusDtos = busService.getAllBuses();
        assertEquals(buses.size(), newBusDtos.size());
        for (int i = 0; i < buses.size(); i++) {
            assertEquals(buses.get(i).getBusNumber(), newBusDtos.get(i).getBusNumber());
            System.out.println(buses.get(i).getBusNumber());
            System.out.println(newBusDtos.get(i).getBusNumber());
        }
        assertNotNull(newBusDtos);
    }

    @Test
    @Transactional
    void getAllByOperatorId() {
        when(busRepository.findById(bus.getId())).thenReturn(Optional.of(bus));
        when(busRepository.findByOperatorId(bus.getOperator().getId())).thenReturn(busesByOperator);
        List<BusDto> newBusDtos = busService.getAllByOperatorId(bus.getOperator().getId());
        assertEquals(buses.size(), newBusDtos.size());
        for (int i = 0; i < buses.size(); i++) {
            assertEquals(buses.get(i).getBusNumber(), newBusDtos.get(i).getBusNumber());
            System.out.println(buses.get(i).getBusNumber());
            System.out.println(newBusDtos.get(i).getBusNumber());
        }
        assertNotNull(newBusDtos);
    }

    @Test
    @Transactional
    void getById() {
        when(busRepository.findById(bus.getId())).thenReturn(Optional.of(bus));
        BusDto newBus = busService.getById(bus.getId());
        assertNotNull(newBus);
        assertEquals(bus.getId(), newBus.getId());
    }

    @Test
    @Transactional
    void updateBus() {
        busDto.setNumberOfSeats(60);
        bus.setNumberOfSeats(busDto.getNumberOfSeats());
        when(busRepository.findById(bus.getId())).thenReturn(Optional.of(bus));
        when(busRepository.save(ArgumentMatchers.any(Bus.class))).thenReturn(bus);
        BusDto newBus = busService.updateBus(busDto, bus.getId());
//        busDto.setBusNumber("TN01AB1111");
//        bus.setBusNumber(busDto.getBusNumber());
//        assertThrows(IBusException.class, () -> busService.updateBus(busDto, bus.getId()));
        assertNotNull(newBus);
        assertEquals(60, newBus.getNumberOfSeats());
    }

    @Test
    @Transactional
    void deleteBus() {
        when(busRepository.findById(bus.getId())).thenReturn(Optional.of(bus));
        when(busRepository.save(bus)).thenReturn(bus);
        busService.deleteBus(bus.getId());
        assertTrue(bus.isDeleted());
    }
}