package com.i2i.ibus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.i2i.ibus.dto.MessageDto;
import com.i2i.ibus.dto.OperatorDto;
import com.i2i.ibus.exception.IBusException;
import com.i2i.ibus.service.OperatorService;

import jakarta.validation.Valid;

/**
 * @author Ragul
 * @version 1.0
 * 
 * @created Nov 29 2022
 *
 */
@RestController
@RequestMapping("api/v1/operators")
public class OperatorController {
    private OperatorService operatorService;

    @Autowired
    private OperatorController(OperatorService operatorService) {
	this.operatorService = operatorService;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    private OperatorDto createOperator(@RequestBody @Valid OperatorDto operatorDto) throws IBusException {
	return operatorService.saveOperator(operatorDto);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    private List<OperatorDto> getAllOperatorDtos() {
	return operatorService.getAllOperatorDtos();
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    private OperatorDto getOperatorDtoById(@PathVariable int id) throws IBusException {
	return operatorService.getOperatorDtoById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    private OperatorDto updateOperator(@PathVariable int id, @RequestBody @Valid OperatorDto operatorDto)
	    throws IBusException {
	return operatorService.updateOperatorById(id, operatorDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    private MessageDto deleteOperator(@PathVariable int id) throws IBusException {
	operatorService.deleteOperatorById(id);
	return new MessageDto("200", "Deleted Successfully");
    }

}
