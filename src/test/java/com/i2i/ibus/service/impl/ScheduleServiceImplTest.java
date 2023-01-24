package com.i2i.ibus.service.impl;

import com.i2i.ibus.dto.BusDto;
import com.i2i.ibus.dto.ScheduleDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.Bus;
import com.i2i.ibus.model.Schedule;
import com.i2i.ibus.repository.ScheduleRepository;
import com.i2i.ibus.service.BusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@SpringBootTest
class ScheduleServiceImplTest {

    @Autowired
    private ScheduleRepository scheduleRepositoryDB;

    @Mock
    private BusService busService;
    @Mock
    private ScheduleRepository scheduleRepository;

    @InjectMocks
    private ScheduleServiceImpl scheduleService;

    private Schedule schedule;

    private ScheduleDto scheduleDto;

    private Bus bus;
    private BusDto busDto;
    private List<Schedule> schedules;

    private List<Schedule> schedulesByBus;
    private List<Schedule> schedulesByDepartureDate;
    @BeforeEach
    void setUp() {
        int id = 1;
        schedule = scheduleRepositoryDB.findById(id).get();
        schedules = scheduleRepositoryDB.findAll();
        schedulesByBus = scheduleRepositoryDB.findByBusId(schedule.getBus().getId());
        schedulesByDepartureDate =
                scheduleRepositoryDB.findByDepartureDateAndSourceAndDestination(schedule.getDepartureDate(),
                        schedule.getSource(), schedule.getDestination());
        scheduleDto = Mapper.toScheduleDto(schedule);
        bus = schedule.getBus();
        busDto = scheduleDto.getBus();
    }

    @Test
    @Transactional
    void addSchedule() {
        when(scheduleRepository.save(ArgumentMatchers.any(Schedule.class))).thenReturn(schedule);
        when(busService.getById(scheduleDto.getBusesId())).thenReturn(busDto);
//        assertThrows(IBusException.class, () -> busService.addBus(busDto));
//        bus.setBusNumber("TN01AB1234");
//        busDto.setBusNumber(bus.getBusNumber());
        ScheduleDto newSchedule = scheduleService.addSchedule(scheduleDto);
        assertNotNull(newSchedule);
        assertEquals(schedule.getDestination(), newSchedule.getDestination());
        assertEquals(schedule.getId(), newSchedule.getId());
    }

    @Test
    @Transactional
    void getAllSchedules() {
        when(scheduleRepository.findAll()).thenReturn(schedules);
        List<ScheduleDto> newScheduleDtos = scheduleService.getAllSchedules();
        assertEquals(schedules.size(), newScheduleDtos.size());
        for (int i = 0; i < schedules.size(); i++) {
            assertEquals(schedules.get(i).getSource(), newScheduleDtos.get(i).getSource());
            System.out.println(schedules.get(i).getSource());
            System.out.println(newScheduleDtos.get(i).getSource());
        }
        assertNotNull(newScheduleDtos);
    }

    @Test
    @Transactional
    void getSchedulesByBusId() {
        when(scheduleRepository.findById(schedule.getId())).thenReturn(Optional.of(schedule));
        when(scheduleRepository.findByBusId(schedule.getBus().getId())).thenReturn(schedulesByBus);
        List<ScheduleDto> newScheduleDtos = scheduleService.getSchedulesByBusId(schedule.getBus().getId());
        assertEquals(schedulesByBus.size(), newScheduleDtos.size());
        for (int i = 0; i < schedulesByBus.size(); i++) {
            assertEquals(schedulesByBus.get(i).getSource(), newScheduleDtos.get(i).getSource());
            System.out.println(schedulesByBus.get(i).getSource());
            System.out.println(newScheduleDtos.get(i).getSource());
        }
        assertNotNull(newScheduleDtos);
    }

    @Test
    @Transactional
    void getByDepartureDate() {
        when(scheduleRepository.findByDepartureDateAndSourceAndDestination(schedule.getDepartureDate(),
                schedule.getSource(), schedule.getDestination())).thenReturn(schedulesByDepartureDate);
        List<ScheduleDto> newScheduleDtos = scheduleService.getByDepartureDate(schedule.getDepartureDate(),
                schedule.getSource(),
                schedule.getDestination());
        assertEquals(schedulesByDepartureDate.size(), newScheduleDtos.size());
        for (int i = 0; i < schedulesByDepartureDate.size(); i++) {
            assertEquals(schedulesByDepartureDate.get(i).getSource(), newScheduleDtos.get(i).getSource());
            System.out.println(schedulesByDepartureDate.get(i).getSource());
            System.out.println(newScheduleDtos.get(i).getSource());
        }
        assertNotNull(newScheduleDtos);
    }

    @Test
    @Transactional
    void updateSchedule() {
        schedule.setSource("Salem");
        scheduleDto.setSource(schedule.getSource());
        when(scheduleRepository.save(ArgumentMatchers.any(Schedule.class))).thenReturn(schedule);
        when(busService.getById(scheduleDto.getBusesId())).thenReturn(busDto);
//        assertThrows(IBusException.class, () -> busService.addBus(busDto));
//        bus.setBusNumber("TN01AB1234");
//        busDto.setBusNumber(bus.getBusNumber());
        ScheduleDto newSchedule = scheduleService.updateSchedule(scheduleDto, scheduleDto.getId());
        assertNotNull(newSchedule);
        assertEquals(schedule.getDestination(), newSchedule.getDestination());
        assertEquals(schedule.getId(), newSchedule.getId());
        assertEquals("Salem", newSchedule.getSource());
    }

    @Test
    @Transactional
    void deleteSchedule() {
        when(scheduleRepository.findById(schedule.getId())).thenReturn(Optional.of(schedule));
        when(scheduleRepository.save(schedule)).thenReturn(schedule);
        scheduleService.deleteSchedule(schedule.getId());
        assertTrue(schedule.isDeleted());
        when(scheduleRepository.findById(0)).thenThrow(NoSuchElementException.class);
        assertThrows(IBusException.class, () -> scheduleService.deleteSchedule(0));
    }

    @Test
    @Transactional
    void validateDateAndTime() {
        assertTrue(scheduleService.validateDateAndTime(scheduleDto));
    }

    @Test
    @Transactional
    void validateSchedule() {
        when(scheduleRepository.findByDepartureDateAndDepartureTimeAndSourceAndDestinationAndBusId
                (scheduleDto.getDepartureDate(), scheduleDto.getDepartureTime(), scheduleDto.getSource(),
                        scheduleDto.getDestination(), scheduleDto.getBusesId())).thenReturn(Optional.of(schedule));
        assertThrows(IBusException.class, () -> scheduleService.validateSchedule(scheduleDto));
    }

    @Test
    @Transactional
    void setStatus() {
        schedule.setArrivingDate(LocalDate.now());
        schedule.setArrivingTime(LocalTime.now());
        scheduleService.setStatus(schedule);
        assertEquals("ended", schedule.getStatus());
    }

    @Test
    @Transactional
    void findByBusIdAndDepartureDateAndDepartureTime() {
        when(scheduleRepository.findByBusIdAndDepartureDateAndDepartureTime(schedule.getBus().getId(),
                schedule.getDepartureDate(), schedule.getDepartureTime())).thenReturn(Optional.of(schedule));
        assertEquals(Optional.of(schedule),
                scheduleService.findByBusIdAndDepartureDateAndDepartureTime(schedule.getBus().getId(),
                        schedule.getDepartureDate(), schedule.getDepartureTime()));
    }
}