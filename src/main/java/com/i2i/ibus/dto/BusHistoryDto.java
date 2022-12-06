package com.i2i.ibus.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class BusHistoryDto {

    private int id;
    @NotNull
    @FutureOrPresent
    private LocalDate departureDate;
    @NotNull
    private LocalTime departureTime;
    @NotNull
    @FutureOrPresent
    private LocalDate arrivingDate;
    @NotNull
    private LocalTime arrivingTime;
    @NotBlank
    @Pattern(regexp = "([a-zA-Z][ ]?){2,}")
    private String source;
    @NotBlank
    @Pattern(regexp = "([a-zA-Z][ ]?){2,}")
    private String destination;

}
