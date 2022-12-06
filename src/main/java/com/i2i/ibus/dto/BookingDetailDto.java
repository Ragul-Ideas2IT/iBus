package com.i2i.ibus.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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

    @Min(value = 2, message = "min age is 2")
    @Max(value = 70, message = "max age is 70")
    private int age;
    @Pattern(regexp = "([a-zA-Z][ ]?){2,50}", message = "Please enter the valid name format. ")
    private String name;
    @Pattern(regexp = "(?i)^(male)|(female)|(others)$", message = "Please enter the valid format gender.")
    private String gender;

}