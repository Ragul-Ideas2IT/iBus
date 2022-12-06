package com.i2i.ibus.dto;

import java.time.LocalTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Ananth.
 * @version 1.0.
 * 
 * @created Nov 29 2022
 *
 */
@Getter
@Setter
public class PickupPointDto {

    private int id;
    @NotBlank
    @Pattern(regexp = "([a-zA-Z][ ]?){2,}")
    private String stopName;
    @NotBlank
    @Pattern(regexp = "([a-zA-Z][ ]?){2,}")
    private String landMark;
    @NotBlank
    @FutureOrPresent
    private LocalTime arrivingTime;
    @NotBlank
    @FutureOrPresent
    private LocalTime departureTime;

}
