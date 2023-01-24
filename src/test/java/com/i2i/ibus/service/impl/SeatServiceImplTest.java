package com.i2i.ibus.service.impl;

import com.i2i.ibus.dto.BusDto;
import com.i2i.ibus.dto.SeatDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.Bus;
import com.i2i.ibus.model.Seat;
import com.i2i.ibus.repository.SeatRepository;
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
class SeatServiceImplTest {

    @Autowired
    private SeatRepository seatRepositoryDB;

    @Mock
    private BusService busService;
    @Mock
    private SeatRepository seatRepository;

    @InjectMocks
    private SeatServiceImpl seatService;

    private Seat seat;

    private Bus bus;
    private BusDto busDto;
    private SeatDto seatDto;

    private List<Seat> seats;

    private List<Seat> seatsByBus;

    @BeforeEach
    void setUp() {
        int id = 1;
        seat = seatRepositoryDB.findById(id).get();
        seats = seatRepositoryDB.findAll();
        seatsByBus = seatRepositoryDB.findAllByBusId(seat.getBus().getId());
        seatDto = Mapper.toSeatDto(seat);
        bus = seat.getBus();
        busDto = Mapper.toBusDto(bus);
    }

    @Test
    @Transactional
    void addSeat() {
        when(seatRepository.save(ArgumentMatchers.any(Seat.class))).thenReturn(seat);
        when(busService.getById(seatDto.getBusesId())).thenReturn(busDto);
        when(seatRepository.findBySeatNumberAndBusId(seat.getSeatNumber(), seat.getBus().getId()))
                .thenReturn(Optional.of(seat));
//        assertThrows(IBusException.class, () -> seatService.addSeat(seatDto));
//        seat.setSeatNumber("W1");
        SeatDto newSeat = seatService.addSeat(seatDto);
        assertNotNull(newSeat);
        assertEquals(seat.getSeatNumber(), newSeat.getSeatNumber());
        assertEquals(seat.getId(), newSeat.getId());
//        seatDto.setSeatNumber(seat.getSeatNumber());
        when(busService.getById(0)).thenThrow(NoSuchElementException.class);
        seatDto.setBusesId(0);
        assertThrows(IBusException.class, () -> seatService.addSeat(seatDto));

    }

    @Test
    @Transactional
    void getAllByBusId() {
        when(seatRepository.findById(seat.getId())).thenReturn(Optional.of(seat));
        when(seatRepository.findAllByBusId(seat.getBus().getId())).thenReturn(seats);
        List<SeatDto> newSeatDtos = seatService.getAllByBusId(seat.getBus().getId());
        assertEquals(seats.size(), newSeatDtos.size());
        for (int i = 0; i < seats.size(); i++) {
            assertEquals(seats.get(i).getSeatNumber(), newSeatDtos.get(i).getSeatNumber());
            System.out.println(seats.get(i).getSeatNumber());
            System.out.println(newSeatDtos.get(i).getSeatNumber());
        }
        assertNotNull(newSeatDtos);
//        when(seatRepository.findById(0)).thenThrow(NoSuchElementException.class);
//        assertThrows(IBusException.class, () -> seatService.deleteSchedule(0));

    }

    @Test
    @Transactional
    void getBySeatId() {
        when(seatRepository.findById(seat.getId())).thenReturn(Optional.of(seat));
        SeatDto newSeat = seatService.getBySeatId(seat.getId());
        assertNotNull(newSeat);
        assertEquals(seat.getId(), newSeat.getId());
        when(seatRepository.findById(0)).thenThrow(NoSuchElementException.class);
        assertThrows(IBusException.class, () -> seatService.getBySeatId(0));

    }

    @Test
    @Transactional
    void updateSeat() {
        seatDto.setGender("Female");
        seat.setGender(seatDto.getGender());
        when(seatRepository.findById(seat.getId())).thenReturn(Optional.of(seat));
        when(seatRepository.save(ArgumentMatchers.any(Seat.class))).thenReturn(seat);
        SeatDto newSeat = seatService.updateSeat(seatDto, seat.getId());
        assertNotNull(newSeat);
        assertEquals("Female", newSeat.getGender());
        when(busService.getById(0)).thenThrow(NoSuchElementException.class);
        seatDto.setBusesId(0);
        assertThrows(IBusException.class, () -> seatService.updateSeat(seatDto, seatDto.getId()));

    }

    @Test
    @Transactional
    void deleteSeat() {
        when(seatRepository.findById(seat.getId())).thenReturn(Optional.of(seat));
        when(seatRepository.save(seat)).thenReturn(seat);
        seatService.deleteSeat(seat.getId());
        assertTrue(seat.isDeleted());
        when(seatRepository.findById(0)).thenThrow(NoSuchElementException.class);
        assertThrows(IBusException.class, () -> seatService.deleteSeat(0));

    }

    @Test
    @Transactional
    void findBySeatNumberAndBusId() {
        when(seatRepository.findBySeatNumberAndBusId(seat.getSeatNumber(), seat.getBus().getId()))
                .thenReturn(Optional.of(seat));
        assertEquals(Optional.of(seat), seatService.findBySeatNumberAndBusId(seat.getSeatNumber(), seat.getBus().getId()));
    }
}