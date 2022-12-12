package com.i2i.ibus.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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
    @Max(value = 120, message = "max age is 120")
    private int age;
    @NotBlank
    @Pattern(regexp = "([a-zA-Z][ ]?){2,50}", message = "Please enter the valid name format. ")
    private String name;
    @NotBlank
    @Pattern(regexp = "(?i)^(male)|(female)|(others)$", message = "Please enter the valid format gender.")
    private String gender;
    @NotBlank
    @Pattern(regexp = "^([L]|[R])([L]|[U])([S]|[B])([0-9]?[1-9])$")
    private String seatNumber;

}