package com.i2i.ibus.service.impl;

import com.i2i.ibus.dto.BusDto;
import com.i2i.ibus.dto.SeatDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.Bus;
import com.i2i.ibus.model.Seat;
import com.i2i.ibus.repository.BusRepository;
import com.i2i.ibus.repository.SeatRepository;
import com.i2i.ibus.service.BusService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
@Log4j2
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SeatServiceImplTest {

    @Mock
    SeatRepository seatRepository;

    @Mock
    BusService busService;

    @InjectMocks
    SeatServiceImpl seatService;

    @Autowired
    BusRepository busRepositoryDB;

    @Autowired
    SeatRepository seatRepositoryDB;

    int seatId;
    int busId;
    int notExistId;
    Seat seat;
    SeatDto seatDto;

    BusDto busDto;
    Bus bus;

    @BeforeAll
    void init() {
        seatId = 1;
        busId = 1;
        notExistId = 0;
    }

    @BeforeEach
    void setup() {
        seat = seatRepositoryDB.findById(seatId).get();
        bus = busRepositoryDB.findById(busId).get();
        seat.setBus(bus);
        seatDto = Mapper.toSeatDto(seat);
        busDto = Mapper.toBusDto(bus);
        seatDto.setBusesId(busId);
    }

    @Test
    void addSeat() {
        when(seatRepository.findBySeatNumberAndBusId(seatDto.getSeatNumber(), seatDto.getBusesId())).thenReturn(Optional.of(seat));
        when(busService.getById(seatDto.getId())).thenReturn(busDto);
        when(seatRepository.save(ArgumentMatchers.any(Seat.class))).thenReturn(seat);
        Assertions.assertThrows(IBusException.class, () -> seatService.addSeat(seatDto));
        seatDto.setBusesId(notExistId);
        SeatDto newSeatDto = seatService.addSeat(seatDto);
        Assertions.assertNotNull(newSeatDto.getBus());
    }

    @Test
    void getAllByBusId() {
        List<Seat> seats = seatRepositoryDB.findAllByBusId(busId);
        List<SeatDto> seatDtos = new ArrayList<SeatDto>();
        for (Seat newSeat : seats) {
            seatDtos.add(Mapper.toSeatDto(newSeat));
        }

        when(seatRepository.findAllByBusId(busId)).thenReturn(seats);
        List<SeatDto> newSeatDtos = seatService.getAllByBusId(busId);
        Assertions.assertEquals(seatDtos.size(), newSeatDtos.size());
    }

    @Test
    void getBySeatId() {
        when(seatRepository.findById(seatId)).thenReturn(Optional.of(seat));
        SeatDto newSeatDto = seatService.getBySeatId(seatId);
        org.assertj.core.api.Assertions.assertThat(seatDto).usingRecursiveComparison().ignoringFields("busesId").isEqualTo(newSeatDto);
        Assertions.assertThrows(IBusException.class, () -> seatService.getBySeatId(notExistId));
    }

    @Test
    void updateSeat() {
        when(seatRepository.findBySeatNumberAndBusIdAndIdNot(seatDto.getSeatNumber(), seatDto.getBusesId(), seatId)).thenReturn(Optional.of(seat));
        Assertions.assertThrows(IBusException.class, () -> seatService.updateSeat(seatDto, seatId));
        when(busService.getById(busId)).thenReturn(busDto);
        when(seatRepository.save(ArgumentMatchers.any(Seat.class))).thenReturn(seat);
        SeatDto newSeatDto = seatService.updateSeat(seatDto, notExistId);
        Assertions.assertNotNull(newSeatDto.getBus());
    }

    @Test
    void deleteSeat() {
        when(seatRepository.findById(seatId)).thenReturn(Optional.of(seat));
        seat.setDeleted(true);
        when(seatRepository.save(seat)).thenReturn(seat);
        Seat newSeat = seatRepository.save(seat);
        seatService.deleteSeat(seatId);
        Assertions.assertTrue(newSeat.isDeleted());
        Assertions.assertThrows(IBusException.class, () -> seatService.deleteSeat(notExistId));
    }

    @Test
    void findBySeatNumberAndBusId() {
        when(seatRepository.findBySeatNumberAndBusId(seatDto.getSeatNumber(), busId)).thenReturn(Optional.of(seat));
        Seat newSeat = seatService.findBySeatNumberAndBusId(seatDto.getSeatNumber(), busId).get();
        Assertions.assertNotNull(newSeat);
    }
}