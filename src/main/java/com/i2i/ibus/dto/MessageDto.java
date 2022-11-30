package com.i2i.ibus.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Ragul
 * @version 1.0
 * 
 * @created Nov 30 2022
 *
 */
@Getter
@Setter
@AllArgsConstructor
public class MessageDto {
    private String code;
    private String message;
}
