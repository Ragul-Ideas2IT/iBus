package com.i2i.ibus.service.impl;

import com.i2i.ibus.constants.Constants;
import com.i2i.ibus.dto.OperatorDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.mapper.Mapper;
import com.i2i.ibus.model.Account;
import com.i2i.ibus.model.Operator;
import com.i2i.ibus.repository.AccountRepository;
import com.i2i.ibus.repository.OperatorRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OperatorServiceImplTest {

    @Autowired
    private OperatorRepository operatorRepositoryDB;

    @Autowired
    private AccountRepository accountRepository;
    @Mock
    private OperatorRepository operatorRepository;

    private AccountServiceImplTest accountServiceImplTest = new AccountServiceImplTest();
    @Mock
    private AccountServiceImpl accountService;

    @InjectMocks
    private OperatorServiceImpl operatorService;

    private OperatorDto operatorDto;
    private Operator operator;
    private List<Operator> operators;
    private List<OperatorDto> operatorDtos;
    private Account account;

    @BeforeEach
    public void setup() {
        int id = 1;
        operator = operatorRepositoryDB.findById(id).get();
        operators = operatorRepositoryDB.findAll();
        operatorDtos = Mapper.toOperatorDTOs(operators);
        account = accountRepository.findByMailId(operator.getMailId()).get();
        operatorDto = Mapper.toOperatorDto(operator);
        operatorDto.setPassword(account.getPassword());
    }

    @Test
    @Transactional
    void validateOperator() {
        when(operatorRepository.findById(operatorDto.getId())).thenReturn(Optional.of(operator));
        assertThrows(IBusException.class, () -> operatorService.validateOperator(2));
    }

    @Test
    @Transactional
    void validateMailId() {
        when(operatorRepository.findByMailId(operator.getMailId())).thenReturn(Optional.of(operator));
        assertThrows(IBusException.class, () -> operatorService.validateMailId(operator.getMailId()));
    }

    @Test
    @Transactional
    void validatePhoneNumber() {
        when(operatorRepository.findByPhoneNumber(operator.getPhoneNumber())).thenReturn(Optional.of(operator));
        assertThrows(IBusException.class, () -> operatorService.validatePhoneNumber(operator.getPhoneNumber()));
    }


    @Test
    @Transactional
    void validateGstNumber() {
        when(operatorRepository.findByGstNumber(operator.getGstNumber())).thenReturn(Optional.of(operator));
        assertThrows(IBusException.class, () -> operatorService.validateGstNumber(operator.getGstNumber()));
    }

    @Test
    @Transactional
    void validateMailIdForUpdate() {
        when(operatorRepository.findByMailIdAndIdNot(operator.getMailId(), operator.getId())).thenReturn(Optional.of(operator));
        assertThrows(IBusException.class, () -> operatorService.validateMailIdForUpdate(operator.getMailId(),
                operator.getId()));
    }

    @Test
    @Transactional
    void validatePhoneNumberForUpdate() {
        when(operatorRepository.findByPhoneNumberAndIdNot(operator.getPhoneNumber(), operator.getId())).thenReturn(Optional.of(operator));
        assertThrows(IBusException.class,
                () -> operatorService.validatePhoneNumberForUpdate(operator.getPhoneNumber(), operator.getId()));
    }


    @Test
    @Transactional
    void validateGstNumberForUpdate() {
        when(operatorRepository.findByGstNumberAndIdNot(operator.getGstNumber(), operator.getId())).thenReturn(Optional.of(operator));
        assertThrows(IBusException.class, () -> operatorService.validateGstNumberForUpdate(operator.getGstNumber(),
                operator.getId()));
    }

    @Test
    @Transactional
    public void saveOperator() {
        when(operatorRepository.save(ArgumentMatchers.any(Operator.class))).thenReturn(operator);
        when(accountService.addAccount(account)).thenReturn(account);
        OperatorDto newOperator = operatorService.saveOperator(operatorDto);
        assertNotNull(newOperator);
        assertEquals(operator.getOwnerName(), newOperator.getOwnerName());
    }

    @Test
    @Transactional
    void getAllOperatorDTOs() {
        when(operatorRepository.findAll()).thenReturn(operatorRepositoryDB.findAll());
        List<OperatorDto> operatorDtosService = operatorService.getAllOperatorDTOs();
        assertEquals(operators.size(), operatorDtos.size());
        for (int i = 0; i < operatorDtos.size(); i++) {
            assertEquals(operatorDtos.get(i).getMailId(), operatorDtosService.get(i).getMailId());
            System.out.println(operatorDtos.get(i).getMailId());
            System.out.println(operatorDtosService.get(i).getMailId());
        }
        assertNotNull(operatorDtosService);
    }

    @Test
    @Transactional
    void getOperatorDtoById() {
        when(operatorRepository.findById(operator.getId())).thenReturn(Optional.of(operator));
        OperatorDto newOperator = operatorService.getOperatorDtoById(operator.getId());
        assertNotNull(newOperator);
        assertEquals(operator.getId(), newOperator.getId());
    }

    @Test
    @Transactional
    void updateOperator() {
        operatorDto.setOwnerName("Jackson");
        operator.setOwnerName(operatorDto.getOwnerName());
        when(operatorRepository.findById(operator.getId())).thenReturn(Optional.of(operator));
        when(operatorRepository.save(ArgumentMatchers.any(Operator.class))).thenReturn(operator);
        OperatorDto newOperator = operatorService.updateOperatorById(operator.getId(), operatorDto);
        assertNotNull(newOperator);
        assertEquals("Jackson", newOperator.getOwnerName());
    }

    @Test
    @Transactional
    void deleteOperator() {
        when(operatorRepository.findById(operator.getId())).thenReturn(Optional.of(operator));
        when(operatorRepository.save(operator)).thenReturn(operator);
        operatorService.deleteOperatorById(operator.getId());
        assertTrue(operator.isDeleted());
    }

}

