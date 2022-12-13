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
import com.i2i.ibus.service.OperatorService;

import javax.validation.Valid;

/**
 * @author Ragul
 * @version 1.0
 * @since Nov 29 2022
 */

@RestController
@RequestMapping("api/v1/operators")
public class OperatorController {
    private final OperatorService operatorService;

    @Autowired
    private OperatorController(OperatorService operatorService) {
        this.operatorService = operatorService;
    }

    /**
     * > The function creates a new operator and returns the created operator
     *
     * @param operatorDto The object that will be created.
     * @return OperatorDto
     */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    private OperatorDto createOperator(@RequestBody @Valid OperatorDto operatorDto) {
        return operatorService.saveOperator(operatorDto);
    }

    /**
     * > This function returns a list of all the operators in the database
     *
     * @return A list of OperatorDto objects.
     */
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    private List<OperatorDto> getAllOperators() {
        return operatorService.getAllOperatorDtos();
    }

    /**
     * > This function returns an OperatorDto object with the given id
     *
     * @param id The id of the operator to be retrieved.
     * @return OperatorDto
     */
    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    private OperatorDto getById(@PathVariable int id) {
        return operatorService.getOperatorDtoById(id);
    }

    /**
     * It updates the operator by id.
     *
     * @param id          The id of the operator to be updated.
     * @param operatorDto The object that will be used to update the operator.
     * @return OperatorDto
     */
    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    private OperatorDto updateOperator(@PathVariable int id, @RequestBody @Valid OperatorDto operatorDto) {
        return operatorService.updateOperatorById(id, operatorDto);
    }

    /**
     * It deletes the operator with the given id.
     *
     * @param id The id of the operator to be deleted.
     * @return A MessageDto object with a status code and a message.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    private MessageDto deleteOperator(@PathVariable int id) {
        operatorService.deleteOperatorById(id);
        return new MessageDto("200", "Deleted Successfully");
    }

}
