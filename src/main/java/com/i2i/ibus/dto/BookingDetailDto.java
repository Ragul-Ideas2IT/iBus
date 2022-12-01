package com.i2i.ibus.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
public class BookingDetailDto {

    private int age;
    @Pattern(regexp = "^[a-z]{2,30}$", message = "Please enter the valid format name")
    private String name;
    @Pattern(regexp = "(?i)^(male)|(female)|(others)$", message = "Please enter the valid format gender")
    private String gender;

}