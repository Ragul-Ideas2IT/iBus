package com.i2i.ibus.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.i2i.ibus.dto.MessageDto;

/**
 * @author Ragul
 * @version 1.0
 * @created Nov 30 2022
 */
@RestControllerAdvice
public class IBusExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * {@inheritDoc}
     */
    @Override
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

    /**
     * This function will be called whenever an IBusException is thrown. It will return a 400 error with the message from
     * the exception
     *
     * @param exception The exception object that was thrown.
     * @return A MessageDto object with a status code of 400 and the message from the exception.
     */
    @ExceptionHandler(value = IBusException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public MessageDto exception(IBusException exception) {
        return new MessageDto("400", exception.getMessage());
    }
}
