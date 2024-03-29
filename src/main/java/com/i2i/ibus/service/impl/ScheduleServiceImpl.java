/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.ibus.constants.Constants;
import com.i2i.ibus.dto.ScheduleDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.Schedule;
import com.i2i.ibus.repository.ScheduleRepository;
import com.i2i.ibus.service.BusService;
import com.i2i.ibus.service.ScheduleService;

/**
 * Bus Ticket Booking Application
 * Used to manipulate the Bus schedule details in the application. Operators are
 * manipulate the bus schedule details.
 *
 * @author Ananth.
 * @version 1.0.
 * @since Nov 29 2022
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {

    private ScheduleRepository scheduleRepository;

    private BusService busService;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, BusService busService) {
        this.scheduleRepository = scheduleRepository;
        this.busService = busService;
    }

    private Logger logger = LogManager.getLogger(ScheduleServiceImpl.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public ScheduleDto addSchedule(ScheduleDto scheduleDto) {
        Schedule schedule = null;

        try {
            if (validateDateAndTime(scheduleDto)) {
                validateSchedule(scheduleDto);
                scheduleDto.setStatus(Constants.STARTED);
                scheduleDto.setBus(busService.getById(scheduleDto.getBusesId()));
                schedule = scheduleRepository.save(Mapper.toSchedule(scheduleDto));
                logger.info(Constants.CREATE_MESSAGE + Constants.SCHEDULE_ID + schedule.getId());
            } else {
                logger.error(Constants.SAME_DEPARTURE_AND_ARRIVAL_DATETIME);
                throw new IBusException(Constants.SAME_DEPARTURE_AND_ARRIVAL_DATETIME);
            }
        } catch (NoSuchElementException exception) {
            logger.error(exception.getMessage());
            throw new IBusException(Constants.BUS_ID_NOT_EXIST);
        }
        return Mapper.toScheduleDto(schedule);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ScheduleDto> getAllSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();
        List<ScheduleDto> schedulesDto = new ArrayList<ScheduleDto>();

        for (Schedule schedule : schedules) {
            setStatus(schedule);
            ScheduleDto scheduleDto = null;
            scheduleDto = Mapper.toScheduleDto(schedule);
            schedulesDto.add(scheduleDto);
        }
        return schedulesDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ScheduleDto> getSchedulesByBusId(int busId) {
        List<Schedule> schedules = scheduleRepository.findByBusId(busId);
        List<ScheduleDto> schedulesDto = new ArrayList<ScheduleDto>();

        for (Schedule schedule : schedules) {
            setStatus(schedule);
            ScheduleDto scheduleDto = null;
            scheduleDto = Mapper.toScheduleDto(schedule);
            schedulesDto.add(scheduleDto);
        }
        return schedulesDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ScheduleDto> getByDepartureDate(LocalDate departureDate, String source, String destination) {
        List<Schedule> schedules = scheduleRepository.findByDepartureDateAndSourceAndDestination(departureDate, source,
                destination);
        List<ScheduleDto> schedulesDto = new ArrayList<ScheduleDto>();

        for (Schedule schedule : schedules) {
            setStatus(schedule);
            ScheduleDto scheduleDto = null;
            scheduleDto = Mapper.toScheduleDto(schedule);
            schedulesDto.add(scheduleDto);
        }
        return schedulesDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ScheduleDto updateSchedule(ScheduleDto scheduleDto, int scheduleId) {
        Schedule schedule = null;

        try {
            if (validateDateAndTime(scheduleDto)) {
                scheduleDto.setId(scheduleId);
                scheduleDto.setStatus(Constants.STARTED);
                scheduleDto.setBus(busService.getById(scheduleDto.getBusesId()));
                schedule = scheduleRepository.save(Mapper.toSchedule(scheduleDto));
                logger.info(Constants.CREATE_MESSAGE + Constants.SCHEDULE_ID + schedule.getId());
            } else {
                logger.error(Constants.SAME_DEPARTURE_AND_ARRIVAL_DATETIME);
                throw new IBusException(Constants.SAME_DEPARTURE_AND_ARRIVAL_DATETIME);
            }
        } catch (NoSuchElementException exception) {
            logger.error(exception.getMessage());
            throw new IBusException(Constants.BUS_ID_NOT_EXIST);
        }
        return Mapper.toScheduleDto(schedule);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteSchedule(int scheduleId) {
        try {
            Schedule schedule = scheduleRepository.findById(scheduleId).get();
            schedule.setDeleted(true);
            scheduleRepository.save(schedule);
            logger.info(Constants.DELETE_MESSAGE + Constants.SCHEDULE_ID + scheduleId);
        } catch (NoSuchElementException exception) {
            logger.error(exception.getMessage());
            throw new IBusException(Constants.SCHEDULE_NOT_EXIST);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validateDateAndTime(ScheduleDto scheduleDto) {
        boolean isValid = false;
        LocalDateTime departureDateTime = LocalDateTime.of(scheduleDto.getDepartureDate(),
                scheduleDto.getDepartureTime());
        LocalDateTime arrivalDateTime = LocalDateTime.of(scheduleDto.getArrivingDate(), scheduleDto.getArrivingTime());

        if (((arrivalDateTime.compareTo(departureDateTime)) > 0)) {
            isValid = true;
        }
        return isValid;
    }

    /**
     * {@inheritDoc}
     */
    public void validateSchedule(ScheduleDto scheduleDto) {
        if (scheduleRepository.findByDepartureDateAndDepartureTimeAndSourceAndDestinationAndBusId(
                scheduleDto.getDepartureDate(), scheduleDto.getDepartureTime(), scheduleDto.getSource(),
                scheduleDto.getDestination(), scheduleDto.getBusesId()).isPresent()) {
            logger.error(Constants.DUPLICATE_SCHEDULE);
            throw new IBusException(Constants.DUPLICATE_SCHEDULE);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStatus(Schedule schedule) {

        if (null != schedule.getArrivingDate()) {
            if ((LocalDate.now().compareTo(schedule.getArrivingDate()) >= 0)
                    && (LocalTime.now().compareTo(schedule.getArrivingTime()) >= 0)) {
                schedule.setStatus(Constants.ENDED);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Schedule> findByBusIdAndDepartureDateAndDepartureTime(int busId, LocalDate departureDate,
                                                                          LocalTime departureTime) {
        return scheduleRepository.findByBusIdAndDepartureDateAndDepartureTime(busId, departureDate, departureTime);
    }
}
