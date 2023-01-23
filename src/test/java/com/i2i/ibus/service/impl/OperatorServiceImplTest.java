package com.i2i.ibus.service.impl;

import com.i2i.ibus.dto.OperatorDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.Operator;
import com.i2i.ibus.repository.OperatorRepository;
import com.i2i.ibus.service.AccountService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@Log4j2
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OperatorServiceImplTest {
        @Mock
        private OperatorRepository operatorRepository;
        @Mock
        private AccountService accountService;

        @InjectMocks
        private OperatorServiceImpl operatorService;

        @Autowired
        private OperatorRepository operatorRepositoryDB;
        OperatorDto operatorDto;

        Operator operator;

        List<Operator> operators;

        List<OperatorDto> operatorDtos;

        int id;
        int notExistId;
        String notExistEmailId;
        String notExistPhoneNumber;
        String notExistGstNumber;

        @BeforeAll
        void init() {
            id = 1;
            notExistId = 2;
            notExistEmailId = "Tamilmani@gmail.com";
            notExistGstNumber = "123456ASDFG";
            notExistPhoneNumber = "990909090990";
        }

        @BeforeEach
        void setup() {
            operator = operatorRepositoryDB.findById(id).get();
            operator.setAddresses(new ArrayList<>());
            operatorDto = Mapper.toOperatorDto(operator);
        }

        @Test
        void validateOperator() {
            when(operatorRepository.findById(operator.getId())).thenReturn(Optional.of(operator));
            Operator newOperator = operatorService.validateOperator(operator.getId());
            Assertions.assertEquals(operator.toString(), newOperator.toString());
            log.info(operator.toString());
            log.info(newOperator.toString());
            Assertions.assertThrows(IBusException.class, () -> operatorService.validateOperator(notExistId));
        }

        @Test
        void validateMailIdPhoneNoAndGstNumber() {
            when(operatorRepository.findByMailId(operator.getMailId())).thenReturn(Optional.of(operator));
            Assertions.assertThrows(IBusException.class, () -> operatorService.validateMailIdPhoneNoAndGstNumber(operator.getMailId(), operator.getPhoneNumber(), operator.getGstNumber()));
            when(operatorRepository.findByPhoneNumber(operator.getPhoneNumber())).thenReturn(Optional.of(operator));
            Assertions.assertThrows(IBusException.class, () -> operatorService.validateMailIdPhoneNoAndGstNumber(notExistEmailId, operator.getPhoneNumber(), operator.getGstNumber()));
            when(operatorRepository.findByGstNumber(operator.getGstNumber())).thenReturn(Optional.of(operator));
            Assertions.assertThrows(IBusException.class, () -> operatorService.validateMailIdPhoneNoAndGstNumber(notExistEmailId, notExistPhoneNumber, operator.getGstNumber()));
        }

        @Test
        void validateMailIdPhoneNoAndGstNumberForUpdate() {
            when(operatorRepository.findByMailIdAndIdNot(operator.getMailId(), operator.getId())).thenReturn(Optional.of(operator));
            Assertions.assertThrows(IBusException.class, () -> operatorService.validateMailIdPhoneNoAndGstNumberForUpdate(operator.getMailId(), operator.getPhoneNumber(), operator.getGstNumber(), operator.getId()));
            when(operatorRepository.findByPhoneNumberAndIdNot(operator.getPhoneNumber(), operator.getId())).thenReturn(Optional.of(operator));
            Assertions.assertThrows(IBusException.class, () -> operatorService.validateMailIdPhoneNoAndGstNumberForUpdate(notExistEmailId, operator.getPhoneNumber(), operator.getGstNumber(), operator.getId()));
            when(operatorRepository.findByGstNumberAndIdNot(operator.getGstNumber(), operator.getId())).thenReturn(Optional.of(operator));
            Assertions.assertThrows(IBusException.class, () -> operatorService.validateMailIdPhoneNoAndGstNumberForUpdate(notExistEmailId, notExistPhoneNumber, operator.getGstNumber(), operator.getId()));
        }

        @Test
        void saveOperator() {
            when(operatorRepository.save(ArgumentMatchers.any(Operator.class))).thenReturn(operator);
            OperatorDto savedOperatorDto = operatorService.saveOperator(operatorDto);
            org.assertj.core.api.Assertions.assertThat(savedOperatorDto).usingRecursiveComparison().isEqualTo(operatorDto);
        }

        @Test
        void getAllOperatorDTOs() {
            operators = operatorRepositoryDB.findAll();
            for (Operator operator : operators) {
                operator.setAddresses(new ArrayList<>());
            }
            operatorDtos = Mapper.toOperatorDTOs(operators);
            when(operatorRepository.findAll()).thenReturn(operators);
            List<OperatorDto> newOperatorDtos = operatorService.getAllOperatorDTOs();
            Assertions.assertEquals(operatorDtos.size(), newOperatorDtos.size());
            for (int i = 0; i < newOperatorDtos.size(); i++) {
                org.assertj.core.api.Assertions.assertThat(newOperatorDtos.get(i)).usingRecursiveComparison().isEqualTo(operatorDtos.get(i));
            }
        }

        @Test
        void getOperatorDtoById() {
            when(operatorRepository.findById(operator.getId())).thenReturn(Optional.of(operator));
            OperatorDto newOperatorDto = operatorService.getOperatorDtoById(operator.getId());
            org.assertj.core.api.Assertions.assertThat(newOperatorDto).usingRecursiveComparison().isEqualTo(operatorDto);
        }

        @Test
        void updateOperatorById() {
            when(operatorRepository.findById(operator.getId())).thenReturn(Optional.of(operator));
            operator.setMailId("Tamil4@gmail.com");
            when(operatorRepository.save(ArgumentMatchers.any(Operator.class))).thenReturn(operator);
            OperatorDto newOperatorDto = operatorService.updateOperatorById(operator.getId(), operatorDto);
            Assertions.assertNotEquals(newOperatorDto.getMailId(), operatorDto.getMailId());
            log.info(newOperatorDto.getMailId());
            log.info(operatorDto.getMailId());
        }

        @Test
        void deleteOperatorById() {
            when(operatorRepository.findById(operator.getId())).thenReturn(Optional.of(operator));
            when(operatorRepository.save(operator)).thenReturn(operator);
            Operator newOperator = operatorRepository.save(operator);
            operatorService.deleteOperatorById(operator.getId());
            Assertions.assertTrue(newOperator.isDeleted());
            log.info(newOperator.isDeleted());
        }

}