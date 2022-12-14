package com.i2i.ibus.service.impl;

import com.i2i.ibus.constants.Constants;
import com.i2i.ibus.dto.ScheduleDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.Schedule;
import com.i2i.ibus.repository.ScheduleRepository;
import com.i2i.ibus.repository.BusRepository;
import com.i2i.ibus.service.ScheduleService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * <h1>Bus Ticket Booking Application
 * <h1>
 * <p>
 * Used to manipulate the Bus schedule details in the application. Operators are
 * manipulate the bus schedule details.
 * <p>
 *
 * @author Ananth.
 * @version 1.0.
 * @created Nov 29 2022
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {

    private ScheduleRepository scheduleRepository;

    private BusRepository busRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, BusRepository busRepository) {
	this.scheduleRepository = scheduleRepository;
	this.busRepository = busRepository;
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
		scheduleDto.setStatus("started");
		scheduleDto.setBus(Mapper.toBusDto(busRepository.findById(scheduleDto.getBusId()).get()));
		schedule = scheduleRepository.save(Mapper.toSchedule(scheduleDto));
	    } else {
		logger.error(Constants.SAME_DEPATURE_AND_ARRIVAL_DATETIME);
		throw new IBusException(Constants.SAME_DEPATURE_AND_ARRIVAL_DATETIME);
	    }
	} catch (NoSuchElementException exception) {
	    logger.error("BusID " + scheduleDto.getBusId() + Constants.NOT_EXIST);
	    throw new IBusException(Constants.BUSID_NOT_EXIST);
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
	List<Schedule> schedules = scheduleRepository.findByDepartureDate(departureDate, source, destination);
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
		scheduleDto.setStatus("started");
		scheduleDto.setBus(Mapper.toBusDto(busRepository.findById(scheduleDto.getBusId()).get()));
		schedule = scheduleRepository.save(Mapper.toSchedule(scheduleDto));
	    } else {
		logger.error(Constants.SAME_DEPATURE_AND_ARRIVAL_DATETIME);
		throw new IBusException(Constants.SAME_DEPATURE_AND_ARRIVAL_DATETIME);
	    }
	} catch (NoSuchElementException exception) {
	    logger.error("BusID " + scheduleDto.getBusId() + Constants.NOT_EXIST);
	    throw new IBusException(Constants.BUSID_NOT_EXIST);
	}
	return Mapper.toScheduleDto(schedule);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteSchedule(int scheduleId) {
	scheduleRepository.deleteById(scheduleId);
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
    @Override
    public void setStatus(Schedule schedule) {

	if (null != schedule.getArrivingDate()) {
	    if ((LocalDate.now().compareTo(schedule.getArrivingDate()) >= 0)
		    && (LocalTime.now().compareTo(schedule.getArrivingTime()) >= 0)) {
		schedule.setStatus("ended");
	    }
	}
    }
}
