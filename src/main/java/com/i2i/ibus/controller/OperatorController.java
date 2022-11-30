package com.i2i.ibus.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.i2i.ibus.dto.OperatorDto;
import com.i2i.ibus.service.OperatorService;

import lombok.RequiredArgsConstructor;

/**
 * @author Ragul
 * @version 1.0
 * 
 * @created Nov 29 2022
 *
 */
@RestController
@RequestMapping("api/v1/operators")
@RequiredArgsConstructor
public class OperatorController {

    private final OperatorService operatorService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    private OperatorDto createOperator(@RequestBody @Validated OperatorDto operatorDto) {
	return operatorService.saveOperator(operatorDto);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    private List<OperatorDto> getAllOperatorDtos() {
	return operatorService.getAllOperatorDtos();
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    private OperatorDto getOperatorDtoById(@PathVariable int id) {
	return operatorService.getOperatorDtoById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    private OperatorDto updateOperator(@PathVariable int id, @RequestBody @Validated OperatorDto operatorDto) {
	return operatorService.updateOperatorById(operatorDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    private void deleteOperator(@PathVariable int id) {
	operatorService.deleteOperatorById(id);
    }

}
