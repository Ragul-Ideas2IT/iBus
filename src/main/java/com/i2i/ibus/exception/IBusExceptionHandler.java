package com.i2i.ibus.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.i2i.ibus.dto.MessageDto;

/**
 * @author Ragul
 * @version 1.0
 * 
 * @created Nov 30 2022
 *
 */
@ControllerAdvice
public class IBusExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * {@inheritDoc}
     */
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
	    HttpHeaders headers, HttpStatus status, WebRequest request) {
	List<String> errors = new ArrayList<String>();
	for (FieldError error : exception.getBindingResult().getFieldErrors()) {
	    errors.add(error.getField() + ": " + error.getDefaultMessage());
	}
	for (ObjectError error : exception.getBindingResult().getGlobalErrors()) {
	    errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
	}
	return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = IBusException.class)
    public MessageDto exception(IBusException exception) {
	MessageDto error = new MessageDto("208", exception.getMessage());
	return error;
    }
}
