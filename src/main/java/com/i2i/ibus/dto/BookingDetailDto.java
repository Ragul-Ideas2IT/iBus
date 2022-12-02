package com.i2i.ibus.dto;

import com.i2i.ibus.model.Seat;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Tamilmani
 * @version 1.0
 * 
 * @created Nov 30 2022
 *
 */
@Getter
@Setter
public class BookingDetailDto {

    private int age;
    private String name;
    @Pattern(regexp = "(?i)^(male)|(female)|(others)$", message = "Please enter the valid format gender")
    private String gender;
    private String seatId;

}