package com.i2i.ibus.service.impl;

import com.i2i.ibus.dto.BusDto;
import com.i2i.ibus.dto.StopDto;
import com.i2i.ibus.dto.StopDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.Bus;
import com.i2i.ibus.model.Stop;
import com.i2i.ibus.model.Stop;
import com.i2i.ibus.repository.StopRepository;
import com.i2i.ibus.service.BusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class StopServiceImplTest {

    @Autowired
    private StopRepository stopRepositoryDB;

    @Mock
    private BusService busService;
    @Mock
    private StopRepository stopRepository;

    @InjectMocks
    private StopServiceImpl stopService;

    private Stop stop;

    private StopDto stopDto;

    private Bus bus;

    private BusDto busDto;
    private List<Stop> stops;

    private List<Stop> stopsByBus;
    @BeforeEach
    void setUp() {
        int id = 1;
        stop = stopRepositoryDB.findById(id).get();
        stops = stopRepositoryDB.findAll();
        stopDto = Mapper.toStopDto(stop);
        stopsByBus = stopRepositoryDB.findAllByBusId(stop.getBus().getId());
        bus = stop.getBus();
        busDto = stopDto.getBus();
    }

    @Test
    @Transactional
    void addStop() {
        when(stopRepository.save(ArgumentMatchers.any(Stop.class))).thenReturn(stop);
        when(busService.getById(stopDto.getBusesId())).thenReturn(busDto);
//        assertThrows(IBusException.class, () -> busService.addBus(busDto));
//        bus.setBusNumber("TN01AB1234");
//        busDto.setBusNumber(bus.getBusNumber());
        StopDto newStop = stopService.addStop(stopDto);
        assertNotNull(newStop);
        assertEquals(stop.getStopName(), newStop.getStopName());
        assertEquals(stop.getId(), newStop.getId());
    }

    @Test
    @Transactional
    void getStopsByBusId() {
        when(stopRepository.findById(stop.getId())).thenReturn(Optional.of(stop));
        when(stopRepository.findAllByBusId(stop.getBus().getId())).thenReturn(stopsByBus);
        List<StopDto> newStopDtos = stopService.getStopsByBusId(stop.getBus().getId());
        assertEquals(stopsByBus.size(), newStopDtos.size());
        for (int i = 0; i < stopsByBus.size(); i++) {
            assertEquals(stopsByBus.get(i).getStopName(), newStopDtos.get(i).getStopName());
            System.out.println(stopsByBus.get(i).getStopName());
            System.out.println(newStopDtos.get(i).getStopName());
        }
        assertNotNull(newStopDtos);
    }

    @Test
    @Transactional
    void updateStop() {
        stop.setStopName("Signal");
        stopDto.setStopName(stop.getStopName());
        when(stopRepository.save(ArgumentMatchers.any(Stop.class))).thenReturn(stop);
        when(busService.getById(stopDto.getBusesId())).thenReturn(busDto);
//        assertThrows(IBusException.class, () -> busService.addBus(busDto));
//        bus.setBusNumber("TN01AB1234");
//        busDto.setBusNumber(bus.getBusNumber());
        StopDto newStop = stopService.updateStop(stopDto, stopDto.getId());
        assertNotNull(newStop);
        assertEquals(stop.getCity(), newStop.getCity());
        assertEquals(stop.getId(), newStop.getId());
        assertEquals("Signal", newStop.getStopName());
    }

    @Test
    @Transactional
    void deleteStop() {
        when(stopRepository.findById(stop.getId())).thenReturn(Optional.of(stop));
        when(stopRepository.save(stop)).thenReturn(stop);
        stopService.deleteStop(stop.getId());
        assertTrue(stop.isDeleted());
        when(stopRepository.findById(0)).thenThrow(NoSuchElementException.class);
        assertThrows(IBusException.class, () -> stopService.deleteStop(0));
    }

    @Test
    @Transactional
    void findAllByBusIdAndCityAndStopName() {
        when(stopRepository.findAllByBusIdAndCityAndStopName(stop.getBus().getId(),
                stop.getCity(), stop.getStopName())).thenReturn(Optional.of(stop));
        assertEquals(Optional.of(stop),
                stopService.findAllByBusIdAndCityAndStopName(stop.getBus().getId(),
                        stop.getCity(), stop.getStopName()));
    }
}